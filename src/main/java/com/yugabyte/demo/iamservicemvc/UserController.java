package com.yugabyte.demo.iamservicemvc;

import com.yugabyte.demo.iamservicemvc.domain.UserProfile;
import com.yugabyte.demo.iamservicemvc.service.UserProfileVO;
import com.yugabyte.demo.iamservicemvc.service.UserService;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

  private final UserService userService;

  public UserController(UserService userService, Function<String, String> passwordEncryptor) {
    this.userService = userService;
  }

  @GetMapping("/user/auth")
  public UserProfileVO authenticate(@RequestParam String userId, @RequestParam String passwordHash){
    return userService.authenticate(userId, passwordHash);
  }

  @GetMapping("/user/profile")
  public UserProfileVO getUserProfileWithActivities(@RequestParam String userId){
    return userService.getProfileWithActivities(userId);
  }

}
