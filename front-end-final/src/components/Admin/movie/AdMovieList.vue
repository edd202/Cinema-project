<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div class="admin-background">
      <!-- 대제목 시작 -->
      <div class="cc_title_wrap">
        <div class="cc_title_top">
          <h2 class="cc_tit">
            관리자 - <span style="font-size: 16px">영화목록</span>
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
                  <button
                    class="cc_tab_tit active"
                    type="button"
                    style="width: 100%"
                  >
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

      <!-- 영화목록 시작 -->
      <div class="cc_tab_content">
        <!-- 영화이미지 불러오기 디자인 시작 -->
        <!-- <div class="row">
          <div class="col-sm-4" v-for="(data, index) in movie" :key="index">
            <div class="card">
              <img :src="data.mposterUrl" class="card-img-top" alt="poster" />
              <div class="card-body">
                <h5 class="card-title">{{ data.mtitle }}</h5>
                <router-link
                  style="color: inherit"
                  :to="'/admin-movie/' + data.mid"
                >
                  <span class="badge bg-danger">수정</span>
                </router-link>
              </div>
            </div>
          </div>
        </div> -->
        <!-- <!— 쇼핑 카트 형태 디자인 끝 —> -->

        

        <!-- 영화목록 검색 시작 -->
        <fieldset class="cc_search_wrap ty2">
          <input
            type="text"
            placeholder="영화제목을 입력해주세요."
            id="searchKeyword"
            title="검색어를 입력해주세요"
            v-model="searchTitle"
          />
          <button
            type="button"
            class="cc_btn_col2"
            @click="
              page = 1;
              retrieveMovie();
            "
          >
            검색
          </button>
        </fieldset>
        <!-- 영화목록 검색 끝 -->

        <!-- 영화목록 테이블 시작 -->
        <table class="tb_acc_wrap">
          <thead>
            <tr>
              <th>ID</th>
              <th>제목</th>
              <th>상영 시작일</th>
              <th>상영 종료일</th>
              <th>정보변경</th>
            </tr>
          </thead>
          <tbody v-for="(data, index) in movie" :key="index">
            <tr>
              <td width="10%">{{ data.mid }}</td>
              <td class="text-start" width="auto">{{ data.mtitle }}</td>
              <td width="15%">{{ data.mreleaseDate }}</td>
              <td width="15%">{{ data.mclosingDate }}</td>
              <td width="15%">
                <router-link :to="'/admin-movie/' + data.mid">
                  <span class="badge bg-danger">수정</span>
                </router-link>
              </td>
            </tr>
          </tbody>
        </table>
        <!-- 영화목록 테이블 끝 -->

        <!--    Todo : page 바 시작 -->
        <div class="col-md-12 mt-5">
          <b-pagination
            v-model="page"
            :total-rows="count"
            :per-page="pageSize"
            @change="handlePageChange"
          ></b-pagination>
          <!-- :per-page="pageSize" -->
        </div>
        <!-- <!—    Todo : page 바 끝 —> -->

        <div class="btn_btm_wrap">
          <router-link to="/add/admin-movie" class="btn_col2 ty6"
            >영화 추가</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
import AdMovieDataService from "@/services/AdMovieDataService";

export default {
  data() {
    return {
      movie: [],
      searchTitle: "",

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
    retrieveMovie() {
      AdMovieDataService.getAll(this.searchTitle, this.page - 1, this.pageSize)
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          const { movie, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.movie = movie; // 스프링부트에서 전송한 데이터
          this.count = totalItems; // 스프링부트에서 전송한 페이지정보(총 건수)
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      this.retrieveMovie();
    },
  },
  mounted() {
    initCustom();
    this.retrieveMovie();
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

/* 버튼 css */
.btn_btm_wrap {
  margin-top: 90px;
  padding-bottom: 60px;
  text-align: center;
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

/* slider 커서 css */
#destination-controls:active,
#destination-controls:focus {
  outline: none;
}

#destination-controls .ad_next,
#destination-controls .ad_prev {
  cursor: pointer;
  display: inline-block;
  position: relative;
  -webkit-transition: 0.3s all ease;
  -o-transition: 0.3s all ease;
  transition: 0.3s all ease;
  width: 40px;
  height: 40px;
  background-color: #a80000;
  border-radius: 50%;
}
#destination-controls .ad_next > span,
#destination-controls .ad_prev > span {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
#destination-controls .ad_next:hover,
#destination-controls .ad_prev:hover {
  opacity: 1;
}
#destination-controls .ad_next:hover,
#destination-controls .ad_prev:hover {
  background-color: #7d00b8;
  color: #fff;
}

#destination-controls .ad_prev {
  right: 0;
}
#destination-controls .ad_prev:hover {
  color: #fff;
  right: 7px;
}

#destination-controls .ad_next {
  left: 0;
}
#destination-controls .ad_next:hover {
  color: #fff;
  left: 7px;
}

/* slider content css */
.destination {
  position: relative;
}
.destination .thumb {
  position: relative;
  border-radius: 10px;
  overflow: hidden;
}
.destination .thumb:before {
  position: absolute;
  content: "";
  z-index: 1;
  bottom: 0;
  left: 0;
  height: 50%;
  width: 100%;
  opacity: 0.5;
  background: black;
  background: -webkit-gradient(
    linear,
    left bottom,
    left top,
    from(black),
    to(rgba(0, 212, 255, 0))
  );
  background: -webkit-linear-gradient(
    bottom,
    black 0%,
    rgba(0, 212, 255, 0) 100%
  );
  background: -o-linear-gradient(bottom, black 0%, rgba(0, 212, 255, 0) 100%);
  background: linear-gradient(0deg, black 0%, rgba(0, 212, 255, 0) 100%);
}
.destination .modi {
  position: absolute;
  right: 0;
  bottom: 0;
  display: inline-block;
  margin: 0 5px 5px 0;
  padding: 5px;
  font-size: 17px;
  background-color: #a80000;
  color: #fff;
  border-radius: 6px;
  z-index: 2;
}

.destination h3 a,
.destination .h3 a {
  color: #343661;
}
.destination .meta {
  color: #999;
}

.heading {
  font-size: 40px;
  color: #fff;
  font-weight: 700;
}
.pagination {
  justify-content: center;
}
</style>
