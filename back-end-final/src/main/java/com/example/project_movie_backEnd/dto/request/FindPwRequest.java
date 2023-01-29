package com.example.project_movie_backEnd.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FindPwRequest {
	@NotBlank
  	private String names;


	private String email;

	private String username;

	private String phone;
}








