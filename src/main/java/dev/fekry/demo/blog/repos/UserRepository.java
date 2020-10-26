package dev.fekry.demo.blog.repos;

import dev.fekry.demo.blog.entites.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String email);

    @Query("select u from User u where u.username = ?1")
    User findUserByUsername(String username);
}
