// import axios from 'axios';
import http from '../../http-common'

// const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {

  login(user) {

    // user 객체의 정보를 임시 객체에 저장
    let userData = {
      username: user.username,
      password: user.password
    }

    return http
      // .post(API_URL + 'signin', userData)
      .post('/auth/signin', userData)
      .then(response => {
        if (response.data.accessToken) {
          // 서버에서 날려준 정보중에 JWT 이 있으면
          // 객체 데이터를 문자열로 바꾸어서 localStorage 저장
          localStorage.setItem('user', JSON.stringify(response.data));
        }
6
        return response.data;
      });
  }

  logout() {
    // localStorage 값을 삭제
    localStorage.removeItem('user');
  }

  nonUserLogout() {
    // localStorage 값을 삭제
    localStorage.removeItem('nonUser');
  }

  register(user) {

    let userData = {
      username: user.username,
      names:user.names,
      phone:user.phone,
      gender:user.gender,
      birthDate:user.birthDate,
      email: user.email,
      password: user.password
    }

    // return axios.post(API_URL + 'signup', userData);
    return http.post('/auth/signup', userData);
  }
    // 공용저장소 함수로 사용하지않지만 로그인 부분에 사용 하는 함수라 이곳에 등록
  idOverlapCheck(username){
    return http.get(`/auth/overlap/${username}`)
  }
}

export default new AuthService();
