package dev.fekry.demo.blog.controllers;

import dev.fekry.demo.blog.annotations.LogExecutionTime;
import dev.fekry.demo.blog.dto.AuthRequest;
import dev.fekry.demo.blog.dto.AuthResponse;
import dev.fekry.demo.blog.dto.RegisterRequest;
import dev.fekry.demo.blog.dto.RegisterResponse;
import dev.fekry.demo.blog.entites.User;
import dev.fekry.demo.blog.services.JwtUtilService;
import dev.fekry.demo.blog.services.MyUserDetailsService;
import dev.fekry.demo.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final UserService userService;

    UserController(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.userService = userService;
    }


    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest body) throws Exception {
        String username = body.getUsername();
        String password = body.getPassword();

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }catch (BadCredentialsException ex) {
            throw new Exception("Incorrect Credentials");
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        final String jwt = JwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest body) {
        User user = this.userService.createUser(
                body.getEmail(),
                body.getUsername(),
                body.getPassword()
        );
        return new ResponseEntity<>(new RegisterResponse(user.getUsername(), user.getEmail(), user.getId()),HttpStatus.CREATED);
    }

    @PatchMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody RegisterRequest body, Authentication authentication) {
        User user = this.userService.updateUser(authentication.getName(), body);
        return new ResponseEntity<>(new RegisterResponse(user.getUsername(), user.getEmail(), user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        this.userService.deleteUser(authentication.getName());
        return ResponseEntity.ok().build();
    }
}
