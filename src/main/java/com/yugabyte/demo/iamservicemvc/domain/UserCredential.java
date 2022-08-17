package com.yugabyte.demo.iamservicemvc.domain;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="user_credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential implements Serializable {

  @Id
  @Column(name = "user_id")
  private String id;

  @Column(name = "password_hash")
  private String passwordHash;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private UserProfile userProfile;

  @OneToMany(fetch = LAZY, mappedBy = "userCredential")
  private List<UserSvcAccount> userSvcAccounts;

}
