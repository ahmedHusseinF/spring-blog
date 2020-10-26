package dev.fekry.demo.blog.services;

import dev.fekry.demo.blog.dto.RegisterRequest;
import dev.fekry.demo.blog.entites.User;
import dev.fekry.demo.blog.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String username, String password) {
        User user = new User();
        user.setEmail(email).setUsername(username).setPassword(password);
        return this.userRepository.save(user);
    }

    public User updateUser(String loggedUsername, RegisterRequest body) {

        User user = this.userRepository.findUserByUsername(loggedUsername);

        if (body.getEmail().length() > 0) {
            user.setEmail(body.getEmail());
        }

        if (body.getUsername().length() > 0) {
            user.setUsername(body.getUsername());
        }

        if (body.getPassword().length() > 0) {
            user.setPassword(body.getPassword());
        }

        return this.userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = this.userRepository.findUserByUsername(username);
        this.userRepository.deleteById(user.getId());
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found in database");
        }
        return user;
    }
}
