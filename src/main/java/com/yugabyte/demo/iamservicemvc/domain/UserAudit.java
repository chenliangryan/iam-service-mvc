package com.yugabyte.demo.iamservicemvc.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.GeneratorStrategy;

@Entity
@Table(name = "user_audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAudit implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

  public UserAudit(UserSvcAccount userSvcAccount) {
    this.userSvcAccount = userSvcAccount;
    this.action = "login";
    this.description = "Login activity";
    this.transactionTime = LocalDateTime.now();
    this.device = "Webflux Webapp";
    this.clientIp = "1.1.1.1";
    this.location = "Singapore";
  }
}
