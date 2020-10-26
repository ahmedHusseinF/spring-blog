package dev.fekry.demo.blog.services;

import dev.fekry.demo.blog.dto.CreatePostRequest;
import dev.fekry.demo.blog.entites.Post;
import dev.fekry.demo.blog.entites.User;
import dev.fekry.demo.blog.repos.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
  private final PostRepository postRepository;
  private final UserService userService;

  PostService(PostRepository postRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userService = userService;
  }

  public List<Post> getAllPost() {
    List<Post> posts = new ArrayList<>();
    this.postRepository.findAll().forEach(posts::add);
    return posts;
  }

  public List<Post> getAllPostsOfAuthor(long authorId) {
    return this.postRepository.findAllByAuthorId(authorId);
  }

  public Post createPost(String loggedUsername, CreatePostRequest body) {
    User user = this.userService.getUserByUsername(loggedUsername);

    Post post = new Post();
    post.setTitle(body.getTitle());
    post.setText(body.getText());
    post.setAuthorId(user);

    return this.postRepository.save(post);
  }

  public Post editPost(long postId, String loggedUsername, CreatePostRequest body) {
    Post ownedPost = this.checkPostOwner(postId, loggedUsername);

    if (ownedPost == null) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Can't edit a non-owned post.");
    }

    if (body.getTitle().length() > 0) {
      ownedPost.setTitle(body.getTitle());
    }

    if (body.getTitle().length() > 0) {
      ownedPost.setTitle(body.getTitle());
    }

    return this.postRepository.save(ownedPost);
  }

  public void deletePost(long postId, String loggedUsername) {
    Post ownedPost = this.checkPostOwner(postId, loggedUsername);

    if(ownedPost == null) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Can't delete a non-owned post.");
    }

    this.postRepository.deleteById(postId);
  }

  public List<Post> searchInPosts(String query) {
    return this.postRepository.searchInPosts(query);
  }

  private Post checkPostOwner(long postId, String loggedUsername) {
    Optional<Post> optionalPost = this.postRepository.findById(postId);

    if (!optionalPost.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found");
    }

    Post post = optionalPost.get();
    User user = this.userService.getUserByUsername(loggedUsername);

    return post.getAuthorId().getId() == user.getId() ? post : null;
  }
}
