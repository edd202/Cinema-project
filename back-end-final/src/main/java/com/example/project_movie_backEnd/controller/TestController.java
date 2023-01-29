package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.model.User;
import com.example.project_movie_backEnd.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  UserService userService;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
//  @PreAuthorize 로 권한체크함
//  아래 페이지는 USER, ADMIN 가능
//  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')or hasRole('ADMIN2')")
  public String userAccess() {


    return "User Content.";
  }

  @PostMapping("/user2")
//  @PreAuthorize 로 권한체크함
//  아래 페이지는 USER, ADMIN 가능
//  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('ADMIN2')")
  public Optional<User> getUserProfile(@RequestBody String email) {

    Optional<User> userOpt =  userService.findByEmail(email.replaceAll("\"",""));

    return userOpt;
  }

//  @GetMapping("/mod")
//  @PreAuthorize("hasRole('MODERATOR')")
//  public String moderatorAccess() {
//    return "Moderator Board.";
//  }

  //  아래 페이지는 ADMIN2(Master) 만 가능
  @GetMapping("/admin")
  @PreAuthorize("hasRole('MASTER')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
