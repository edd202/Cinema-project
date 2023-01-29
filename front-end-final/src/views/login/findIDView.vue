<template>
  <div>
    <nav-black></nav-black>
    <!-- 본문시작 -->
    <!-- 1.메뉴선택 -->
    <div class="tab-box">
      <nav class="navbar justify-content-center" style="border-top:1px solid lightgray">
        <ul class="nav nav-tabs" id="tabs">
          <li v-bind:class="navtab1">
            <a
              @click.prevent="
                (currentTab = 0),
                  (navtab1 = 'nav-item active'),
                  (navtab2 = 'nav-item'),
                  (navtab3 = 'nav-item')
              "
              >이메일 주소로 찾기</a
            >
          </li>

          <li v-bind:class="navtab2">
            <a
              @click.prevent="
                (currentTab = 1),
                  (navtab1 = 'nav-item'),
                  (navtab2 = 'nav-item active'),
                  (navtab3 = 'nav-item')
              "
              >휴대폰 번호로 찾기</a
            >
          </li>
        </ul>
      </nav>
      <!-- 메뉴선택끝 -->

      <!-- 1.이메일로 찾기 -->
      <div v-show="currentTab == 0" class="tab-continer member_login_wrap">
        <div>
          <div class="tab-continer">
            <div class="tab-content2">
              <div class="tab_con">
                
                <div class="contents_inner">
                  <div
                    style="
                      font-size: 15px !important;
                      text-align: center;
                      margin-bottom: 35px;
                      padding-bottom: 10px;
                      border-bottom: 1px solid black;
                      padding-left: 15px;" 
                    v-show="!fineduser"
                  >
                               가입 시 입력한 이름과 이메일을 정확하게 입력하셔야합니다.
                              </div>
                    <!-- 이름 -->
                  <dl class="join_input_box"  v-show="!findedID">
                    <dt class="title">
                      <label for="names" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        
                        type="text"
                        name="names"
                        id="names"
                        placeholder="이름"
                        v-model="names"
                      />
                    </dd>
                  </dl>
                  <!-- 이메일 -->
                  <dl class="join_input_box" v-show="!findedID">
                    <dt class="title">
                      <label for="Email" class="txt_star">이메일</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="email"
                        name="Email"
                        id="Email"
                        v-model="email"
                        placeholder="이메일(@포함)을 입력해주세요"
                        title="입력태그"
                        maxlength="50"
                      />
                    </dd>
                  </dl>
                  <dl class="join_input_box" style=" padding-bottom: 75px !important;"  v-show="!findedID">
                    <button class="btn_col3 ty6" style="float:right;" @click="findID()">아이디 찾기</button>
                  </dl>
                  <!-- 정보입력 끝 -->
                  <dl class="join_input_box" v-show="findedID">
                    <dt class="title">
                        <label for="userID" class="txt_star">ID</label>
                    </dt>
                    <dd class="inp_box" style="height:40px;">
                        <input
                        type="userID"
                        name="userID"
                        id="userID"
                        v-model="findedID"
                        style="height:40px; background-color: white;"
                        disabled
                      />
                    </dd>
                  </dl> 
                  <dl class="join_input_box" v-show="findedID">
                   
                    <dd class="inp_box">
                        <router-link to="/login">
                            <button class="btn_col3 ty6" style="float:right;">로그인 하러가기</button>
                        </router-link>
                     
                    </dd>
                     
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 이메일로 찾기 끝 -->
      <!-- 휴대폰으로 찾기 -->
      <div
        v-show="currentTab == 1"
        class="tab-continer member_login_wrap"
      >
      <div>
          <div class="tab-continer">
            <div class="tab-content2">
              <div class="tab_con">
                <div class="contents_inner">
                  <div style="font-size:15px !important;  text-align : center; margin-bottom: 35px; padding-bottom: 10px; border-bottom: 1px solid black; color: red;">
                               가입 시 입력한 이름과 휴대폰 번호를 정확하게 입력하셔야합니다.
                              </div>
                    <!-- 이름 -->
                  <dl class="join_input_box"  v-show="!findedID2">
                    <dt class="title">
                      <label for="names" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        
                        type="text"
                        name="names"
                        id="names"
                        placeholder="이름"
                        v-model="names2"
                      />
                    </dd>
                  </dl>
                  <!-- 휴대폰번호 -->
                  <dl class="join_input_box" v-show="!findedID2">
                    <dt class="title">
                      <label for="phone" class="txt_star">휴대폰 번호</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="phone"
                        id="phone"
                        v-model="phone"
                        placeholder="휴대폰 번호(-제외)를 모두 입력해주세요"
                        title="입력태그"
                        maxlength="50"
                      />
                    </dd>
                  </dl>
                  <dl class="join_input_box" style=" padding-bottom: 75px !important;"  v-show="!findedID2">
                    <button class="btn_col3 ty6" style="float:right;" @click="findIDphone()">아이디 찾기</button>
                  </dl>
                  <!-- 정보입력 끝 -->
                  <dl class="join_input_box" v-show="findedID2">
                    <dt class="title">
                        <label for="userID" class="txt_star">ID</label>
                    </dt>
                    <dd class="inp_box" style="height:40px;">
                        <input
                        type="userID"
                        name="userID"
                        id="userID"
                        v-model="findedID2"
                        style="height:40px; background-color: white;"
                        disabled
                      />
                    </dd>
                  </dl> 
                  <dl class="join_input_box" v-show="findedID2">
                   
                    <dd class="inp_box">
                        <router-link to="/login">
                            <button class="btn_col3 ty6" style="float:right;">로그인 하러가기</button>
                        </router-link>
                    </dd>
                     
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 휴대폰 번호로 찾기 끝 -->
    </div>

      <!-- 정보수정 끝 -->
    </div>
  </div>
