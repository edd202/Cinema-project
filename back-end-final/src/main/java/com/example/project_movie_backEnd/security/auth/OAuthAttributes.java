package com.example.project_movie_backEnd.security.auth;

import com.example.project_movie_backEnd.model.ERole;
import com.example.project_movie_backEnd.model.Role;
import com.example.project_movie_backEnd.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * packageName : com.example.simplelogin.security.auth
 * fileName : OAuthAttributes
 * author : kangtaegyung
 * date : 2022/12/16
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/16         kangtaegyung          최초 생성
 */
@Slf4j
@Getter
public class OAuthAttributes {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;

    private String names;

    private String phone;

    private String gender;

    private String birthDate;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String username,
                           String names, String phone,String gender,String birthDate,
                           String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.names = names;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
    }

    //    registrationId 를 체크해서 구글 / 네이버 정보 가져오기
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        switch (registrationId) {
            case "google" :
                return ofGoogle(userNameAttributeName, attributes);
            case "naver" :
                return ofNaver(userNameAttributeName, attributes);
            default:
                return ofGoogle(userNameAttributeName, attributes);
        }
    }
//  구글 생성자
    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username(((String) attributes.get("email")).split("@")[0])
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
//    네이버 생성자
    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info((String) response.get("gender"));
        return OAuthAttributes.builder()
                .username(((String) response.get("email")).split("@")[0]+"@")
                .email((String) response.get("email")+"@")
                .names((String) response.get("name"))
                .phone(((String) response.get("mobile")).replaceAll("-","")+"@")
                .gender((String) response.get("gender"))
                .birthDate(((String) response.get("birthyear")+
                        response.get("birthday")).replaceAll("-",""))
                .email((String) response.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //    OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때
//    User 엔티티를 생성
    public User toEntity() {
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setRid(1);
        userRole.setName(ERole.ROLE_USER);

        roles.add(userRole); // 기본 User 롤 추가

        return User.builder()
                .username((String) this.email.split("@")[0]+"@")
                .names(this.names)
                .phone(this.phone.replaceAll("-",""))
                .gender(this.gender)
                .birthDate(this.birthDate.replaceAll("-",""))
                .email(this.email)
                .password(encoder.encode("123456"))
                .roles(roles)
                .build();
    }
}
