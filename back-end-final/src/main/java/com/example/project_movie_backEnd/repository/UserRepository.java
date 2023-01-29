package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.userCountDto;
import com.example.project_movie_backEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  List<User> findAllByUsernameContaining(String username);

  //  중복확인
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);


  Boolean existsByPhone(String phone);
  //  이메일로 아이디 찾기
  List<User> findAllByNamesAndEmail(String names,String email);

  //  폰번호로 아이디 찾기
  List<User> findAllByNamesAndPhone(String names,String phone);

  //  비밀번호 이메일로 찾기
  Optional<User> findByUsernameAndNamesAndEmail(String username,String names,String email);
  Optional<User> findByUsernameAndNamesAndPhone(String username,String names,String phone);
  //  oauth2 에서 사용할 함수
//  소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 함수
  Optional<User> findByEmail(String email);
  Optional<User> findByPhone(String email);

  Optional<User> findById(Long id);


  @Query(value = "select count(*) as userCount " +
          "from tb_user "
         ,
          nativeQuery = true)
  List<userCountDto> userCount();
}








