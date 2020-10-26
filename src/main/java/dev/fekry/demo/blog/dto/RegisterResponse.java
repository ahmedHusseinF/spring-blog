package dev.fekry.demo.blog.dto;

public class RegisterResponse {
  String username;
  String email;
  long id;

  public RegisterResponse() {
  }

  public RegisterResponse(String username, String email, long id) {
    this.username = username;
    this.email = email;
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
