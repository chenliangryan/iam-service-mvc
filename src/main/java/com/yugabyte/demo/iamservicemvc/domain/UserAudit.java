package com.yugabyte.demo.iamservicemvc.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAudit implements Serializable {

  @Id
  @Column(name = "id")
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumns({
    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  })
  private UserSvcAccount userSvcAccount;

//  @Column(name = "user_id", insertable = false, updatable = false)
//  private String userId;

  @Column(name = "action")
  private String action;

  @Column(name = "description")
  private String description;


  @Column(name = "transaction_time")
  private LocalDateTime transactionTime;

  @Column(name = "device")
  private String device;

  @Column(name = "client_ip")
  private String clientIp;

  @Column(name = "location")
  private String location;

}
