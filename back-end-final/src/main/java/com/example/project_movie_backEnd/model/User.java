package com.example.project_movie_backEnd.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(
        name = "SQ_USER_GENERATOR"
        , sequenceName = "SQ_USER"
        , initialValue = 1
        , allocationSize = 1
)
@Table(name = "TB_USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE
          , generator = "SQ_USER_GENERATOR"
  )
  private Long id;

  //  로그인 ID 임
  @NotBlank
  @Size(max = 20)
  private String username;


  private String names;
  private String gender;

  private String birthDate;


  private String phone;


  @Size(max = 50)
  @Email
  private String email;


  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "TB_USER_ROLE",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username,String names,String phone,String gender, String birthDate,String email, String password) {
    this.username = username;
    this.names = names;
    this.phone = phone;
    this.gender = gender;
    this.birthDate = birthDate;
    this.email = email;
    this.password = password;
  }

  public User(long id, String username,String names,String phone,String gender, String birthDate,String email, String password) {
    this.id=id;
    this.username = username;
    this.names = names;
    this.phone = phone;
    this.gender = gender;
    this.birthDate = birthDate;
    this.email = email;
    this.password = password;
  }

  @Builder
  public User(String username,String names,String phone,String gender,String birthDate,
              String email, String password, Set<Role> roles) {
    this.username = username;
    this.names = names;
    this.phone = phone;
    this.gender=gender;
    this.birthDate = birthDate;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public User update(String name) {
    this.username = username;

    return this;
  }
}
