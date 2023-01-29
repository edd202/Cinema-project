package com.example.project_movie_backEnd.dto.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String names;
  private String birthDate;
  private String phone;
  private String gender;
  private String email;
  private List<String> roles;

  public JwtResponse(
          String accessToken, Long id, String username,String names,String phone,String gender,
          String birthDate, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.names = names;
    this.phone = phone;
    this.gender = gender;
    this.birthDate = birthDate;
    this.email = email;
    this.roles = roles;
  }
  public JwtResponse(
          String accessToken, Long id, String username,
          String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public List<String> getRoles() {
    return roles;
  }
}