</template>
  
  <script>
/* eslint-disable */
import NavBlack from "@/components/common/NavBlack.vue";

import User from "@/models/user";
import userService from "@/services/userServices/user.service";

export default {
  components: {
    NavBlack,
  },
  data() {
    return {
      // 탭변경 데이터
      currentTab: 0,
      navtab1: "nav-item active",
      navtab2: "nav-item",
      navtab3: "nav-item",
    //   아이디 찾기 용 데이터(이메일)
      findedID:"",
      names:"",
      email:"",
          //   아이디 찾기 용 데이터(폰)
    findedID2:"",
      names2:"",
      phone:""

    };
  },

  mounted() {
   
    
  },
  created() {
    if (this.loggedIn && this.loggedIn2) {
      this.$router.push("/");
    }
    // this.user = JSON.parse(localStorage.getItem("user"));
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    loggedIn2() {
      return this.$store.state.auth.initialState.status.loggedIn2;
    },
  },
  methods: {
    findID(){
        if(this.names&&this.email){
        userService.findID(this.names,this.email)
        .then((response)=>{
            if(response.data==""){
                alert("가입된 회원이 없습니다");
            }else{
                // console.log(response.data[0].username)
            this.findedID=response.data[0].username;
            }
           
        }).catch((e)=>{
            console.log(e)
        })
    }else{
            alert("이름과 이메일을 모두 입력해주세요.")
        }
    },
    findIDphone(){
        if(this.names2&&this.phone){
            userService.findID2(this.names2,this.phone)
        .then((response)=>{
            if(response.data==""){
                alert("가입된 회원이 없습니다");
            }else{
            // console.log(response.data[0].username)
           
            this.findedID2=response.data[0].username;
            
            }
        }).catch((e)=>{
            console.log(e)
        })
        }else{
            alert("이름과 휴대폰 번호를 모두 입력해주세요.")
        }
       
    }
  },
};
</script>
  
  
  <style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
a:link {
  color: black;
  text-decoration: none;
}

a:visited {
  color: black;
  text-decoration: none;
}

a:hover {
  color: black;
  text-decoration: none;
}

a:active {
  color: black;
  text-decoration: none;
}

ul.tabs {
  margin: 0px;
  padding: 0px;
  list-style: none;
}
/* ul.tabs li {
    background: none;
    color: #222;
    display: inline-block;
    padding: 10px 15px;
    cursor: pointer;
  } */

ul.tabs li.current {
  background: #ededed;
  color: #222;
}

.id_box{
  display: table-cell;
    vertical-align: middle;
  height: 40px;
  border-bottom: 1px solid rgb(201, 199, 199);
}

.nav-item {
  padding: 10px;
  width: 200px;
  text-align: center;
}

.tab-box {
  margin-top: 100px;
  /* margin-bottom: 150px; */
}

.nav-item.active {
  background: transparent !important;

  border-bottom: 2px solid rgb(0, 0, 0) !important;
  border-radius: 0px !important;
}

.content-right {
  text-align: right;
  margin: 15px 0px;
}

