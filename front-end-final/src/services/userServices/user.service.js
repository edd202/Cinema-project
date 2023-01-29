// import axios from 'axios';
import http from '../../http-common'
import authHeader from './auth-header';

// const API_URL = 'http://localhost:8080/api/test/';

class UserService {
  getPublicContent() {
    // return axios.get(API_URL + 'all');
    return http.get('/test/all');
  }

  getUserBoard() {
    // axios.get(url, 헤더정보)
    // 헤더정보 : 토큰과 함께 요청
    // return axios.get(API_URL + 'user', { headers: authHeader() });
    return http.get('/test/user', { headers: authHeader() });
  }

  getUserProfile(username){
      return http.post('/user',username, { headers: authHeader() });
  }

  getUserProfile2(id){
    return http.post('/user/id',id, { headers: authHeader() });
}
  

  // getModeratorBoard() {
  //   return axios.get(API_URL + 'mod', { headers: authHeader() });
  // }

  getAdminBoard() {
    // axios.get(url, 헤더정보)
    // 헤더정보 : 토큰과 함께 요청
    // return axios.get(API_URL + 'admin', { headers: authHeader() });
    return http.get('/test/admin', { headers: authHeader() });
  }
// 정보수정함수
  update(id, user) {
    // 임시 객체
    let userData = {
        username : user.username,
        names : user.names,
        email    : user.email,
        phone    : user.phone,
        gender   : user.gender,
        birthDate: user.birthDate,
        password : user.password,
        role     : [user.roles[0].name]
    }
    console.log("보내는 데이터는:"+JSON.stringify(userData));
    return http.put(`/user/${id}`, userData, { headers: authHeader() } );
  }

  // 아이디찾기 함수들
  // 1 이메일로 찾기
  findID(names,email){
    let userData ={
      names: names,
      email: email
    }
    return http.post(`/user/findid`, userData, { headers: authHeader() } );
  }
// 2 폰번호로 찾기
  findID2(names,phone){
    let userData ={
      names: names,
      phone: phone
    }
    return http.post(`/user/findid2`, userData, { headers: authHeader() } );
  } 
  // 비밀번호찾기에 쓰일 유저 정보 가져오기
  // 이메일 찾기용
  getUser(username,names,email){
    let userData ={
      username: username,
      names: names,
      email: email
    }
    console.log(userData)
    return http.post('/user/findpw',userData, { headers: authHeader() });
}
// 비밀번호 찾기용
getUser2(username,names,phone){
    let userData ={
      username: username,
      names: names,
      phone: phone
    }
    console.log(userData)
    return http.post('/user/findpw',userData, { headers: authHeader() });
}

}

export default new UserService();
