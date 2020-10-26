package dev.fekry.demo.blog.repos;

import dev.fekry.demo.blog.entites.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("select p from Post p where p.text like '%:text%' or p.title like '%:text%'")
    List<Post> searchInPosts(@Param("text") String text);

    @Query("select p from Post p where p.authorId = :authorId")
    List<Post> findAllByAuthorId(@Param("authorId") long authorId);
}