.tab-continer {
  /* background: #ededed; */
  margin: 0px auto;
  padding: 15px;
}

.tab-content-login {
  width: 500px;
  margin: 0px auto;
}
.tab-content2 {
  width: 550px;
  margin: 0px auto;
}
.tab-content3 {
  width: 300px;
  margin: 0px auto;
}
.login-btn {
  width: 300px;
  height: 70px;
}

.btn_naver_social {
  display: block;
  width: 100%;
  height: 45px;
}

.member_login_wrap .contents_inner {
  position: relative;
}
.member_login_wrap .ad_box {
  position: absolute;
  right: 0;
  top: 20px;
  width: 451px;
  height: 220px;
}
.member_login_wrap .login_box {
  position: relative;
  width: 470px;
  min-height: 220px;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
}
.member_login_wrap .login_box .tip {
  font-size: 15px;
}
.member_login_wrap .login_box .login_area {
  position: relative;
  margin-top: 21px;
  padding-right: 120px;
}
.member_login_wrap .login_box .login_area input {
  display: block;
  width: 100%;
  height: 45px;
}
.member_login_wrap .login_box .login_area input + input {
  margin-top: 10px;
}
.member_login_wrap .login_box .login_area .btn_login {
  position: absolute;
  right: 0;
  top: 0;
  width: 110px;
  height: 100px;
  color: #fff;
  font-size: 16px;
  border-radius: 4px;
  background-color: #ff243e;
}
.member_login_wrap .login_box .login_bot_wrap {
  margin: 15px 0 40px;
}
.member_login_wrap .login_box .login_bot_wrap:after {
  display: block;
  content: "";
  clear: both;
}
.member_login_wrap .login_box .login_bot_wrap .login_check {
  float: left;
}
.member_login_wrap .login_box .login_bot_wrap .login_check label {
  min-width: 88px;
}
.member_login_wrap .login_box .login_bot_wrap .login_menu {
  float: right;
  font-size: 0;
  line-height: 20px;
}
.member_login_wrap .login_box .login_bot_wrap .login_menu a {
  position: relative;
  margin-left: 20px;
  color: #666;
  font-size: 12px;
}
.member_login_wrap .login_box .login_bot_wrap .login_menu a:after {
  content: "";
  position: absolute;
  left: -11px;
  top: 50%;
  margin-top: -5px;
  width: 1px;
  height: 10px;
  background: #ddd;
}
.member_login_wrap .login_box .login_bot_wrap .login_menu a:first-child {
  margin-left: 0;
}
.member_login_wrap .login_box .login_bot_wrap .login_menu a:first-child:after {
  display: none;
}
.member_login_wrap .login_box .login_ad {
  text-align: right;
}
.member_login_wrap .login_box.social {
  padding-top: 76px;
}
.member_login_wrap .login_box.social .login_bot_wrap {
  text-align: center;
}
.member_login_wrap .login_box.social .login_bot_wrap .login_menu {
  float: none;
  padding-left: 56px;
}
.member_login_wrap .login_box .btn_naver_social,
.member_login_wrap .login_box .btn_cacao_social {
  display: block;
  position: relative;
  padding-left: 56px;
  width: 470px;
  height: 50px;
  line-height: 48px;
  font-size: 16px;
  text-align: center;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #fff;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
}

.btn_naver_social {
  display: block;
  position: relative;
  padding-left: 56px;
  width: 300px;
  height: 50px;
  line-height: 48px;
  font-size: 16px;
  text-align: center;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #fff;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
}

.member_login_wrap .login_box .btn_cacao_social::after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 56px;
  height: 50px;
  border-right: 1px solid #ddd;
}

.member_login_wrap .login_input_box .login_form_wrap {
  width: 470px;
  min-height: 220px;
  padding-top: 20px;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
}
.member_login_wrap .login_input_box .list_txt {
  margin-top: 33px;
  padding-left: 128px;
  color: #666;
}
.member_login_wrap .txtarea_box {
  margin: 30px 0 30px;
}
.member_login_wrap .txtarea_box .agreement_check_box {
  margin-top: 28px;
  text-align: right;
}
.member_login_wrap .btn_btm_wrap {
  margin-bottom: 0;
}

/* form */
.join_input_box {
  position: relative;
  padding-left: 100px;
}
.join_input_box .title {
  position: absolute;
  left: 0;
  top: 0;
  width: 90px;
  line-height: 40px;
  letter-spacing: -0.5px;
  font-size: 15px;
  text-align: right;
}
/* .join_input_box .inp_box {
    font-size: 0;
  } */
