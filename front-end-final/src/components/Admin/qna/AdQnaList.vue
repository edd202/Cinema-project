<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div class="admin-background">
      <!-- 대제목 시작 -->
      <div class="cc_title_wrap">
        <div class="cc_title_top">
          <h2 class="cc_tit">
            관리자 - <span style="font-size: 16px">1:1문의</span>
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
                  <button class="cc_tab_tit" type="button" style="width: 100%">
                    <span>공지사항 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-qna">
                  <button
                    class="cc_tab_tit active"
                    type="button"
                    style="width: 100%"
                  >
                    <span>1:1문의 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
          </div>
        </ul>
      </div>
      <!-- 3대장 끝 -->

      <!-- 1:1문의(QnA) 시작 -->
      <div class="cc_tab_content">
        <!-- QnA 검색 시작 -->
        <fieldset class="cc_search_wrap ty2">
          <input
            type="text"
            placeholder="검색어를 입력해주세요."
            id="searchKeyword"
            title="검색어를 입력해주세요"
            v-model="searchSubject"
          />
          <button
            type="button"
            class="cc_btn_col2"
            @click="
              page = 1;
              retrieveQna();
            "
          >
            검색
          </button>
        </fieldset>
        <!-- QnA 검색 끝 -->

        <!-- 관리자페이지는 전체 QnA 목록 불러오면 됨. -->

        <table class="tb_acc_wrap">
          <thead>
            <tr>
              <th>번호</th>
              <th>등록일</th>
              <th>제목</th>
              <th>답변</th>
            </tr>
          </thead>
          <tbody v-for="(data, index) in qna" :key="index">
            <tr>
              <td width="10%">{{ data.qid }}</td>
              <td width="20%">{{ data.insertTime }}</td>
              <td class="text-start" width="auto">
                <!-- 임시로 router-link 설정 -> 이후에 바꿔야함 -->
                <!-- <router-link :to="'/qna/' + data.qno"> -->
                <router-link style="color: #fff;" :to="'/admin-qna/' + data.qid"
                  ><span class="cc_noti_title">{{
                    data.subject
                  }}</span></router-link
                >
              </td>
              <td width="10%">
                <router-link :to="'/admin-qna/' + data.qid">
                  <span class="badge bg-success">답변</span>
                </router-link>
              </td>
            </tr>
          </tbody>
        </table>

        <!--    Todo : page 바 시작 -->
        <div class="col-md-12 mt-5">

          <b-pagination
            v-model="page"
            :total-rows="count"
            :per-page="pageSize"
            @change="handlePageChange"
          ></b-pagination>
        </div>
        <!-- <!—    Todo : page 바 끝 —> -->

      </div>
    </div>
    <!-- 1:1문의(QnA) 끝 -->
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
import AdQnaDataService from "@/services/AdQnaDataService";

export default {
  data() {
    return {
      qna: [],
      searchSubject: "",

      // 페이징을 위한 변수 정의
      page: 1, // 현재 페이지
      count: 0, // 전체 데이터 건수
      pageSize: 10, // 한페이지당 몇개를 화면에 보여줄지 결정하는 변수

    };
  },
  components: {
    NavBlack,
  },
  methods: {
    // axios , 모든 정보 조회 요청 함수
    retrieveQna() {
      AdQnaDataService.getAll(
        this.searchSubject,
        this.page - 1,
        this.pageSize
      )
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          const { qna, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.qna = qna; // 스프링부트에서 전송한 데이터
          this.count = totalItems; // 스프링부트에서 전송한 페이지정보(총 건수)
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // select box 값 변경시 실행되는 함수(재조회)
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; // 한페이지당 개수 저장(3, 6, 9)
      this.page = 1;
      // 재조회 함수 호출
      this.retrieveQna();
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      this.retrieveQna();
    },
  },
  mounted() {
    initCustom();
    this.retrieveQna();
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

/* 검색창 css */
.cc_search_wrap {
  position: relative;
  border: 1px solid #b90808;
  border-radius: 4px;
  background: #292929;
  padding: 22px 22px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  font-size: 0;
}
.cc_search_wrap.ty2 input[type="text"] {
  width: 440px;
  margin-right: 8px;
}
.cc_search_wrap [class^="btn_col"] {
  width: 86px;
  height: 38px;
  line-height: 34px;
  vertical-align: middle;
}
.cc_btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  width: 60px;
  height: 39px;
  line-height: 35px;
  border: 1px solid #af0909;
  font-size: 14px;
  color: #fff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #af0909;
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
  text-align: center;
}
.tb_acc_wrap thead th {
  border-top: 1px solid #bbbbbb;
  border-bottom-color: #bbbbbb;
  font-weight: bold;
}

/* 공지사항 css */
.form-select {
  display: inline-block;
}
.form-select:focus {
  border-color: #000;
  outline: 0;
  box-shadow: 0 0 0 0rem rgba(7, 13, 89, 0.25);
}
.cc_noti_select {
  width: 120px;
  height: 40px;
  margin-right: 8px;
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
.cc_noti_title,
.cc_qna_title {
  padding-left: 10px;
}

/* qna css */
.cc_qna_btn {
  display: block;
  box-sizing: border-box;
  border-radius: 4px;
  width: 100px;
  margin: auto;
  height: 38px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  background-color: #414141;
}

.pagination {
  padding-bottom: 50px;
  justify-content: center;
}
</style>
