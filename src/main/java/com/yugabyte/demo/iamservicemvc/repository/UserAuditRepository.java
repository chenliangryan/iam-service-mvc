package com.yugabyte.demo.iamservicemvc.repository;

import com.yugabyte.demo.iamservicemvc.domain.UserAudit;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuditRepository extends
  PagingAndSortingRepository<UserAudit, Long> {
  @Query("""
  SELECT u
  FROM UserAudit u
  WHERE u.action = 'login' AND u.userSvcAccount.userCredential.id = :userId
  ORDER BY u.transactionTime desc
  """)
  List<UserAudit> recentLogins(String userId, Pageable pageable);
}
