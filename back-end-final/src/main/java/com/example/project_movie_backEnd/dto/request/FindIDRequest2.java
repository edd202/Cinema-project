package com.example.project_movie_backEnd.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FindIDRequest2 {
	@NotBlank
  private String names;

	@NotBlank
	private String phone;
}