.join_input_box .inp_box input {
  width: 100%;
}
.join_input_box .inp_box > select {
  padding: 0 18px;
  width: 142px;
}
.join_input_box .inp_box > select:first-child {
  width: 235px;
}
.join_input_box .inp_box > select ~ select {
  margin-left: 10px;
}
.join_input_box ~ .join_input_box {
  margin-top: 5px;
}
/* ë¹„íšŒì› */
.txtarea {
  padding: 21px 32px;
  overflow-y: scroll;
  height: 133px;
  line-height: 1.6;
  font-size: 14px;
  border: 1px solid #ddd;
  background-color: #fff;
}

/*form*/
/* input, */
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}
input::-ms-clear {
  display: none;
}
/* input[type="text"]:disabled,
input[type="email"]:disabled,
input[type="text"].ty2:disabled,
input[type="text"].ty3:disabled {
  border-color: #f8f8f8;
  color: #666;
  background: #fffdfd;
} */

input[type="password"],
input[type="text"],
input[type="text"]:disabled,
input[type="email"],
input[type="number"],
input[type="tel"] {
  height: 40px;
  line-height: 40px;
  padding: 0 18px;
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  background-color: #fff;
  font-size: 14px;
  box-sizing: border-box;
  border: 1px solid #ddd;
}

input[type="password"].ty2,
input[type="text"].ty2,
input[type="number"].ty2,
input[type="tel"].ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
}
input[type="password"].ty3,
input[type="text"].ty3,
input[type="number"].ty3,
input[type="tel"].ty3 {
  background-color: #f8f8f8;
  border-color: transparent;
}
::-webkit-input-placeholder,
::-moz-placeholder,
:-ms-input-placeholder,
:-moz-placeholder {
  color: #666;
}
button {
  border: 1px solid #ddd;
  cursor: pointer;
  background-color: #fff;
}
button > * {
  position: relative;
} /* 클릭시 문자 흔들림 방지(ie) */
::-webkit-file-upload-button {
  cursor: pointer;
}
select {
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  border: 1px solid #ddd;

  background-repeat: no-repeat;
  background-position: right 6px top 50%;
  height: 40px;
  line-height: 40px;
  padding: 0 35px 0 18px;
  font-size: 14px;
  box-sizing: border-box;
}
select.ty2 {
  background-color: #f8f8f8;
}

select.ty4 {
  background-color: #f8f8f8;
}
select::-ms-expand {
  display: none;
}
select:disabled {
  background-color: #efefef;
  color: #666;
}
textarea {
  width: 100%;
  line-height: 1.5;
  box-sizing: border-box;
  padding: 13px 18px;
  border: 1px solid #ddd;
  resize: none;
  font-size: 14px;
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
}
textarea:disabled {
  background: #efefef;
  color: #666;
}
textarea.ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
}
textarea.ty3 {
  background-color: #f8f8f8;
  border-color: transparent;
}
input[type="radio"],
input[type="checkbox"] {
  accent-color: black;
  padding: 0;
  margin: 5px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  vertical-align: middle;
  border: none 0;
  color: #000000;
}
input[type="radio"] + label,
input[type="checkbox"] + label {
  position: relative;
  display: inline-block;
  margin-right: 20px;
  vertical-align: middle;
}
input[type="radio"] + label:last-of-type,
input[type="checkbox"] + label:last-of-type {
  margin-right: 0px;
}
input[type="radio"]:focus + label,
input[type="checkbox"]:focus + label {
  outline: #666 dotted 1px;
}
/* input[type="radio"] + label:before {
    content: "";
    display: inline-block;
    width: 18px;
    height: 18px;
    margin-right: 5px;
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: -5px;
  } */
/* input[type="checkbox"] + label:before {
    content: "";
    display: inline-block;
    border: 1px solid #ddd;
    background-color: #fff;
    width: 18px;
    height: 18px;
    margin-right: 5px;
    vertical-align: -5px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    -moz-border-radius: 3px;
    -ms-border-radius: 3px;
    -o-border-radius: 3px;
  } */
input[type="checkbox"] + label.disabled:before {
  display: none;
}
/* 2020-01-06, eskang fixed */
input[type="checkbox"] + label.ty2.disabled:before {
  border-color: #bebebe;
  pointer-events: none;
  cursor: default;
}

