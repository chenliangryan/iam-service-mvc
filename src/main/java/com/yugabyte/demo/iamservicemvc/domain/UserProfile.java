package com.yugabyte.demo.iamservicemvc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_profile")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements Serializable {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "gender")
  String gender;

  @Column(name = "birthday")
  LocalDate birthday;

  @Column(name = "mobile_no")
  private String mobileNo;

  @Column(name = "email")
  private String email;

  @Column(name = "email_verified")
  private String emailVerified;

  @Column(name = "create_time")
  private LocalDateTime createTime;

  @Column(name = "last_access_time")
  private LocalDateTime lastAccessTime;

  @Column(name = "salutation")
  private String salutation;

  @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private UserCredential credential;
}
