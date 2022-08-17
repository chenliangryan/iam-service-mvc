package com.yugabyte.demo.iamservicemvc.repository;

import com.yugabyte.demo.iamservicemvc.domain.UserProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

  @Query("""
    SELECT up
    FROM UserProfile up LEFT OUTER JOIN up.credential  uc
    WHERE uc.id = :userId AND uc.passwordHash = :passwordHash
    """)
  UserProfile authenticate(String userId, String passwordHash);

  UserProfile findByCredential_IdAndCredential_PasswordHash(String userId, String passwordHash);

  @Query("""
    SELECT up
    FROM UserProfile up LEFT JOIN  up.credential uc
    WHERE
      uc.id = :userId
    """)
  Optional<UserProfile> findByUserId(String userId);

}
