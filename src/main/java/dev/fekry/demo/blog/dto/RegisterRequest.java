package dev.fekry.demo.blog.dto;

import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
  @NotEmpty(message = "email is required")
  String email;
  @NotEmpty(message = "password is required")
  String password;
  @NotEmpty(message = "username is required")
  String username;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