/* button ================================================== */
/* base ========== */
.btn_col1 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}
.btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}
.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #000000;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ffffff;
}
.btn_col4 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #dddddd;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ffffff;
}
.btn_col5 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #333333;
  font-size: 14px;
  color: #eed4a9 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #333333;
}
.btn_col6 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #000000;
  font-size: 14px;
  color: #eed4a9 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #000000;
}
.btn_col7 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #0eafee;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #0eafee;
}
.btn_col8 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #000000;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: transparent;
}
.btn_col9 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #707070;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: transparent;
}
.btn_col10 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #707070;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #000000;
}
/* size */
.btn_col1.ty1,
.btn_col2.ty1,
.btn_col3.ty1,
.btn_col4.ty1,
.btn_col5.ty1,
.btn_col6.ty1,
.btn_col7.ty1,
.btn_col8.ty1,
.btn_col9.ty1,
.btn_col10.ty1 {
  height: 18px;
  line-height: 16px;
  padding: 0 12px;
}
.btn_col1.ty2,
.btn_col2.ty2,
.btn_col3.ty2,
.btn_col4.ty2,
.btn_col5.ty2,
.btn_col6.ty2,
.btn_col7.ty2,
.btn_col8.ty2,
.btn_col9.ty2,
.btn_col10.ty2 {
  height: 25px;
  line-height: 23px;
  padding: 0 12px;
}
.btn_col1.ty3,
.btn_col2.ty3,
.btn_col3.ty3,
.btn_col4.ty3,
.btn_col5.ty3,
.btn_col6.ty3,
.btn_col7.ty3,
.btn_col8.ty3,
.btn_col9.ty3,
.btn_col10.ty3 {
  height: 28px;
  line-height: 26px;
  padding: 0 14px;
}
.btn_col1.ty4,
.btn_col2.ty4,
.btn_col3.ty4,
.btn_col4.ty4,
.btn_col5.ty4,
.btn_col6.ty4,
.btn_col7.ty4,
.btn_col8.ty4,
.btn_col9.ty4,
.btn_col10.ty4 {
  height: 33px;
  line-height: 31px;
  padding: 0 14px;
}
.btn_col1.ty5,
.btn_col2.ty5,
.btn_col3.ty5,
.btn_col4.ty5,
.btn_col5.ty5,
.btn_col6.ty5,
.btn_col7.ty5,
.btn_col8.ty5,
.btn_col9.ty5,
.btn_col10.ty5 {
  height: 40px;
  line-height: 38px;
  padding: 0 18px;
}
.btn_col1.ty6,
.btn_col2.ty6,
.btn_col3.ty6,
.btn_col4.ty6,
.btn_col5.ty6,
.btn_col6.ty6,
.btn_col7.ty6,
.btn_col8.ty6,
.btn_col9.ty6,
.btn_col10.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_col1.ty7,
.btn_col2.ty7,
.btn_col3.ty7,
.btn_col4.ty7,
.btn_col5.ty7,
.btn_col6.ty7,
.btn_col7.ty7,
.btn_col8.ty7,
.btn_col9.ty7,
.btn_col10.ty7 {
  height: 52px;
  line-height: 50px;
  padding: 0 24px;
}
/* round */
.btn_col1.rnd,
.btn_col2.rnd,
.btn_col3.rnd,
.btn_col4.rnd,
.btn_col5.rnd,
.btn_col6.rnd,
.btn_col7.rnd,
.btn_col8.rnd,
.btn_col9.rnd,
.btn_col10.rnd {
  border-radius: 52px;
}
/* disabled */
.btn_col1.disabled,
.btn_col2.disabled,
.btn_col3.disabled,
.btn_col4.disabled,
.btn_col5.disabled,
.btn_col6.disabled,
.btn_col7.disabled,
.btn_col8.disabled,
.btn_col9.disabled,
.btn_col10.disabled {
  border-color: #adadad;
  color: #ffffff !important;
  cursor: default;
  pointer-events: none;
  background-color: #adadad;
}
/* */
.btn_col1.disabled.active,
.btn_col2.disabled.active,
.btn_col3.disabled.active,
.btn_col4.disabled.active,
.btn_col5.disabled.active,
.btn_col6.disabled.active,
.btn_col7.disabled.active,
.btn_col8.disabled.active,
.btn_col9.disabled.active,
.btn_col10.disabled.active {
  cursor: pointer;
  pointer-events: inherit;
}
/* +아이콘 */
</style>