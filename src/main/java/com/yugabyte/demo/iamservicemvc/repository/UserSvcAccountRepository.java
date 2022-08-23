package com.yugabyte.demo.iamservicemvc.repository;

import com.yugabyte.demo.iamservicemvc.domain.UserSvcAccount;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserSvcAccountRepository extends CrudRepository<UserSvcAccount, Long> {

  @Query("""
  SELECT u
  FROM UserSvcAccount u
  WHERE u.userCredential.id = :userId
  """)
  List<UserSvcAccount> findAllByUserCredential_Id(String userId);

}
