package dev.fekry.demo.blog.dto;

import javax.validation.constraints.NotEmpty;

public class CreatePostRequest {
  @NotEmpty(message = "title is required")
  String title;

  String text;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
