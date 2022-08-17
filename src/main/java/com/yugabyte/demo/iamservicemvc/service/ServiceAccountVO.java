package com.yugabyte.demo.iamservicemvc.service;

import com.yugabyte.demo.iamservicemvc.domain.UserSvcAccount;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ServiceAccountVO {

  private Long accountId;
  private String userId;
  private LocalDateTime creationTime;
  private LocalDateTime lastAccessTime;
  private String userVerified;
  private String svcName;
  private String svcDescription;

  public void init(UserSvcAccount o){
    this.accountId = o.getAccountId();
    this.userId = o.getUserCredential().getId();
    this.creationTime = o.getCreateTime();
    this.lastAccessTime = o.getLastAccessTime();
    this.userVerified = o.getUserVerified();
    this.svcName = o.getSvcName();
    this.svcDescription = o.getSvcDescription();
  }
  public static ServiceAccountVO from(UserSvcAccount userSvcAccount){
    var vo = new ServiceAccountVO();
    vo.init(userSvcAccount);
    return vo;
  }

}
