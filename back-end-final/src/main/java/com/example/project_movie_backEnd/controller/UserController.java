package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.dto.request.FindIDRequest;
import com.example.project_movie_backEnd.dto.request.FindIDRequest2;
import com.example.project_movie_backEnd.dto.request.FindPwRequest;
import com.example.project_movie_backEnd.dto.request.SignupRequest;
import com.example.project_movie_backEnd.dto.response.MessageResponse;
import com.example.project_movie_backEnd.model.ERole;
import com.example.project_movie_backEnd.model.Role;
import com.example.project_movie_backEnd.model.User;
import com.example.project_movie_backEnd.repository.RoleRepository;
import com.example.project_movie_backEnd.repository.UserRepository;
import com.example.project_movie_backEnd.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * packageName : com.example.project_movie_backEnd.controller
 * fileName : UserController
 * author : Chozy93
 * date : 22-12-28(028)
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 22-12-28(028)         Chozy93          최초 생성
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager; // 인증/권한체크 처리를 위한 객체

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    // 유저 정보 가져오기 함수
    @PostMapping("/user")
    public ResponseEntity<Object> getUserId(@RequestBody String username) {

        try {
            Optional<User> optionalUser = userRepository.findByUsername((username).replaceAll("\"", ""));

            if (optionalUser.isPresent() == true) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  아이디 찾기 함수
    @PostMapping("/user/findid")
    public ResponseEntity<Object> findUserId(@RequestBody FindIDRequest findIDRequest) {
        String names = findIDRequest.getNames();
        String email = findIDRequest.getEmail();

        try {
            List<User> listUser = userRepository.findAllByNamesAndEmail(names, email);
            log.info("결과값"+listUser.toString());
            if (!listUser.isEmpty()) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(listUser, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 폰번호로 찾기
    @PostMapping("/user/findid2")
    public ResponseEntity<Object> findUserId2(@RequestBody FindIDRequest2 findIDRequest2) {
        String names = findIDRequest2.getNames();
        String phone = findIDRequest2.getPhone();

        try {
            List<User> listUser = userRepository.findAllByNamesAndPhone(names, phone);

            if (!listUser.isEmpty()) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(listUser, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 비밀번호 찾기 함수
//
    @PostMapping("/user/findpw")
    public ResponseEntity<Object> getUser(@RequestBody FindPwRequest findPwRequest) {
        log.info("아이디:"+findPwRequest.getUsername()+
                        "이름:"+findPwRequest.getNames() +
                        "이메일:"+findPwRequest.getEmail()+
                        "폰:"+findPwRequest.getPhone());
        try {
            if(findPwRequest.getEmail() == null){
                Optional<User> optionalUser =
                        userRepository.findByUsernameAndNamesAndPhone(
                                findPwRequest.getUsername(),
                                findPwRequest.getNames(),
                                findPwRequest.getPhone());
                if (optionalUser.isPresent()) {
//                데이터 + 성공 메세지 전송
                    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
                } else {
//                데이터 없음 메세지 전송(클라이언트)
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }else{
                Optional<User> optionalUser =
                        userRepository.findByUsernameAndNamesAndEmail(
                                findPwRequest.getUsername(),
                                findPwRequest.getNames(),
                                findPwRequest.getEmail());
                if (optionalUser.isPresent()) {
//                데이터 + 성공 메세지 전송
                    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
                } else {
//                데이터 없음 메세지 전송(클라이언트)
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //유저 본인 정보 수정 함수
    @PutMapping("/user/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MASTER')")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody SignupRequest signupRequest) {
//
        log.debug("signupRequest {}", signupRequest);
        String password = "";


        if (signupRequest.getPassword() == null) {
            Optional<User> savedUser = userRepository.findById(id);
            if (savedUser.isPresent()) {
                password = savedUser.get().getPassword();
            }
        } else {
            password = encoder.encode(signupRequest.getPassword());
        }
        log.info("비밀번호는" + password);

//       저장
        User user = new User(
                id,
                signupRequest.getUsername(),
                signupRequest.getNames(),
                signupRequest.getPhone(),
                signupRequest.getGender(),
                signupRequest.getBirthDate(),
                signupRequest.getEmail(),
                password);
        log.info("롤" + signupRequest.getRole());
        Set<String> strRoles = signupRequest.getRole(); // Vue 전송한 권한(role) 적용
        Set<Role> roles = new HashSet<>(); // return 시 사용할 role 정보(Vue 전송됨)

//        Vue에서 요청한 데이터에 role 정보가 없으면
//        기본(default)으로 ROLE_USER 로 생성함 ( User 권한을 가진 회원이 생성됨 )
//        strRoles(Vue 전송한 roles) 이 없으면
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
//            Vue 에서 전송한 데이터에 role = "admin" 이면 ROLE_ADMIN 저장해서 전송
//                                       = ... 이면 ... 저장해서 전송
//                                       = 모두아니면 ROLE_USER 저장해서 전송
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

//        Vue에서 요청한 데이터에 role 정보가 없으면
//        기본(default)으로 ROLE_USER 로 생성함 ( User 권한을 가진 회원이 생성됨 )
//        strRoles(Vue 전송한 roles) 이 없으면


        user.setRoles(roles);      // USER 객체에 role 넣어주기(ROLE_USER)

        userRepository.save(user);

//        Vue 에 성공메세지 전송
        return ResponseEntity.ok(new MessageResponse("정보가 성공적으로 수정 되었습니다."));
    }

    @PostMapping("/user/id")
    public ResponseEntity<Object> getUserInfo(@RequestBody Long id) {

        try {
            Optional<User> optionalUser = userRepository.findById((id));

            if (optionalUser.isPresent() == true) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
