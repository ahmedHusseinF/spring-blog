package dev.fekry.demo.blog.dto;

public class CreatePostResponse {
  long id;
  String title;
  String text;

  public CreatePostResponse() {
  }

  public CreatePostResponse(long id, String title, String text) {
    this.id = id;
    this.title = title;
    this.text = text;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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
