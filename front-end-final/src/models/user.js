// 유저  클래스 정의
export default class User {
  constructor(username, names, phone,gender, birthDate, email, password,id,roles) {
    this.username = username;
    this.names = names;
    this.phone = phone;
    this.gender=gender
    this.birthDate = birthDate;
    this.email = email;
    this.password = password;
    this.id = id;
    this.roles = roles;
  }
}
