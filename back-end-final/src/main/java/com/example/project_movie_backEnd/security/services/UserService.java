package com.example.project_movie_backEnd.security.services;

import com.example.project_movie_backEnd.model.User;
import com.example.project_movie_backEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.simplelogin.security.services
 * fileName : UserService
 * author : Chozy93
 * date : 22-12-22(022)
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 22-12-22(022)         Chozy93          최초 생성
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        Optional<User> userOpt= userRepository.findByEmail(email);
        return userOpt;
    }
}
