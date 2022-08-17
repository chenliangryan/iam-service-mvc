package com.yugabyte.demo.iamservicemvc.service;

import static java.lang.String.format;

import com.yugabyte.demo.iamservicemvc.repository.UserAuditRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserCredentialsRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserProfileRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserSvcAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private final UserProfileRepository userProfileRepository;
  private final UserAuditRepository userAuditRepository;

  private final UserSvcAccountRepository userSvcAccountRepository;

  private final UserCredentialsRepository userCredentialsRepository;



  public UserService(UserProfileRepository userProfileRepository, UserAuditRepository userAuditRepository,
    UserSvcAccountRepository userSvcAccountRepository,
    UserCredentialsRepository userCredentialsRepository) {
    this.userProfileRepository = userProfileRepository;
    this.userAuditRepository = userAuditRepository;
    this.userSvcAccountRepository = userSvcAccountRepository;
    this.userCredentialsRepository = userCredentialsRepository;
  }


  public UserProfileVO authenticate(String userId, String passwordHash) {
    var credential = userCredentialsRepository.getByIdAndPasswordHash(userId, passwordHash);

    if (credential == null) {
      log.info("{}: did not fined a user with that userId", userId);
      throw new RuntimeException(format("%1$s: Not Found", userId));
    }
    log.info("{}: logged in", userId);
    return getProfileWithActivities(userId);
  }

  public UserProfileVO getProfileWithActivities(String userId) {
    var credentialOptional = userCredentialsRepository.findById(userId);
    if(credentialOptional.isEmpty()){
      throw new RuntimeException(("%1$s: user not found"));
    }
    var credential = credentialOptional.get();
    var userProfileId = credential.getUserProfile().getId();
    var userProfileOptional = userProfileRepository.findById(userProfileId);
    if (userProfileOptional.isEmpty()) {
      throw new RuntimeException(format("%1$s: Missing user profile for userId", userId));
    }
    var profileVo = UserProfileVO.from(userProfileOptional.get());
    var svcAccounts = userSvcAccountRepository.findAllByUserCredential_Id(userId)
            .stream()
            .map(ServiceAccountVO::from)
            .toList();
    var activities = userAuditRepository.recentLogins(userId, PageRequest.of(0,10))
            .stream()
            .map(UserActivitiesVO::from)
            .toList();
    profileVo.setServiceAccount(svcAccounts);
    profileVo.setUserActivities(activities);
    return profileVo;
  }

  public Boolean updatePassword(String userId, String oldPasswordHash, String newPasswordHash){
    var result = userCredentialsRepository.updatePasswordHash(userId, oldPasswordHash, newPasswordHash);
    log.info("Password change [{}]: {}", userId, result);
    return result;
  }
}
