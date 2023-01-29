package com.example.project_movie_backEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ProjectMovieBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMovieBackEndApplication.class, args);
	}

}
