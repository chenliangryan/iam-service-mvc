package com.yugabyte.demo.iamservicemvc.service;

import com.yugabyte.demo.iamservicemvc.domain.UserProfile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileVO {

  private Integer userProfileId;
  private String firstName;
  private String lastName;
  private String gender;
  private LocalDate birthday;
  private String email;
  private String emailVerified;
  private LocalDateTime createTime;
  private LocalDateTime lastAccessTime;
  private String salutation;
  private List<ServiceAccountVO> serviceAccount = new ArrayList<>(0);
  private List<UserActivitiesVO> UserActivities = new ArrayList<>(0);

  public void init(UserProfile o) {
    this.birthday = o.getBirthday();
    this.createTime = o.getCreateTime();
    this.email = o.getEmail();
    this.userProfileId = o.getId();
    this.emailVerified = o.getEmailVerified();
    this.firstName = o.getFirstName();
    this.lastName = o.getLastName();
    this.gender = o.getGender();
    this.lastAccessTime = o.getLastAccessTime();
    this.salutation = o.getSalutation();
  }

  public static UserProfileVO from(UserProfile o) {
    UserProfileVO vo = new UserProfileVO();
    vo.init(o);
    return vo;
  }

}
