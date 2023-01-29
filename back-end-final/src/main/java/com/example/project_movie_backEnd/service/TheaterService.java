package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository repository;


}
