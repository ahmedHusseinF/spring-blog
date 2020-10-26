package dev.fekry.demo.blog.dto;

public class Violation {

  private final String fieldName;

  private final String message;

  public Violation() {
    this.fieldName = this.message = "";
  }

  public Violation(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getMessage() {
    return message;
  }
}
