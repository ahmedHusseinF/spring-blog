package dev.fekry.demo.blog.controllers;

import dev.fekry.demo.blog.dto.CreatePostRequest;
import dev.fekry.demo.blog.dto.CreatePostResponse;
import dev.fekry.demo.blog.dto.GetPostResponse;
import dev.fekry.demo.blog.entites.Post;
import dev.fekry.demo.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
  PostService postService;

  PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/post")
  public ResponseEntity<?> createPost(@RequestBody CreatePostRequest body, Authentication authentication) {
    Post post = this.postService.createPost(authentication.getName(), body);
    return new ResponseEntity<>(new CreatePostResponse(post.getId(), post.getTitle(), post.getText()), HttpStatus.CREATED);
  }

  @GetMapping("/post")
  public ResponseEntity<?> getAllPosts() {
    List<Post> posts = this.postService.getAllPost();

    List<GetPostResponse> apiPosts = posts.stream().map(post -> {
      return new GetPostResponse(post.getId(), post.getTitle(), post.getText(), post.getAuthorId().getId(), post.getAuthorId().getUsername());
    }).collect(Collectors.toList());

    return ResponseEntity.ok(apiPosts);
  }

  @GetMapping("/post/user/{id}")
  public ResponseEntity<?> getAllPostsOfUser(@PathVariable("id") long id) {
    List<Post> posts = this.postService.getAllPostsOfAuthor(id);

    List<GetPostResponse> apiPosts = posts.stream().map(post -> {
      return new GetPostResponse(post.getId(), post.getTitle(), post.getText(), post.getAuthorId().getId(), post.getAuthorId().getUsername());
    }).collect(Collectors.toList());

    return ResponseEntity.ok(apiPosts);
  }

  @PatchMapping("/post/{id}")
  public ResponseEntity<?> updatePost(@PathVariable("id") long id, @RequestBody CreatePostRequest body, Authentication authentication) {
    Post post = this.postService.editPost(id, authentication.getName(), body);
    return ResponseEntity.ok(new CreatePostResponse(post.getId(), post.getTitle(), post.getText()));
  }

  @DeleteMapping("/post/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") long id, Authentication authentication) {
    this.postService.deletePost(id, authentication.getName());
    return ResponseEntity.ok().build();
  }
}
