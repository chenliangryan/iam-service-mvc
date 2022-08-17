package com.yugabyte.demo.iamservicemvc.service;

import com.yugabyte.demo.iamservicemvc.domain.UserAudit;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserActivitiesVO {

  private String action;
  private String description;
  private LocalDateTime transactionTime;
  private String device;
  private String clientIp;
  private String location;

  public static UserActivitiesVO from(UserAudit o) {
    UserActivitiesVO vo = new UserActivitiesVO();
    vo.init(o);
    return vo;
  }

  public void init(UserAudit o) {
    this.action = o.getAction();
    this.clientIp = o.getClientIp();
    this.description = o.getDescription();
    this.device = o.getDevice();
    this.location = o.getLocation();
    this.transactionTime = o.getTransactionTime();
  }

}
