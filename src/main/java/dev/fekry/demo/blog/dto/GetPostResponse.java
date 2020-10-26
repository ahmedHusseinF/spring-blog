package dev.fekry.demo.blog.dto;

public class GetPostResponse {
  long id;
  String title;
  String text;
  long authorId;
  String authorName;

  public GetPostResponse() {
  }

  public GetPostResponse(long id, String title, String text, long authorId, String authorName) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.authorId = authorId;
    this.authorName = authorName;
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

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
}
