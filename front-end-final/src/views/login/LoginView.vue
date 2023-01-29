<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>
    <!-- 본문 시작 -->

    <div class="tab-box">
      <nav class="navbar bg-light justify-content-center">
        <ul class="nav nav-tabs" id="tabs">
          <li v-bind:class="navtab1">
            <a
              @click.prevent="
                (currentTab = 0),
                  (navtab1 = 'nav-item active'),
                  (navtab2 = 'nav-item'),
                  (navtab3 = 'nav-item'),
                  checkReset()
              "
              >로그인</a
            >
          </li>

          <li v-bind:class="navtab2">
            <a
              @click.prevent="
                (currentTab = 1),
                  (navtab1 = 'nav-item'),
                  (navtab2 = 'nav-item active'),
                  (navtab3 = 'nav-item'),
                  checkReset()
              "
              >비회원 로그인</a
            >
          </li>
          <li v-bind:class="navtab3">
            <a
              @click.prevent="
                (currentTab = 2),
                  (navtab1 = 'nav-item'),
                  (navtab2 = 'nav-item'),
                  (navtab3 = 'nav-item active'),
                  checkReset()
              "
              >소셜 로그인</a
            >
          </li>
        </ul>
      </nav>
      <!-- 회원 로그인 -->
      <div v-show="currentTab == 0" class="tab-continer member_login_wrap">
        <div class="tab-content-login">
          <div class="contents_inner">
            <div class="login_box">
              <div class="login_area">
                <form name="form" @submit.prevent="handleLogin">
                  <input
                    v-model="user.username"
                    type="text"
                    id="userId"
                    maxlength="50"
                    placeholder="아이디 또는 이메일을 입력해 주세요."
                    title="입력태그"
                  /><input
                    v-model="user.password"
                    type="password"
                    id="userPassword"
                    maxlength="15"
                    placeholder="비밀번호를 입력해 주세요."
                    title="입력태그"
                  /><button class="btn_login">로그인</button>
                </form>
              </div>
              <div class="login_bot_wrap">
                <div class="login_check">
                  <input
                    type="checkbox"
                    name="idsave"
                    id="checkSavedID"
                    v-model="saveID"
                    value="check"
                    
                  /><label for="checkSavedID">아이디 저장</label>
                </div>
                <div class="login_menu">
                  <!-- TODO:아이디 찾기 비밀번호 찾기 페이지 추가 -->
                  <router-link to="/signup">회원가입</router-link>
                  <router-link to="/findid">아이디 찾기</router-link>
                  <router-link to="/findpw">비밀번호 찾기</router-link>
                </div>
                <br />
                <div
                  style="font-size: 12px; color: red; float: left; width: 470px"
                >
                  {{ IDPassowrdCheck }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 비회원 로그인 및 예매확인 -->
      <div v-show="currentTab == 1" class="tab-continer">
        <div class="tab-content2">
          <ul class="nav nav-tabs" id="tabs" style="margin-bottom: 15px">
            <li v-bind:class="navtab_tab1">
              <a
                @click.prevent="
                  (currentTab2 = 0),
                    (navtab_tab1 = 'nav-item active'),
                    (navtab_tab2 = 'nav-item'),
                    checkReset()
                "
                >비회원 로그인</a
              >
            </li>
            <li v-bind:class="navtab_tab2">
              <a
                @click.prevent="
                  (currentTab2 = 1),
                    (navtab_tab1 = 'nav-item'),
                    (navtab_tab2 = 'nav-item  active'),
                    checkReset()

                "
                >비회원 예매확인</a
              >
            </li>
          </ul>

          <div class="tab_con" v-show="currentTab2 == 0" style="height:750px">
            <h3 class="hidden">비회원</h3>
            <div class="contents_inner">
              <div class="login_input_box">
                <div class="login_form_wrap">
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userName" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="userName"
                        id="userName"
                        placeholder="이름을 입력해 주세요"
                        title="입력태그"
                        v-model="nonUser.names"
                      />
                      <span style="font-size: 12px; color: red">{{
                        nameCheck
                      }}</span>
                    </dd>
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
                        placeholder="휴대폰 번호(- 생략)를 입력해 주세요."
                        title="입력태그"
                        maxlength="11"
                        v-model="nonUser.phone"
                      />
                      <span style="font-size: 12px; color: red">{{
                        phoneCheck
                      }}</span>
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
                      >
                        <option value="">출생년도</option>
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
                        <option value="1900">1900</option></select
                      ><select
                        title="월"
                        id="birthmm"
                        name="birthmm"
                        v-model="birthmm"
                      >
                        <option value="">월</option>
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option></select
                      ><select
                        title="일"
                        id="birthdd"
                        name="birthdd"
                        v-model="birthdd"
                      >
                        <option value="">일</option>
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
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
                      <span style="font-size: 12px; color: red">{{
                        birthDateCheck
                      }}</span>
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPassword2" class="txt_star"
                        >예매 비밀번호</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="password"
                        name="userPassword2"
                        id="userPassword2"
                        maxlength="4"
                        placeholder="예매 비밀번호 (4자리)를 입력해 주세요."
                        title="입력태그"
                        aria-autocomplete="list"
                        v-model="passwordChecker"
                      />
                      <span style="font-size: 12px; color: red">{{
                        passwordCheck
                      }}</span>
                    </dd>
                  </dl>
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPassword3" class="txt_star"
                        >예매 비밀번호 확인</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="password"
                        name="userPassword3"
                        id="userPassword3"
                        maxlength="4"
                        placeholder="예매 비밀번호 (4자리)를 재입력해 주세요."
                        title="입력태그"
                        v-model="nonUser.password"
                        @keyup="passwordValidate2()"
                      />
                      <span style="font-size: 12px; color: red">{{
                        passwordCheck2
                      }}</span>
                    </dd>
                  </dl>
                </div>
                <ul class="list_txt ty2">
                  <li>
                    비회원 로그인 시 예매 및 예매확인/취소 메뉴만 이용 가능하며
                    관람권, 할인권을 포함한 기타 결제, 할인수단은 정회원 로그인
                    시 사용 가능 합니다.
                  </li>
                </ul>
              </div>

              <div class="txtarea_box">
                <div class="txtarea">
                  <a class="focus_textarea" href="#none"
                    >개인정보의 수집목적 및 항목</a
                  ><br />① 개인정보 수집 목적: 비회원 예매확인 및 이용자 식별
                  <br />② 수집항목: 이름, 휴대폰번호, 생년월일, 고유번호
                  <br /><br /><span class="color_red"
                    >개인정보의 보유 및 이용기간<br />개인정보는 영화 예매 완료
                    후 동의일로부터 70일까지 위 이용 목적으로 이용 및 보유
                    합니다. (단, 생년월일은 이용자 식별 목적으로 이용되며 별도
                    보관되지 않습니다.)<br />다만, 상법 등 관련법령의 규정에
                    의하여 거래 관련 관리 의무 관계의 확인 등을 이유로 일정기간
                    보유하여야 할 필요가 있을 경우 아래와 같이 보유합니다.
                    <br />대금결제 및 재화 등의 공급에 관한 기록 : 5년</span
                  ><br /><br />※ 비회원 예매서비스 제공을 위한 최소한의
                  개인정보이며 거부할 수 있습니다. 다만, 수집에 동의하지 않을
                  경우 서비스 이용이 제한됩니다.
                </div>
                <div class="agreement_check_box content-right">
                  <input
                    type="radio"
                    name="agreeCheck1"
                    id="privacyOk01"
                    value="agree"
                    checked
                  /><label for="privacyOk01">동의</label
                  ><input
                    type="radio"
                    name="agreeCheck1"
                    id="privacyOk02"
                    value="degree"
                  /><label for="privacyOk02">동의하지않음</label>
                </div>
              </div>
            </div>
            <div class="btn_btm_wrap content-right">
              <router-link to="/signup"
                ><button
                  href="#none"
                  class="btn_col3 ty6"
                  title="회원가입 페이지 이동"
                >
                  회원가입
                </button></router-link
              >&nbsp;
              <button
                @click="nonUserLogin()"
                class="btn_col2 ty6"
                title="비회원 로그인 페이지 이동"
              >
                비회원 로그인
              </button>
            </div>
          </div>
          <!-- 비회원 예매 확인 -->
          <div class="tab_con" v-show="currentTab2 == 1" style="height:650px">
            <div v-if="!showTicket">
            <div class="contents_inner">
              <div class="login_input_box">
                <div class="login_form_wrap">
                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userName" class="txt_star">이름</label>
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="text"
                        name="userName"
                        id="userName"
                        placeholder="이름을 입력해 주세요"
                        title="입력태그"
                        v-model="nonUserReserNames"
                      />
                    </dd>
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
                        placeholder="휴대폰 번호(- 생략)를 입력해 주세요."
                        title="입력태그"
                        maxlength="11"
                        v-model="nonUserReserPhone"
                      />
                    </dd>
                  </dl>

                  <dl class="join_input_box">
                    <dt class="title">
                      <label for="userPassword2" class="txt_star"
                        >예매 비밀번호</label
                      >
                    </dt>
                    <dd class="inp_box">
                      <input
                        type="password"
                        name="userPassword2"
                        id="userPassword2"
                        maxlength="4"
                        placeholder="예매 비밀번호 (4자리)를 입력해 주세요."
                        title="입력태그"
                        v-model="nonUserReserPassword"
                        aria-autocomplete="list"
                      />
                      <span style="font-size: 12px; color: red">{{
                        nonUserReserError
                      }}</span>
                    </dd>
                  </dl>
                </div>
              </div>

              <div class="txtarea_box">
                <div class="txtarea">
                  <a class="focus_textarea" href="#none"
                    >개인정보의 수집목적 및 항목</a
                  ><br />① 개인정보 수집 목적: 비회원 예매확인 및 이용자 식별
                  <br />② 수집항목: 이름, 휴대폰번호, 고유번호 <br /><br /><span
                    class="color_red"
                    >개인정보의 보유 및 이용기간<br />개인정보는 영화 예매 완료
                    후 동의일로부터 70일까지 위 이용 목적으로 이용 및 보유
                    합니다.<br />다만, 상법 등 관련법령의 규정에 의하여 거래
                    관련 관리 의무 관계의 확인 등을 이유로 일정기간 보유하여야
                    할 필요가 있을 경우 아래와 같이 보유합니다. <br />대금결제
                    및 재화 등의 공급에 관한 기록 : 5년</span
                  ><br /><br />※ 비회원 예매서비스 제공을 위한 최소한의
                  개인정보이며 거부할 수 있습니다. 다만, 수집에 동의하지 않을
                  경우 서비스 이용이 제한됩니다.
                </div>
                <div class="agreement_check_box content-right">
                  <input
                    type="radio"
                    name="agreeCheck2"
                    id="privacyOk01"
                    checked
                    value="agree"
                  /><label for="privacyOk01">동의</label
                  ><input
                    type="radio"
                    name="agreeCheck2"
                    id="privacyOk02"
                    value="degree"
                  /><label for="privacyOk02">동의하지않음</label>
                </div>
              </div>
            </div>
            <div class="btn_btm_wrap content-right">
              <router-link to="/signup"
                ><button
                  href="#none"
                  class="btn_col3 ty6"
                  title="회원가입 페이지 이동"
                >
                  회원가입
                </button></router-link
              >
              &nbsp;<button
                href="#none"
                class="btn_col2 ty6"
                title="비회원 로그인 페이지 이동"
                @click="nonUserCheckReservation()"
              >
                비회원 예매확인
              </button>
            </div>
          </div>


            <!-- 예매 티켓 확인 시작 -->
    <div v-if="showTicket" style="">
      <div class="cc_tab_content" style="width:980px">

        <!-- 영화 이미지 -->
        <div class="cc_movie_img" style="width: 350px;height:500px;float: left;">
          <img
            :src="poster"
            style="width: 350px; height: 500px"
            alt="예약한 티켓이 없습니다."
          />
        </div>

        <!-- 영화정보 테이블 -->
        <div v-for="(data, index) in ticket" :key="index" style="width:400px; height: 500px; float: left;">
        <div class="cc_movie_info">
          <table class="tb_acc_wrap">
            <colgroup>
              <col style="width: 15%" />
              <col style="width: auto" />
            </colgroup>
            <tbody>
              <tr>
                <th>제목</th>
                <td>
                  <div class="font">{{ data.tkTitle }}</div>
                </td>
              </tr>
              <tr>
                <th>일시</th>
                <td>
                  <div class="font">  {{ startDate }}일</div>
                  <div> <br> </div>
                  <div class="font"> 시작시간 : {{ startTime }}</div>
                  <div class="font"> 종료시간 : {{ data.tkEndTime }}</div>
                </td>
              </tr>
              <tr>
                <th>상영관</th>
                <td>
                  <div class="font">{{ data.tkTheater }}</div>
                </td>
              </tr>
              <tr>
                <th>좌석</th>
                <td>
                  <div v-for="(data,index) in seatNum.sort()" :key="index" class="font" >{{ data }}</div>
                </td>
              </tr>
            </tbody>
          </table>

          <div class="cc_pay">
            <span class="cc_pay_tit">결제금액</span>
            <span class="cc_pay_amount">{{price}}원</span>
          </div>
          
        </div>

        <div class="btn_btm_wrap">
          <a class="btn_col3 ty6" @click="cancelTicket(ticketId)">예매취소</a>
          <router-link to="/" class="btn_col2 ty6">확인</router-link>
        </div>

      </div>
    </div>
    </div>
    <!-- 예매 티켓 확인 끝 -->


          </div>
        </div>
      </div>
      <!-- 소셜 로그인 -->
      <div v-show="currentTab == 2" class="tab-continer member_login_wrap">
        <div class="tab-content3 login_box">
          <div class="mt-3">
            <a
              href="http://localhost:8000/oauth2/authorization/naver"
              role="button"
              title="네이버 계정으로 새창열기"
              class="btn_naver_social"
              >네이버 계정으로 로그인</a
            >
          </div>
          <div class="login_bot_wrap">
            <div class="login_menu">
              <!-- TODO: 아이디 찾기 비밀번호 찾기 페이지 추가 -->
              <router-link to="/signup">회원가입</router-link>
              <router-link to="/findid">아이디 찾기</router-link>
              <router-link to="/findpw">비밀번호 찾기</router-link>
            </div>
          </div>
          <!-- 카카오는 어플 인증이 필요 -->
          <!-- <div class="mt-3">
            <a
              href="http://localhost:8000/oauth2/authorizatsetion/kakao"
              role="button"
              ><button class="btn_naver_social">
                카카오 계정으로 로그인
              </button></a
            >
          </div> -->
        </div>
      </div>
    </div>

    <!-- 본문 끝-->
  </div>
</template>

<script>
/* eslint-disable */
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom.js";
import User from "@/models/user";
import nonUser from "@/models/nonUser";
import ReservationCheckService from "@/services/ReservationCheckService.js"
import userService from "@/services/userServices/user.service";

export default {
  components: {
    NavBlack,
  },

  mounted() {
    initCustom();
  },
  data() {
    return {
      // 멤버로그인 데이터
      user: new User("", ""),
      IDPassowrdCheck: "",
      // 비회원로그인 데이터
      nonUser: new nonUser("", "", "", ""),
      birthyy: "",
      birthmm: "",
      birthdd: "",
      // 비회원로그인 validate 데이터
      namePass: false,
      nameCheck: "",
      phonePass: false,
      phoneCheck: "",
      phoneCheckNum: "",
      passwordCheck: "",
      passwordCheck2: "",
      passwordPass: false,
      passwordPass2: false,
      birthDatePass: false,
      birthDateCheck: "",
      privacyPass: true,
      privacyCheck: "",
      passwordChecker: "",
      // 비회원 예매확인 데이터
      nonUserReserNames: "",
      nonUserReserPhone: "",
      nonUserReserPassword: "",
      privacyPass2: true,
      privacyCheck2: "",
      nonUserReserError: "",
      // 로그인 탭 변경 데이터
      currentTab: 0,
      currentTab2: 0,
      navtab1: "nav-item active",
      navtab2: "nav-item",
      navtab3: "nav-item",
      navtab_tab1: "nav-item active",
      navtab_tab2: "nav-item",
      // 아이디 저장 데이터
      saveID: true,
      // 티켓관련 데이터
      showTicket:false,
      ticket: [],
      currentIndex: -1,
      userId:'',
      phone:'',
      password:'',
      price:'',
      startTime:'',
      startDate:'',
      seatNum:'',
      poster:'',
      ticketId:'',
    };
  },
  created() {
    if (this.loggedIn || this.loggedIn2) {
      this.$router.push("/");
    }
    // 저장된 아이디 불러오기
    this.user.username = JSON.parse(localStorage.getItem("saveUserID"));
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    loggedIn2() {
      return this.$store.state.auth.initialState.status.loggedIn2;
    },
    birthDate() {
      return (this.nonUser.birthDate =
        this.birthyy + this.birthmm + this.birthdd);
    },
  },
  methods: {
    
    cancelTicket(ticketId){

console.log("티켓아이디"+this.ticketId);

ReservationCheckService.cancelReservationTicket(ticketId)
.then(response=>{
  console.log("취소하고 받은 값"+response.data)
  alert("예약이 취소 되었습니다.")
  this.$router.push("/");
})
.catch(e=>{
  console.log(e)
})
},



    // 탭변경시 경고문 리셋
    checkReset() {
      if (this.currentTab == 0 || this.currentTab2 == 0) {
        return (
          (this.IDPassowrdCheck = ""),
          (this.nameCheck = ""),
          (this.phoneCheck = ""),
          (this.passwordCheck = ""),
          (this.passwordCheck2 = ""),
          (this.birthDateCheck = ""),
          (this.privacyCheck = ""),
          (this.privacyCheck2 = ""),
          (this.nonUserReserError = "")
          (this.showTicket=false)
        );
      } else if (this.currentTab == 1 || this.currentTab2 == 1) {
        return (
          (this.IDPassowrdCheck = ""),
          (this.nameCheck = ""),
          (this.phoneCheck = ""),
          (this.passwordCheck = ""),
          (this.passwordCheck2 = ""),
          (this.birthDateCheck = ""),
          (this.privacyCheck = ""),
          (this.privacyCheck2 = ""),
          (this.nonUserReserError = "")
          (this.showTicket=false)
        );
      } else if (this.currentTab == 2) {
        return (
          (this.IDPassowrdCheck = ""),
          (this.nameCheck = ""),
          (this.phoneCheck = ""),
          (this.passwordCheck = ""),
          (this.passwordCheck2 = ""),
          (this.birthDateCheck = ""),
          (this.privacyCheck = ""),
          (this.privacyCheck2 = ""),
          (this.nonUserReserError = "")
          (this.showTicket=false)
        );
      }
    },
    // 회원 로그인
    handleLogin() {
      // 유저이름과 패스워드가 모두 있으면 공유함수 실행
      var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
      if (special_pattern.test(this.user.username) == false) {
        if (this.user.username && this.user.password) {
          this.IDSaves();
          // 아이디 저장 확인
          // console.log("저장여부는"+this.saveID)
          // console.log("저장된아이디는"+JSON.parse(localStorage.getItem('saveUserID')))
          this.getUser(this.user.username);
              console.log("로그인 정보 가져옴")
          this.$store
            .dispatch("auth/login", this.user)
            .then(() => {
              
              this.$router.push("/");
              this.$router.go();
            })
            .catch(() => {
              this.IDPassowrdCheck =
                " 아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.";
            });
        } else if (this.user.username && !this.user.password) {
          this.IDPassowrdCheck = "*비밀번호를 입력해주세요.";
        } else if (!this.user.username) {
          this.IDPassowrdCheck = "*아이디를 입력해주세요.";
        }
      } else {
        this.IDPassowrdCheck = "*유효하지 않은 아이디입니다.";
      }
    },
    // 아이디 저장 함수
    IDSaves() {
      if (this.saveID) {
        return localStorage.setItem(
          "saveUserID",
          JSON.stringify(this.user.username)
        );
      } else {
        return localStorage.removeItem("saveUserID");
      }
    },
    nonUserLogin() {
      this.nameValidate();
      this.phoneValidate();
      this.birthDateValidate();
      this.passwordValidate();
      this.privacyValidate();
      if (this.privacyPass) {
        if (
          this.namePass &&
          this.phonePass &&
          this.birthDatePass &&
          this.passwordPass &&
          this.passwordPass2 &&
          this.privacyPass
        ) {
          // 이름 폰번호 예매비밀번호
          this.$store
            .dispatch("auth/login2", this.nonUser)
            .then(() => {
              this.$router.push("/");
            })
            .catch(() => {
              alert("입력정보를 확인하여 주세요.");
            });
          this.$router.push("/");
        } else {
          alert("입력정보를 확인하여 주세요.");
        }
      } else {
        alert(this.privacyCheck);
      }
    },
    // 비회원 예매확인
    nonUserCheckReservation() {
      this.privacyValidate2();
      if (this.privacyPass2) {
        if (
          this.nonUserReserNames &&
          this.nonUserReserPassword &&
          this.nonUserReserPhone
        ) {
          // 비회원 예매 get api 실행
          ReservationCheckService.getMovieReservationCheckNonmem(this.nonUserReserPhone , this.nonUserReserPassword)
          .then(response=>{
            console.log("받은 예매표 값 : "+response.data);
            this.showTicket =true;
            this.ticket=response.data.Ticket;
        this.price = this.ticket[0].tkPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        let day = this.ticket[0].tkStartTime.split("일");
        this.startDate=day[0];
        console.log("일"+day[0])
        console.log("시간"+day[1])
        this.startTime=day[1];
        this.seatNum = this.ticket[0].tkSeat.split("좌석");
        this.poster = response.data.PosterUri;
        this.ticketId=this.ticket[0].tkId;
          })
          .catch(e=>{
            console.log(e);
          })
        } else {
          return (this.nonUserReserError =
            "이름과 휴대폰 번호,예매 비밀번호를 확인해주세요.");
        }
      } else {
        alert(this.privacyCheck2);
      }
    },
    // 비회원 로그인 validate
    nameValidate() {
      if (this.nonUser.names) {
        return (this.namePass = true), (this.nameCheck = "");
      } else {
        return (
          (this.namePass = false), (this.nameCheck = "*이름을 입력해주세요.")
        );
      }
    },
    phoneValidate() {
      if (!isNaN(this.nonUser.phone) && this.nonUser.phone.length < 10) {
        return (
          (this.phoneCheck = "*유효한 휴대폰 번호를 입력해주세요"),
          (this.phonePass = false)
        );
      } else {
        return (this.phoneCheck = ""), (this.phonePass = true);
      }
    },
    birthDateValidate() {
      if (this.birthDate.length == 8) {
        (this.birthDatePass = true),
          (this.birthDateCheck = ""),
          (this.brithDatePass = true);
      } else {
        return (
          (this.birthDatePass = false),
          (this.birthDateCheck = "*생년월일을 정확하게 선택하여 주세요."),
          (this.brithDatePass = false)
        );
      }
    },
    passwordValidate() {
      if (!isNaN(this.nonUser.password) && this.nonUser.password.length == 4) {
        return (this.passwordPass = true), (this.passwordCheck = "");
      } else {
        return (
          (this.passwordPass = false),
          (this.passwordCheck = "*예매비밀번호를 확인해주세요.")
        );
      }
    },
    passwordValidate2() {
      if (
        this.nonUser.password === this.passwordChecker &&
        this.passwordChecker
      ) {
        return (this.passwordPass2 = true), (this.passwordCheck2 = "");
      } else {
        return (
          (this.passwordPass2 = false),
          (this.passwordCheck2 = "*예매비밀번호가 일치하지않습니다.")
        );
      }
    },
    privacyValidate() {
      if (
        document.querySelector('input[name="agreeCheck1"]:checked').value ==
        "agree"
      ) {
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return (this.privacyPass = true), (this.privacyCheck = "");
      } else {
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return (
          (this.privacyPass = false),
          (this.privacyCheck = "약관에 동의하지않으면 이용에 제한됩니다.")
        );
      }
    },
    //  비회원 예매확인 privacy Validate
    privacyValidate2() {
      if (
        document.querySelector('input[name="agreeCheck2"]:checked').value ==
        "agree"
      ) {
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return (this.privacyPass2 = true), (this.privacyCheck2 = "");
      } else {
        // alert(document.querySelector('input[name="agreeCheck"]:checked').value)
        return (
          (this.privacyPass2 = false),
          (this.privacyCheck2 = "약관에 동의하지않으면 이용에 제한됩니다.")
        );
      }
    },
    getUser(username) {
      userService
        .getUserProfile(username)
        .then((reponse) => {
          localStorage.setItem("user",JSON.stringify(reponse.data))
          console.log("로그인후 등록 데이터"+JSON.stringify(reponse.data))
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

.nav-item {
  padding: 10px;
  width: 200px;
  text-align: center;
}

.tab-box {
  margin-top: 100px;
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
/* 소셜 로그인 이미지 넣는 부분 */
.btn_naver_social::after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 56px;
  height: 50px;
  border-right: 1px solid #ddd;
  background: url("../../assets/images/login/social_naver.png") no-repeat center
    center;
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
  width: 500px;
}
.tb_acc_wrap {
  height: 379px;
  margin: auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
  /* border-bottom: 1px solid #eee; */
}
.tb_acc_wrap th {
  font-size: 15px;
  color: #666666;
  text-align: center;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  padding: 5px 0;
}
.tb_acc_wrap td {
  text-align: left;
  font-size: 19px;
  font-weight: bold;
  padding-left: 40px;
}
.cc_pay {
  border-top: 1px solid lightgray;
  padding-bottom: 10px;
  margin-left: 5px;
  width: 395px;
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
  float: right;
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

tr{
  height: 55px;
}

</style>