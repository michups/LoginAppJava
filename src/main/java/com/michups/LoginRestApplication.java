package com.michups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // <1>
public class LoginRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoginRestApplication.class, args); // <2>
  }

}
