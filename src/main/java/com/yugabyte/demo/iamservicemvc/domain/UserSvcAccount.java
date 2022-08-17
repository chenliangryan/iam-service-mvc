package com.yugabyte.demo.iamservicemvc.domain;

import com.yugabyte.demo.iamservicemvc.domain.UserSvcAccount.UserSvcAccountId;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_svc_account")
@IdClass(UserSvcAccountId.class)
public class UserSvcAccount implements Serializable {

  @Id
  @Column(name = "account_id")
  Long accountId;

//  @Id
//  @Column(name = "user_id", insertable = false, updatable = false)
//  String userId;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  UserCredential userCredential;

  @Column(name = "create_time")
  LocalDateTime createTime;

  @Column(name = "last_access_time")
  LocalDateTime lastAccessTime;


  @Column(name = "user_verified")
  String userVerified;

  @Column(name = "svc_name")
  String svcName;

  @Column(name = "svc_description")
  String svcDescription;


  public static class UserSvcAccountId implements Serializable{
    @Id
    @Column(name = "account_id")
    Long accountId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserCredential userCredential;
  }
}
