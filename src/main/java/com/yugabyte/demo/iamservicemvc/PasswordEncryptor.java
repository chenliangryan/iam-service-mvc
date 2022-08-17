package com.yugabyte.demo.iamservicemvc;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor implements Function<String, String> {

  private final String digestAlgorithm;

  public PasswordEncryptor() {
    this("SHA-256");
  }

  public PasswordEncryptor(String digestAlgorithm) {
    this.digestAlgorithm = digestAlgorithm;
  }

  @Override
  @SneakyThrows
  public String apply(String password) {
    byte[] hash = createHash(password);
    return toHex(hash);
  }



  private byte[] createHash(String password) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance(digestAlgorithm);
    return digest.digest(password.getBytes(UTF_8));
  }
  private static String toHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
