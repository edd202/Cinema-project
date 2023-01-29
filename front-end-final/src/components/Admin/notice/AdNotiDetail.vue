<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div class="admin-background">
      <!-- 대제목 시작 -->
      <div class="cc_title_wrap">
        <div class="cc_title_top">
          <h2 class="cc_tit">
            관리자 - <span style="font-size: 16px">공지사항</span>
          </h2>
        </div>
      </div>
      <!-- 대제목 끝 -->

      <!-- 3대장 시작 -->
      <div class="container col-6">
        <ul class="cc_tab_wrap cc_outer">
          <div class="row">
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-movie">
                  <button class="cc_tab_tit" type="button" style="width: 100%">
                    <span>영화목록 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-notice">
                  <button
                    class="cc_tab_tit active"
                    type="button"
                    style="width: 100%"
                  >
                    <span>공지사항 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-qna">
                  <button class="cc_tab_tit" type="button" style="width: 100%">
                    <span>1:1문의 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
          </div>
        </ul>
      </div>
      <!-- 3대장 끝 -->

      <div v-if="currentNotice">
        <!-- 공지사항 세부내용 시작 -->
        <div class="cc_tab_content mt-5">
          <div v-if="!modify">
            <table class="tb_acc_wrap">
              <thead>
                <tr>
                  <th scope="col">
                    {{ currentNotice.ntcTitle }}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr class="tb_info">
                  <td>
                    <span class="tit">영화관</span>
                    <!-- Noti - 구분(Categorie) -->
                    <span class="cont">전체</span>
                    <span class="tit">등록일</span>
                    <span class="cont">{{ currentNotice.insertTime }}</span>
                  </td>
                </tr>
                <tr class="tb_content">
                  <td>
                    <div class="qna_textarea">
                      <pre>{{ currentNotice.ntcContent }}</pre>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>

            <div class="btn_btm_wrap">
              <button @click="deleteNotice" class="btn_col3 ty6" >
                공지삭제
              </button>
              <button @click="modifyNotice" class="btn_col2 ty6" style="background:white !important ; color:black  !important;">
                수정하기
              </button>
              <router-link to="/admin-notice"
                ><button class="btn_col2 ty6">목록</button></router-link
              >
            </div>
          </div>

          <div v-else>
            <table class="tb_acc_wrap">
              <thead>
                <tr>
                  <th scope="col">
                    <input
                    type="text"
                    class="ty2 w_full"
                    id="ntcTitle"
                    required
                    name="ntcTitle"
                    v-model="currentNotice.ntcTitle"
                  />
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr class="tb_info">
                  <td>
                    <span class="tit">영화관</span>
                    <!-- Noti - 구분(Categorie) -->
                    <span class="cont">전체</span>
                    <span class="tit">등록일</span>
                    <span class="cont">{{ currentNotice.insertTime }}</span>
                  </td>
                </tr>
                <tr class="tb_content">
                  <td>
                    <div class="qna_textarea">
                      <textarea
                      class="ty2"
                      cols="10"
                      rows="10"
                      id="ntcContent"
                      v-model="currentNotice.ntcContent"
                      required
                    ></textarea>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>

            <div class="success_message">
              <div class="mb-1 alert alert-success" role="alert" v-if="message">
                {{ message }}
              </div>
            </div>

            <div class="btn_btm_wrap">
              <button @click="deleteNotice" class="btn_col3 ty6">
                공지삭제
              </button>
              <button @click="updateNotice" class="btn_col2 ty6">저장</button>
              <router-link to="/admin-notice"
                ><button class="btn_col2 ty6">목록</button></router-link
              >
            </div>
          </div>
          <!-- 공지사항 세부내용 끝 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
import AdNoticeDataService from "@/services/AdNoticeDataService";

