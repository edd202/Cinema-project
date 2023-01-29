<template>
  <div>
    <NavBlack></NavBlack>
    <!-- 본문시작 -->
    <!-- 1.메뉴선택 -->
    <div class="tab-box">
      <nav class="navbar bg-light justify-content-center" > 
        <ul class="nav nav-tabs" id="tabs" style="background-color:#F8F9FA !important;">
          <li v-bind:class="navtab1">
            <a
              @click.prevent="
                (currentTab = 0),
                  (navtab1 = 'nav-item active'),
                  (navtab2 = 'nav-item'),
                  (navtab3 = 'nav-item')
              "
              >예매내역</a
            >
          </li>

          <li v-bind:class="navtab2">
            <a
              @click.prevent="
                (currentTab = 1),
                  (navtab1 = 'nav-item'),
                  (navtab2 = 'nav-item active'),
                  (navtab3 = 'nav-item'),
                  getUser(userID)
              "
              >정보수정</a
            >
          </li>
          <li v-bind:class="navtab3">
            <a
              @click.prevent="
                (currentTab = 2),
                  (navtab1 = 'nav-item'),
                  (navtab2 = 'nav-item'),
                  (navtab3 = 'nav-item active'),
                  getUser(userID)
              "
              >비밀번호 변경</a
            >
          </li>
        </ul>
      </nav>
      <!-- 메뉴선택끝 -->

      <!-- 1.예매내역 -->
      <div
        v-show="currentTab == 0"
        class="tab-continer member_login_wrap justify-content-center"
      >
        <div v-for="(data, index) in ticket" :key="index" class="ticket-box">
          <div class="cc_movie_info">
            <table class="tb_acc_wrap">
              <colgroup>
                <col style="width: 15%" />
                <col style="width: auto" />
              </colgroup>
              <tbody>
                <tr>
                  <th>제목</th>
                  <td class="left-td">
                    <div class="font">{{ data.tkTitle }}</div>
                  </td>
                  <th>일시</th>
                  <td class="right-td">
                    <div>{{ data.tkStartTime.slice(0,-8) }}</div>
                    <div class="font">시작시간 : {{ data.tkStartTime.substring(12,data.tkStartTime.length) }}</div>
                    <div class="font">종료시간 : {{ data.tkEndTime }}</div>
                  </td>
                </tr>
                <!-- <tr>
               
              </tr> -->
                <tr>
                  <th>상영관</th>
                  <td class="left-td">
                    <div class="font">{{ data.tkTheater }}</div>
                  </td>
                  <th>좌석</th>
                  <td class="right-td">
                    <div class="font">
                      {{ data.tkSeat.replaceAll("석 ", "석,").slice(0, -2) }}
                    </div>
                  </td>
                </tr>
                <!-- <tr>

              </tr> -->
              </tbody>
            </table>
            <div class="cc_pay">
              <span class="cc_pay_tit">결제금액</span>
              <span class="cc_pay_amount">{{ data.tkPrice }}원</span>
              <span class="btn_btm_wrap">
                <a class="btn_col3 ty6" @click="cancelTicket(data.tkId)"
                  >예매취소</a
                >
              </span>
            </div>
          </div>
        </div>
        <div style="text-align:center" v-if="!ticket">
          <h1>예매한 내역이 없습니다!</h1>
        </div>
      </div>
      <!-- 예매내역끝 -->
      <!-- 정보수정 -->
      <div v-show="currentTab == 1" class="tab-continer member_login_wrap">
        <div class="tab-box">
          <div class="tab-continer">
            <div class="tab-content2">
              <div class="tab_con">
                <div class="contents_inner">
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userID" class="txt_star">ID</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        class="id_box"
                        v-model="user.username"
                        disabled
                        name="userID"
                        id="userID"
                      />
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userName" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="userName"
                        id="userName"
                        v-model="user.names"
                        placeholder="이름을 입력해 주세요"
                        title="입력태그"
                        @keyup="nameValidate()"
                      />
                    </dd>
                    <span style="font-size: 12px; color: red">{{
                      nameCheck
                    }}</span>
                  </dl>
                  <!-- 폰번호 -->
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPhone" class="txt_star"
                        >휴대폰 번호</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="userPhone"
                        id="userPhone"
                        v-model="user.phone"
                        placeholder="휴대폰 번호(- 생략)를 입력해 주세요."
                        title="입력태그"
                        maxlength="11"
                        minlength="10"
                        @keyup="phoneValidate()"
                        @blur="phoneValidate2()"
                      />
                      <span style="font-size: 12px; color: red">
                        {{ phoneCheck }}
                      </span>
                      <span style="font-size: 12px; color: red">
                        {{ phoneCheckNum }}
                      </span>
                    </dd>
                  </dl>
                  <!-- 이메일 -->
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="Email" class="txt_star">이메일</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="email"
                        name="Email"
                        id="Email"
                        v-model="user.email"
                        placeholder="이메일(@포함)을 입력해주세요"
                        title="입력태그"
                        maxlength="50"
                        @blur="emailValidate()"
                      />
                      <!-- v-validate="'email'" -->
                      <span style="font-size: 12px; color: red">
                        {{ emailCheck }}
                      </span>
                    </dd>
                  </dl>
                  <!-- 성별 -->
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="gender" class="txt_star">성별</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="gender"
                        id="gender"
                        v-model="genderName"
                        disabled
                      />
                    </dd>
                  </dl>
                  <!-- 생년월일 -->
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="birthdate" class="txt_star">생년월일</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="birthdate"
                        id="birthdate"
                        v-model="user.birthDate"
                        disabled
                      />
                    </dd>
                  </dl>
                  <dl class="join_input_box" style="float: right">
                    <button
                      class="btn_col3 ty6"
                      title="정보수정"
                      @click="userUpdate()"
                    >
                      정보수정
                    </button>
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-show="currentTab == 2" class="tab-continer member_login_wrap">
        <div class="tab-box">
          <div class="tab-continer">
            <div class="tab-content2">
              <div class="tab_con">
                <div class="contents_inner">
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPassword2" class="txt_star"
                        >비밀번호</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="password"
                        name="userPassword2"
                        id="userPassword2"
                        v-model="changedPassword"
                        maxlength="20"
                        minlength="8"
                        placeholder="비밀번호 (8~20자리)를 입력해 주세요."
                        title="입력태그"
                        aria-autocomplete="list"
                        @keyup="passwordValidate()"
                      />
                    </dd>
                    <span style="font-size: 12px; color: red">{{
                      passwordCheck
                    }}</span>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPassword3" class="txt_star"
                        >비밀번호 확인</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="password"
                        name="userPassword3"
                        id="userPassword3"
                        maxlength="20"
                        minlength="8"
                        v-model="passwordChecker"
                        placeholder="비밀번호 (8~20자리)를 재입력해 주세요."
                        title="입력태그"
                        @keyup="passwordValidate2()"
                        @blur="passwordValidate2()"
                      />
                      <span style="font-size: 12px; color: red">{{
                        passwordCheck2
                      }}</span>
                    </dd>
                  </dl>
                  <dl class="join_input_box" style="float: right">
                    <button
                      class="btn_col3 ty6"
                      title="비밀번호 변경"
                      @click="passowrdUpdate()"
                    >
                      비밀번호 변경
                    </button>
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
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
import ReservationCheckService from "@/services/ReservationCheckService";

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
      //   정보수정 데이터
      userID: "",
      // 정보수정에 담아보낼 유저 양식
      user: new User("", "", "", "", "", "", "", "", ""),
      message: "",
      IDPass: false,
      IDCheck: "",
      namePass: true,
      nameCheck: "",
      phonePass: true,
      phoneCheck: "",
      phoneCheckNum: "",
      emailPass: true,
      emailCheck: "",
      passwordPass: false,
      passwordPass2: false,
      passwordChecker: "",
      passwordCheck:
        "*8~16자의 영문 대,소문자와, 숫자 또는 특수문자의 조합으로 이루어져야합니다.",
      passwordCheck2: "",
      changedPassword: "",
      // 예매내역 가져오기 데이터
      ticket: [],
    };
  },

  mounted() {
      console.log(
      "회원로그인여부:" + this.loggedIn + "비회원로그인여부:" + this.loggedIn2
    );
    if (!this.loggedIn && !this.loggedIn2) {
      this.$router.push("/");
    }
    if (this.loggedIn2) {
      this.$router.push("/");
    }
    if (JSON.parse(localStorage.getItem("user"))) {
      if (JSON.parse(localStorage.getItem("user")).username.includes("@")) {
        this.$router.push("/mypage3");
      }
    }
    if (this.loggedIn) {
      this.userID = JSON.parse(localStorage.getItem("user")).username;
      this.getMemTicketList(JSON.parse(localStorage.getItem("user")).id);
    }
    console.log("유저아이디는" + this.userID);
    this.getUser(this.userID);
  },

  created() {
  

    // this.user = JSON.parse(localStorage.getItem("user"));
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    loggedIn2() {
      return this.$store.state.auth.initialState2.status.loggedIn2;
    },
    genderName() {
      if (this.user.gender == "M") {
        return "남자";
      } else {
        return "여자";
      }
    },
  },
  methods: {
    getMemTicketList(Id) {
      ReservationCheckService.getTicketListMem(Id)
        .then((response) => {
          console.log("티켓" + response.data);
          this.ticket = response.data;
          console.log(this.ticket[0]);
          // this.price = this.ticket[0].tkPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
          // let day = this.ticket[0].tkStartTime.split("일");
          // this.startDate=day[0];
          // console.log("일"+day[0])
          // console.log("시간"+day[1])
          // this.startTime=day[1];
          // this.seatNum = this.ticket[0].tkSeat.split("좌석");
          // this.poster = response.data.PosterUri;
          // this.ticketId=this.ticket[0].tkId;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    nameValidate() {
      if (this.user.names) {
        return (this.namePass = true), (this.nameCheck = "");
      } else {
        return (
          (this.namePass = false), (this.nameCheck = "*이름을 입력해주세요.")
        );
      }
    },
    phoneValidate() {
      if (isNaN(this.user.phone)) {
        return (
          (this.phoneCheck = "*휴대폰 번호는 숫자만 입력해주세요"),
          (this.phonePass = false),
          console.log(this.phonePass)
        );
      } else {
        return (this.phoneCheck = ""), (this.phonePass = true);
      }
    },

    phoneValidate2() {
      if (isNaN(this.user.phone) || this.user.phone.length < 10) {
        return (
          (this.phoneCheck = "*유효한 휴대폰 번호를 입력해주세요"),
          (this.phonePass = false)
        );
      } else {
        return (this.phoneCheck = ""), (this.phonePass = true);
      }
    },
    emailValidate() {
      var reg_email =
        /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      if (!reg_email.test(this.user.email)) {
        return (
          (this.emailPass = false),
          (this.emailCheck = "*올바른 이메일 형식이 아닙니다.")
        );
      } else {
        return (this.emailPass = true), (this.emailCheck = "");
      }
    },
    // 비밀번호 검증 1차
    passwordValidate() {
      var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/;
      var kr_pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
      var num_pattern = /[0-9]/;
      var en_pattern = /[a-z|A-Z]/;
      // console.log("비밀번호?"+this.changedPassword)
      // console.log("특수문자포함?"+special_pattern.test(this.changedPassword))
      // console.log("숫자포함?"+num_pattern.test(this.changedPassword))
      // console.log("한글포함?"+kr_pattern.test(this.changedPassword))
      // console.log("영어포함?"+en_pattern.test(this.changedPassword))
      // console.log("길이?"+this.changedPassword.length)
      if (
        special_pattern.test(this.changedPassword) ||
        num_pattern.test(this.changedPassword)
      ) {
        if (
          kr_pattern.test(this.changedPassword) == false &&
          en_pattern.test(this.changedPassword)
        ) {
          if (
            this.changedPassword.length >= 8 &&
            this.changedPassword.length < 21
          ) {
            return (
              (this.passwordCheck = "*사용가능한 비밀번호입니다!"),
              (this.passwordPass = true)
            );
          } else {
            return (
              (this.passwordCheck =
                "*8~16자의 영문 대,소문자와, 숫자 또는 특수문자의 조합으로 이루어져야합니다."),
              (this.passwordPass = false)
            );
          }
        } else {
          return (
            (this.passwordCheck =
              "*8~16자의 영문 대,소문자와, 숫자 또는 특수문자의 조합으로 이루어져야합니다."),
            (this.passwordPass = false)
          );
        }
      } else {
        return (
          (this.passwordCheck =
            "*8~16자의 영문 대,소문자와, 숫자 또는 특수문자의 조합으로 이루어져야합니다."),
          (this.passwordPass = false)
        );
      }
    },
    // 비밀번호 검증 2차
    passwordValidate2() {
      if (
        this.changedPassword === this.passwordChecker &&
        this.passwordChecker
      ) {
        return (
          (this.passwordCheck2 = "*비밀번호가 일치합니다!"),
          (this.passwordPass2 = true)
        );
      } else if (this.changedPassword != this.passwordChecker) {
        return (this.passwordCheck2 = "*비밀번호가 일치하지않습니다.");
      } else if (
        this.user.password === this.passwordChecker &&
        !this.passwordChecker
      ) {
        return (this.passwordCheck2 = "");
      }
    },
    // 유저정보수정
    userUpdate() {
      this.message = "";

      if (this.namePass && this.phonePass && this.emailPass) {
        console.log(JSON.stringify(this.user));
        userService
          .update(this.user.id, this.user)
          .then((response) => {
            console.log(response.data.message);
            alert(response.data.message);
          })
          .catch((e) => {
            console.log(e);
            alert(e);
          });
        localStorage.setItem("user", JSON.stringify(this.user));
        this.$router.go();
      } else {
        alert("정보를 다시 확인하여주세요.");
      }
    },
    // 유저 비밀번호 수정
    passowrdUpdate() {
      const PWchangeUser = new User(
        this.user.username,
        this.user.names,
        this.user.phone,
        this.user.gender,
        this.user.birthDate,
        this.user.email,
        this.changedPassword,
        this.user.id,
        this.user.roles
      );
      console.log(JSON.stringify(PWchangeUser));
      if (this.passwordPass && this.passwordPass2) {
        userService
          .update(this.user.id, PWchangeUser)
          .then((response) => {
            console.log(response.data);
            this.message = "비밀번호 변경에 성공하였습니다.";
            alert(this.message);
            this.$router.go();
          })
          .catch((e) => {
            console.error(e);
            alert(e);
          });
      } else {
        alert("비밀번호를 다시 확인하여주세요.");
      }
    },
    getUser(username) {
      userService
        .getUserProfile(username)
        .then((reponse) => {
          this.user = reponse.data;
          // console.log(reponse.data);

          // console.log("가져오는 유저 양식은:" + JSON.stringify(this.geteduser));
          // console.log("수정용 유저 양식은:" + JSON.stringify(this.user));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 예매취소
    cancelTicket(ticketId) {
      console.log("티켓아이디" + this.ticketId);

      ReservationCheckService.cancelReservationTicket(ticketId)
        .then((response) => {
          console.log("취소하고 받은 값" + response.data);
          alert("예약이 취소 되었습니다.");
          this.$router.push("/");
        })
        .catch((e) => {
          console.log(e);
        });
    },
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

.id_box {
  display: table-cell;
  vertical-align: middle;
  height: 40px;
}

.nav-item {
  padding: 10px;
  width: 200px;
  text-align: center;
}

.tab-box {
  margin-top: 100px;
  margin-bottom: 150px;
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
  width: 700px;
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
  padding-left: 160px;
  height: 100px;
}
.join_input_box .title {
  position: absolute;
  left: 0;
  top: 0;
  width: 140px;
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
input[type="text"]:disabled,
input[type="text"].ty2:disabled,
input[type="text"].ty3:disabled {
  color: #666;
  background: white;
  border-bottom: 1px solid #ddd;
}

input[type="password"],
input[type="text"],
input[type="email"],
input[type="number"],
input[type="tel"] {
  height: 40px;
  line-height: 40px;
  padding: 0 18px;
  color: black;
  background-color: rgb(245, 244, 244);
  font-size: 14px;
  font-weight: bolder;
  box-sizing: border-box;
  border-bottom: 1px solid rgb(0, 0, 0);
  border-left: 0px white;
  border-top: 0px white;
  border-right: 0px white;
  outline: none;
}

input[type="password"]:focus,
input[type="text"] :focus,
input[type="email"] :focus,
input[type="number"] :focus,
input[type="tel"]:focus {
  height: 40px;
  line-height: 40px;
  padding: 0 18px;
  background-color: rgb(255, 255, 255);
  font-size: 14px;
  box-sizing: border-box;
  border-bottom: 1px solid #ddd;
  border-left: 0px white;
  border-top: 0px white;
  border-right: 0px white;
  outline: none;
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

/* 예매페이지 */

.cc_title_wrap {
  margin-top: 50px;
}
.cc_title_top {
  width: 980px;
  margin: auto;
  height: 50px;
  border-bottom: 1px solid #eee;
}
.cc_title_top .cc_tit {
  float: left;
  font-size: 24px;
  color: #000;
  line-height: 50px;
}
.cc_tab_wrap.cc_outer {
  padding-top: 20px;
}
.cc_tab_content {
  overflow: inherit;
  position: relative;
  width: 980px;
  height: 750px;
  margin: 60px auto;
}

.cc_movie_info {
  /* position: relative;
  left: 400px;
  bottom: 500px; */
  width: 930px;
}
.tb_acc_wrap {
  width: 930px;
  margin: auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
  border-bottom: 1px solid #eee;
}
.tb_acc_wrap th {
  font-size: 15px;
  color: #666666;
  text-align: center;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  padding: 27px 0;
}
.tb_acc_wrap td {
  text-align: left;
  font-size: 19px;
  font-weight: bold;
  padding-left: 40px;
}
.cc_pay {
  margin-top: 20px;
  padding-right: 40px;
  text-align: right;
}
.cc_pay_tit {
  margin-right: 20px;
  font-size: 15px;
  font-weight: 600;
  color: #666666;
}
.cc_pay_amount {
  font-size: 30px;
  font-weight: bold;
  color: #ff243e;
}

.btn_btm_wrap {
  /* position: relative;
  bottom: 400px; */
  margin: auto;
  /* text-align: center; */
}
.btn_col2.ty6,
.btn_col3.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_btm_wrap a,
.btn_btm_wrap button {
  margin: 0 3px;
  min-width: 160px;
}
.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #fff !important;
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

h1,
h2,
h3,
h4,
h5,
h6,
address,
caption,
em,
th {
  font-style: normal;
  font-weight: normal;
}

.font {
  font-size: 17px;
  font-style: normal;
  font-weight: bold;
}

.right-td {
  width: 300px;
}
.left-td {
  width: 150px;
}

th {
  width: 140px;
}

.ticket-box {
  border: 1px solid lightgray;
  text-align: center;
  padding: 15px;
  width: 960px;
  margin: 0 auto;
}
</style>