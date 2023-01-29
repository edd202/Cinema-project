<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>
    <!-- 본문 시작 -->

    <div class="tab-box">
      <div class="tab-continer">
        <div class="tab-content2">
          <div class="tab_con">
            <div class="contents_inner">
              <div class="login_input_box">
                <div class="login_form_wrap">
                 <div style="font-size:20px;color: blue; font-size: 13px; border-bottom: 1px solid black; padding-bottom: 20px; text-align: center; margin-bottom: 40px;">*모든 정보는 필수 사항이며, 약관에 동의하지 않을 시 회원가입을 이용에 제한이있습니다.*</div>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="username" class="txt_star">ID</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        class="id_box"
                        type="text"
                        name="username"
                        id="username"
                        v-model="user.username"
                        placeholder="ID를 입력해 주세요"
                        title="입력태그"
                        @keyup="IDValidate()"
                      />
                      <button class="id_check_box" style="font-size: 12px; margin-left: 5px;" @click="idOverlapCheck()" v-bind:disabled="!IDPass">중복확인</button>
                      <br>
                      <span style="font-size:12px;color: red">
                        {{ IDCheck }}
                      </span>
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="names" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="names"
                        id="names"
                        v-model="user.names"
                        placeholder="이름을 입력해 주세요"
                        title="입력태그"
                        @keyup="nameValidate()"
                      />
                    </dd>
                    <br>
                    <span style="font-size:12px;color: red">{{ nameCheck }}</span>
                  </dl>
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
                      <br>
                      <span style="font-size:12px;color: red">
                        {{ phoneCheck }}
                      </span>
                    <span style="font-size:12px;color: red">
                        {{ phoneCheckNum }}
                      </span>
                    </dd>
                  </dl>
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
                      <br>
                      <!-- v-validate="'email'" -->
                      <span style="font-size:12px;color: red">
                        {{ emailCheck }}
                      </span>
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="Email" class="txt_star">성별</label>
                    </dt>
                    <dd class="inp_box">
                     <select   id="gender"
                        name="gender" v-model="user.gender" style="width:80px !important; text-align: center;" title="성별">
                      <option value="" selected>성별</option>
                      <option value="M">남</option>
                      <option value="F">여</option>
                    </select>
                    
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="input_join5" class="txt_star">생년월일</label>
                    </dt>
                    <dd class="inp_box">
                      <select
                        title="출생년도"
                        id="birthyy"
                        name="birthyy"
                        v-model="birthyy"
                        @click="birthDateValidate()"
                      >
                        <option value="">출생년도</option>
                        <option value="2023">2023</option>
                        <option value="2022">2022</option>
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                        <option value="1987">1987</option>
                        <option value="1986">1986</option>
                        <option value="1985">1985</option>
                        <option value="1984">1984</option>
                        <option value="1983">1983</option>
                        <option value="1982">1982</option>
                        <option value="1981">1981</option>
                        <option value="1980">1980</option>
                        <option value="1979">1979</option>
                        <option value="1978">1978</option>
                        <option value="1977">1977</option>
                        <option value="1976">1976</option>
                        <option value="1975">1975</option>
                        <option value="1974">1974</option>
                        <option value="1973">1973</option>
                        <option value="1972">1972</option>
                        <option value="1971">1971</option>
                        <option value="1970">1970</option>
                        <option value="1969">1969</option>
                        <option value="1968">1968</option>
                        <option value="1967">1967</option>
                        <option value="1966">1966</option>
                        <option value="1965">1965</option>
                        <option value="1964">1964</option>
                        <option value="1963">1963</option>
                        <option value="1962">1962</option>
                        <option value="1961">1961</option>
                        <option value="1960">1960</option>
                        <option value="1959">1959</option>
                        <option value="1958">1958</option>
                        <option value="1957">1957</option>
                        <option value="1956">1956</option>
                        <option value="1955">1955</option>
                        <option value="1954">1954</option>
                        <option value="1953">1953</option>
                        <option value="1952">1952</option>
                        <option value="1951">1951</option>
                        <option value="1950">1950</option>
                        <option value="1949">1949</option>
                        <option value="1948">1948</option>
                        <option value="1947">1947</option>
                        <option value="1946">1946</option>
                        <option value="1945">1945</option>
                        <option value="1944">1944</option>
                        <option value="1943">1943</option>
                        <option value="1942">1942</option>
                        <option value="1941">1941</option>
                        <option value="1940">1940</option>
                        <option value="1939">1939</option>
                        <option value="1938">1938</option>
                        <option value="1937">1937</option>
                        <option value="1936">1936</option>
                        <option value="1935">1935</option>
                        <option value="1934">1934</option>
                        <option value="1933">1933</option>
                        <option value="1932">1932</option>
                        <option value="1931">1931</option>
                        <option value="1930">1930</option>
                        <option value="1929">1929</option>
                        <option value="1928">1928</option>
                        <option value="1927">1927</option>
                        <option value="1926">1926</option>
                        <option value="1925">1925</option>
                        <option value="1924">1924</option>
                        <option value="1923">1923</option>
                        <option value="1922">1922</option>
                        <option value="1921">1921</option>
                        <option value="1920">1920</option>
                        <option value="1919">1919</option>
                        <option value="1918">1918</option>
                        <option value="1917">1917</option>
                        <option value="1916">1916</option>
                        <option value="1915">1915</option>
                        <option value="1914">1914</option>
                        <option value="1913">1913</option>
                        <option value="1912">1912</option>
                        <option value="1911">1911</option>
                        <option value="1910">1910</option>
                        <option value="1909">1909</option>
                        <option value="1908">1908</option>
                        <option value="1907">1907</option>
                        <option value="1906">1906</option>
                        <option value="1905">1905</option>
                        <option value="1904">1904</option>
                        <option value="1903">1903</option>
                        <option value="1902">1902</option>
                        <option value="1901">1901</option>
                        <option value="1900">1900</option>
                      </select>
                      <select
                        title="월"
                        id="birthmm"
                        name="birthmm"
                        v-model="birthmm"
                        @click="birthDateValidate()"
                      >
                        <option value="">월</option>
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                      </select>
                      <select
                        title="일"
                        id="birthdd"
                        name="birthdd"
                        v-model="birthdd"
                        @click="birthDateValidate()"
                      >
                        <option value="">일</option>
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                      </select>
                      <br>
                      <span style="font-size:12px;color: red">{{ birthDateCheck }}</span>
                    </dd>
                  </dl>
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
                        v-model="user.password"
                        maxlength="20"
                        minlength="8"
                        placeholder="비밀번호 (8~20자리)를 입력해 주세요."
                        title="입력태그"
                        aria-autocomplete="list"
                        @keyup="passwordValidate()"
                      />
                      <br>

                      <span style="font-size:12px;color: red">{{ passwordCheck }}</span>
                    </dd>
                    
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
                      <br>
                      <span style="font-size:12px;color: red">{{ passwordCheck2 }}</span>
                    </dd>
                  </dl>
                </div>
              </div>

              <div class="txtarea_box">
                <div class="txtarea">
                  <a class="focus_textarea" href="#none"
                    >개인정보의 수집목적 및 항목</a
                  ><br />① 개인정보 수집 목적: 회원 예매확인 및 이용자 식별
                  <br />② 수집항목: 이름, 휴대폰번호, 생년월일, 이메일 주소
                  <br /><br /><span class="color_red"
                    >개인정보의 보유 및 이용기간<br />개인정보는 회원 탈퇴 이전
                    까지 위 이용 목적으로 이용 및 보유 합니다. <br />다만, 상법
                    등 관련법령의 규정에 의하여 거래 관련 관리 의무 관계의 확인
                    등을 이유로 일정기간 보유하여야 할 필요가 있을 경우 아래와
                    같이 보유합니다. <br />대금결제 및 재화 등의 공급에 관한
                    기록 : 5년</span
                  ><br /><br />※ 회원 가입 서비스 제공을 위한 최소한의
                  개인정보이며 거부할 수 있습니다. 다만, 수집에 동의하지 않을
                  경우 서비스 이용이 제한됩니다.
                </div>
                <div class="agreement_check_box content-right">
                  <input
                    type="radio"
                    name="agreeCheck"
                    id="privacyOk01"
                    value="agree"
                    checked
                    @click="privacyValidate()"
                  /><label for="privacyOk01">동의</label
                  ><input
                    type="radio"
                    name="agreeCheck"
                    id="privacyOk02"
                    value="degree"
                    @click="privacyValidate()"
                  /><label for="privacyOk02">동의하지않음</label>
                </div>
              </div>
            </div>
            <div class="btn_btm_wrap content-right">
              <button class="btn_col3 ty6" title="회원가입" @click="signUp()">
                회원가입
              </button>
              &nbsp;<button
                type="button"
                class="btn_col2 ty6"
                title="회원가입 취소"
              >
                <router-link to="/" style="color: white"
                  >회원가입 취소</router-link
                >
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 본문 끝-->

  
  </div>