export default {
  components: {
    NavBlack,
  },
  data() {
    return {
      currentNotice: null,
      message: "",
      modify: false,
    };
  },
  methods: {
    // 부서번호(qno)로 조회 요청하는 함수
    getNotice(ntcNo) {
      // axios 공통함수 호출
      AdNoticeDataService.get(ntcNo)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          this.currentNotice = response.data;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    modifyNotice() {
      this.modify = true;
    },
    // 정보를 수정 요청하는 함수
    updateNotice() {
      // axios 공통함수 호출
      AdNoticeDataService.update(this.currentNotice.ntcNo, this.currentNotice)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          this.message = "수정 내역을 성공적으로 저장하였습니다.";
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 정보를 삭제 요청하는 함수
    deleteNotice() {
      // axios 공통함수 호출
      AdNoticeDataService.delete(this.currentNotice.ntcNo)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          // 첫페이지(전체목록_조회_페이지) 강제 이동
          this.$router.push("/admin-notice");
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  // 화면이 뜨자 마자 실행되는 이벤트
  mounted() {
    initCustom();
    this.message = "";
    this.getNotice(this.$route.params.ntcNo);
  },
};
</script>

<style scoped>
/* 포괄 css */
button {
  border: 0px solid #000;
  cursor: pointer;
  background-color: #000;
}
input[type="password"],
input[type="text"],
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
input,
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}
a,
a:link,
a:visited,
a:active,
a:hover {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
ul,
ol {
  list-style: none;
}
.admin-background {
  background-color: #000;
}

/* 대제목 css */
.cc_title_top {
  width: 980px;
  margin: auto;
  height: 105px;
  border-bottom: 1px solid #eee;
}
.cc_title_top .cc_tit {
  float: left;
  font-size: 24px;
  color: #fff;
  line-height: 50px;
}
.cc_tit {
  margin-top: 50px;
}

/* 3가지 버튼 css */
.cc_tab_wrap.cc_outer {
  padding-top: 20px;
}
.cc_tab_wrap {
  position: relative;
}
.cc_tab_tit {
  height: 50px;
  color: #fff;
}
.cc_tab_tit.active {
  border-bottom: 2px solid #fff;
}
.cc_tab_content {
  overflow: inherit;
  position: relative;
  width: 980px;
  height: auto;
  margin: 40px auto;
}

/* Table css */
.tb_acc_wrap {
  width: 980px;
  margin: 5px auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
  color: #fff;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  padding: 15px 20px;
  font-size: 15px;
  line-height: 24px;
}
.tb_acc_wrap thead th {
  border-top: 1px solid #bbbbbb;
  border-bottom-color: #bbbbbb;
  font-weight: bold;
}
.tb_info > td {
  background: #292929;
  color: #fff;
  padding-left: 61px;
}
.tit {
  color: #fff;
}
.tb_info > td .tit:after {
  content: "";
  display: inline-block;
  background: #666;
  width: 1px;
  height: 14px;
  margin: 0 20px;
  vertical-align: middle;
}
.tb_info > td .cont + .tit {
  margin-left: 120px;
}
.tb_content > td {
  padding: 30px 60px;
}
#divNoticeContents p {
  min-height: 26px;
}
.tb_content > td p {
  line-height: 22px;
}
.tb_link > td {
  padding: 0;
}
.tb_link > td a {
  display: block;
  padding: 18px 20px 18px 61px;
  color: #fff;
}
.tb_link > td a .tb_next {
  background-image: url(@/assets/images/icon/arr_up_13.png);
}
.tb_link > td a .tb_prev {
  background-image: url(@/assets/images/icon/arr_dw_13.png);
}
.tb_link > td a .tb_next,
.tb_link > td a .tb_prev {
  background-repeat: no-repeat;
  background-position: 100% 54%;
  margin-right: 30px;
  padding-right: 24px;
  color: #fff;
}

/* 버튼 css */
.btn_btm_wrap {
  margin-top: 60px;
  padding-bottom: 60px;
  text-align: center;
}
.btn_col2.ty6,
.btn_col3.ty6,
.btn_co14.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_btm_wrap a,
.btn_btm_wrap button {
  margin: 0 3px;
  min-width: 160px;
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
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #fff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}

.w_full {
  width: 100%;
}
.qna_textarea {
  position: relative;
}
.qna_textarea textarea {
  margin-bottom: 5px;
}
textarea.ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
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
</style>
