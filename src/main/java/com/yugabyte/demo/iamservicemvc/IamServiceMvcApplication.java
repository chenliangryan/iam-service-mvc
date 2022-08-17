package com.yugabyte.demo.iamservicemvc;

import com.yugabyte.demo.iamservicemvc.domain.UserAudit;
import com.yugabyte.demo.iamservicemvc.domain.UserCredential;
import com.yugabyte.demo.iamservicemvc.domain.UserProfile;
import com.yugabyte.demo.iamservicemvc.domain.UserSvcAccount;
import com.yugabyte.demo.iamservicemvc.repository.UserAuditRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserCredentialsRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserProfileRepository;
import com.yugabyte.demo.iamservicemvc.repository.UserSvcAccountRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IamServiceMvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(IamServiceMvcApplication.class, args);
  }
}
