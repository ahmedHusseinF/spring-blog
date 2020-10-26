package dev.fekry.demo.blog.entites;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotNull(message = "title is required")
    String title;

    String text;

    @OneToOne
    @NotNull(message = "post must have an author")
    User authorId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }
}
