package com.example.project_movie_backEnd.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;


  @Size(max = 50)
  @Email
  private String email;


  @Size(max = 50)

  private String names;


  @Size(max = 50)
  private String phone;
  private String gender;


  private String birthDate;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


}
