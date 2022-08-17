package com.yugabyte.demo.iamservicemvc.repository;

import com.yugabyte.demo.iamservicemvc.domain.UserCredential;
import java.util.Optional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredential, String> {

  @Modifying
  @Query("""
    UPDATE UserCredential
      SET passwordHash = :newPasswordHash
    WHERE
      id = :userId AND passwordHash = :oldPasswordHash
    """)
  Boolean updatePasswordHash(String userId, String oldPasswordHash, String newPasswordHash);

  UserCredential getByIdAndPasswordHash(String userIs, String passswordHash);

}