</template>
  
  <script>
/* eslint-disable */

import NavBlack from "@/components/common/NavBlack.vue";
import User from "@/models/user";
import authService from "@/services/userServices/auth.service";

export default {
  components: {
    NavBlack,
     },

  mounted() {
    
  },
  data() {
    return {
      birthyy: "",
      birthmm: "",
      birthdd: "",
      show: false,
      user: new User("", "", "", "", "", "","",""),
      submitted: false,
      successful: false,
      message: "",
      IDPass: false,
      IDPass2: false,
      IDCheck: "",
      namePass: false,
      nameCheck: "",
      phonePass: false,
      phoneCheck: "",
      phoneCheckNum: "",
      emailPass: false,
      emailCheck: "",
      passwordPass: false,
      passwordPass2: false,
      passwordChecker: "",
      passwordCheck:
        "*8~16자의 영문 대,소문자와, 숫자 또는 특수문자의 조합으로 이루어져야합니다.",
      passwordCheck2: "",
      birthDatePass:false,
      birthDateCheck: "",
      privacyPass:true,
      privacyCheck:""
      
    };
  },
  methods: {
    IDValidate() {
      var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
      var kr_pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
      if (special_pattern.test(this.user.username) == false) {
        if (kr_pattern.test(this.user.username) == false) {
          if (
            this.user.username.length >= 5 &&
            this.user.username.length < 21
          ) {
            return (
              (this.IDCheck = "*사용가능한 아이디 형식 입니다."), (this.IDPass = true)
            );
          } else {
            return (
              (this.IDCheck =
                "*ID는 5~20자리의 영어 및 숫자로 이루어져야 합니다."),
              (this.IDPass = false)
            );
          }
        } else {
          return (
            (this.IDCheck =
              "*ID는 5~20자리의 영어 및 숫자로 이루어져야 합니다."),
            (this.IDPass = false)
          );
        }
      } else {
        return (
          (this.IDCheck = "*ID는 5~20자리의 영어 및 숫자로 이루어져야 합니다."),
          (this.IDPass = false)
        );
      }
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
          (this.phonePass = false)
        );
      } else {
        return (this.phoneCheck = "");
      }
    },
    phoneValidate2() {
      if (!isNaN(this.user.phone) && this.user.phone.length < 10) {
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
        return this.emailPass=false,this.emailCheck="*올바른 이메일 형식이 아닙니다.";
      } else {
        return this.emailPass=true,this.emailCheck=""
      }
    },
    birthDateValidate() {
      if (this.birthDate.length == 8) {
        this.birthDatePass=true, (this.birthDateCheck = ""),this.brithDatePass=true
      } else {
        return (
          this.birthDatePass=false, this.birthDateCheck = "*생년월일을 정확하게 선택하여 주세요.",this.brithDatePass=false
        );
      }
    },
    passwordValidate() {
      var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
      var kr_pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
      var num_pattern = /[0-9]/;
      var en_pattern = /[a-z|A-Z]/;
      if (special_pattern.test(this.user.password)||num_pattern.test(this.user.password)) {
        if (kr_pattern.test(this.user.password) == false&&en_pattern.test(this.user.password)) {
          if (
            this.user.password.length >= 8 &&
            this.user.password.length < 21
          ) {
            console.log(this.user.password.length);
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
    passwordValidate2() {
      if (this.user.password === this.passwordChecker&&this.passwordChecker) {
        return (
          (this.passwordCheck2 = "*비밀번호가 일치합니다!"),
          (this.passwordPass2 = true)
        );
      } else if(this.user.password != this.passwordChecker) {
        return (this.passwordCheck2 = "*비밀번호가 일치하지않습니다.");
      }else if(this.user.password === this.passwordChecker&&!this.passwordChecker){
        return (this.passwordCheck2 = "");
      }
    },
    privacyValidate(){
      if(document.querySelector('input[name="agreeCheck"]:checked').value=="agree"){
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return this.privacyPass=true,this.privacyCheck=""
      }else{
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return this.privacyPass=false,this.privacyCheck="약관에 동의하지않으면 이용에 제한됩니다."
      }
  },
  // 아이디 중복 체크 함수 실행
  idOverlapCheck(){
    this.message = "";
    authService.idOverlapCheck(this.user.username)
    .then((response)=>{
      console.log(response.data)
      if(response.data==false){
        this.IDPass=true;
        this.IDPass2=true;
        alert("사용가능한 아이디입니다.")
      }else{
        this.IDCheck="새로운 아이디를 입력해주세요."
        this.IDPass=false;
        this.IDPass2=false;
        alert("이미 등록된 아이디입니다.")
      }
    }
    ).catch((error)=>{
      this.message = error;
      alert(this.message)
    })
  },
  // 회원가입
    signUp() {
      this.birthDate;
      this.message = "";
      this.submitted = true;
      if(this.privacyPass){
        if (
        this.IDPass &&
        this.IDPass2 &&
        this.namePass &&
        this.phonePass &&
        this.passwordPass &&
        this.birthDatePass &&
        this.emailPass
      ) {
        console.log(JSON.stringify(this.user))
        //  공유 저장소의 새사용자 등록 함수 실행
        this.$store
          .dispatch("auth/register", this.user)
          .then((response) => {
            this.message = response.message;
            this.successful = true;
            alert(this.message)
            this.$router.push("/")
          })
          .catch((error) => {
            
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            this.successful = false;
            alert(this.message);
          });
      }else{
        if(this.IDPass2==false){
          alert("아이디 중복확인을 해주세요.")
        }
        alert("입력정보를 확인하여 주세요.")
      } 
      // else {
      //   console.log(
      //     this.IDPass,
      //     this.namePass,
      //     this.phonePass,
      //     this.passwordPass,
      //     this.birthDatePass,
      //     this.emailPass,
      //     this.privacyPass
      //   );
      // }
    }else{
      alert(this.privacyCheck)
    }
      }
      
  },
  computed: {
    birthDate() {
      return (this.user.birthDate = this.birthyy + this.birthmm + this.birthdd);
    },
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    mounted() {
      if (this.loggedIn) {
        this.$router.push("/");
      }
      
    },
    
    
}}
</script>
  
  <style scoped>
body {
  margin-top: 100px;
  font-family: "Trebuchet MS", serif;
  line-height: 1.6;
}

a {
  text-decoration: none;
  cursor: pointer;
}

ul.tabs {
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tabs li {
  background: none;
  color: #222;
  display: inline-block;
  padding: 10px 15px;
  cursor: pointer;
}

ul.tabs li.current {
  background: #ededed;
  color: #222;
}

.nav-item {
  padding: 10px;
  width: 200px;
  text-align: center;
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

.tab-content {
  width: 500px;
  margin: 0px auto;
}
.tab-content2 {
  width: 700px;
  margin: 50px auto;
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
/* 소셜 로그인 이미지 넣는 부분 */
.member_login_wrap .login_box .btn_naver_social::after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 56px;
  height: 50px;
  border-right: 1px solid #ddd;
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
  padding-left: 110px;
  height: 75px;
}
.join_input_box .title {
  position: absolute;
  left: 0;
  top: 0;
  width: 95px;
  line-height: 40px;
  letter-spacing: -0.5px;
  font-size: 15px;
  text-align: right;
}

.join_input_box .inp_box input {
  width:465px;
}
.join_input_box .inp_box > select {
  padding: 0 18px;
  width: 132.5px;
}
.join_input_box .inp_box > select:first-child {
  width: 180px;
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

.id_box{
width: 465px !important;
vertical-align: middle;

}

.id_check_box{
  width: 75px !important;
  height: 40px;
  line-height: 40px;
  border-radius: 4px;
  background-color: rgb(222, 71, 71);
  color: white;
}

.id_check_box:disabled{
  width: 75px !important;
  height: 40px;
  line-height: 40px;
  border-radius: 4px;
  background-color: rgb(191, 191, 191);
  color: white;
}

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
  border-color: #f8f8f8;
  color: #666;
  background: #efefef;
}

input[type="password"],
input[type="text"],
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

/* common */
.mgt0 {
  margin-top: 0 !important;
}
.mgt5 {
  margin-top: 5px !important;
}
.mgt10 {
  margin-top: 10px !important;
}
.mgt15 {
  margin-top: 15px !important;
}
.mgt20 {
  margin-top: 20px !important;
}
.mgt25 {
  margin-top: 25px !important;
}
.mgt30 {
  margin-top: 30px !important;
}
.mgt35 {
  margin-top: 35px !important;
}
.mgt40 {
  margin-top: 40px !important;
}
.mgt45 {
  margin-top: 45px !important;
}
.mgt50 {
  margin-top: 50px !important;
}

.mgl0 {
  margin-left: 0 !important;
}
.mgl5 {
  margin-left: 5px !important;
}
.mgl10 {
  margin-left: 10px !important;
}
.mgl20 {
  margin-left: 20px !important;
}
.mgl30 {
  margin-left: 30px !important;
}

.mgr5 {
  margin-right: 5px !important;
}
.mgr10 {
  margin-right: 10px !important;
}
.mgr20 {
  margin-right: 20px !important;
}
.mgr30 {
  margin-right: 30px !important;
}

.mgb0 {
  margin-bottom: 0 !important;
}
.mgb5 {
  margin-bottom: 5px !important;
}
.mgb10 {
  margin-bottom: 10px !important;
}
.mgb15 {
  margin-bottom: 15px !important;
}
.mgb20 {
  margin-bottom: 20px !important;
}
.mgb25 {
  margin-bottom: 25px !important;
}
.mgb30 {
  margin-bottom: 30px !important;
}
.mgb35 {
  margin-bottom: 35px !important;
}
.mgb40 {
  margin-bottom: 40px !important;
}

.pdl0 {
  padding-left: 0 !important;
}
.pdl20 {
  padding-left: 20px !important;
}

.ellipsis {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.hidden {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}

/* 추가 20191231 */
.pt0 {
  padding-top: 0 !important;
}
.pt5 {
  padding-top: 5px !important;
}
.pt10 {
  padding-top: 10px !important;
}
.pt15 {
  padding-top: 15px !important;
}
.pt20 {
  padding-top: 20px !important;
}
.pt25 {
  padding-top: 25px !important;
}
.pt30 {
  padding-top: 30px !important;
}
.pt35 {
  padding-top: 35px !important;
}
.pt40 {
  padding-top: 40px !important;
}
.pt45 {
  padding-top: 45px !important;
}
.pt50 {
  padding-top: 50px !important;
}

.pb0 {
  padding-bottom: 0 !important;
}
.pb5 {
  padding-bottom: 5px !important;
}
.pb10 {
  padding-bottom: 10px !important;
}
.pb15 {
  padding-bottom: 15px !important;
}
.pb20 {
  padding-bottom: 20px !important;
}
.pb25 {
  padding-bottom: 25px !important;
}
.pb30 {
  padding-bottom: 30px !important;
}
.pb35 {
  padding-bottom: 35px !important;
}
.pb40 {
  padding-bottom: 40px !important;
}
.pb45 {
  padding-bottom: 45px !important;
}
.pb50 {
  padding-bottom: 50px !important;
}

.pl0 {
  padding-left: 0 !important;
}
.pl5 {
  padding-left: 5px !important;
}
.pl10 {
  padding-left: 10px !important;
}
.pl15 {
  padding-left: 15px !important;
}
.pl20 {
  padding-left: 20px !important;
}
.pl25 {
  padding-left: 25px !important;
}
.pl30 {
  padding-left: 30px !important;
}
.pl35 {
  padding-left: 35px !important;
}
.pl40 {
  padding-left: 40px !important;
}
.pl45 {
  padding-left: 45px !important;
}
.pl50 {
  padding-left: 50px !important;
}

.pr0 {
  padding-right: 0 !important;
}
.pr5 {
  padding-right: 5px !important;
}
.pr10 {
  padding-right: 10px !important;
}
.pr15 {
  padding-right: 15px !important;
}
.pr20 {
  padding-right: 20px !important;
}
.pr25 {
  padding-right: 25px !important;
}
.pr30 {
  padding-right: 30px !important;
}
.pr35 {
  padding-right: 35px !important;
}
.pr40 {
  padding-right: 40px !important;
}
.pr45 {
  padding-right: 45px !important;
}
.pr50 {
  padding-right: 50px !important;
}

.mt0 {
  margin-top: 0 !important;
}
.mt5 {
  margin-top: 5px !important;
}
.mt10 {
  margin-top: 10px !important;
}
.mt15 {
  margin-top: 15px !important;
}
.mt20 {
  margin-top: 20px !important;
}
.mt25 {
  margin-top: 25px !important;
}
.mt30 {
  margin-top: 30px !important;
}
.mt35 {
  margin-top: 35px !important;
}
.mt40 {
  margin-top: 40px !important;
}
.mt45 {
  margin-top: 45px !important;
}
.mt50 {
  margin-top: 50px !important;
}
.mt80 {
  margin-top: 80px !important;
}

.mb0 {
  margin-bottom: 0px !important;
}
.mb5 {
  margin-bottom: 5px !important;
}
.mb10 {
  margin-bottom: 10px !important;
}
.mb15 {
  margin-bottom: 15px !important;
}
.mb20 {
  margin-bottom: 20px !important;
}
.mb25 {
  margin-bottom: 25px !important;
}
.mb30 {
  margin-bottom: 30px !important;
}
.mb35 {
  margin-bottom: 35px !important;
}
.mb40 {
  margin-bottom: 40px !important;
}
.mb45 {
  margin-bottom: 45px !important;
}
.mb50 {
  margin-bottom: 50px !important;
}

.ml0 {
  margin-left: 0 !important;
}
.ml5 {
  margin-left: 5px !important;
}
.ml10 {
  margin-left: 10px !important;
}
.ml15 {
  margin-left: 15px !important;
}
.ml20 {
  margin-left: 20px !important;
}
.ml25 {
  margin-left: 25px !important;
}
.ml30 {
  margin-left: 30px !important;
}
.ml35 {
  margin-left: 35px !important;
}
.ml40 {
  margin-left: 40px !important;
}
.ml45 {
  margin-left: 45px !important;
}
.ml50 {
  margin-left: 50px !important;
}

.mr0 {
  margin-right: 0 !important;
}
.mr5 {
  margin-right: 5px !important;
}
.mr10 {
  margin-right: 10px !important;
}
.mr15 {
  margin-right: 15px !important;
}
.mr20 {
  margin-right: 20px !important;
}
.mr25 {
  margin-right: 25px !important;
}
.mr30 {
  margin-right: 30px !important;
}
.mr35 {
  margin-right: 35px !important;
}
.mr40 {
  margin-right: 40px !important;
}
.mr45 {
  margin-right: 45px !important;
}
.mr50 {
  margin-right: 50px !important;
}

.fr {
  float: right !important;
}
.fl {
  float: left !important;
}
.txtleft {
  text-align: left !important;
}
.txtright {
  text-align: right !important;
}
.txtcenter {
  text-align: center !important;
}
.clearboth {
  clear: both;
}
.imgver {
  vertical-align: middle;
}
.imgverT {
  vertical-align: top;
}
.txtb {
  font-weight: bold !important;
}
.disnone {
  display: none !important;
}
.txt_color01 {
  color: #ff243e !important;
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