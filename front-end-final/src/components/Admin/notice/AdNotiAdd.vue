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

      <!-- 공지사항 파트 시작 -->
      <!-- v-if 제출 전 화면 -->
      <div v-if="!submitted">
        <div class="cc_tab_content">
          <div class="cc_title_wrap">
            <div class="cc_title_sub">
              <h4 class="cc_tit_h4">공지사항 추가</h4>
              <div class="group_rgt">
                <span class="txt_req">* 필수입력</span>
              </div>
            </div>
          </div>

          <table class="tb_acc_wrap">
            <colgroup>
              <col style="width: 15%" />
              <col style="width: auto" />
            </colgroup>
            <tbody>
              <tr>
                <th scope="row">제목 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="제목을 입력해주세요"
                      title="문의내용 제목입력"
                      id="iDtitle"
                      v-model="notice.ntcTitle"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">내용 <span class="txt_req">*</span></th>
                <td>
                  <div class="qna_textarea">
                    <textarea
                      class="ty2"
                      cols="10"
                      rows="10"
                      id="iDcontents"
                      v-model="notice.ntcContent"
                      title="공지사항의 내용을 입력해주세요"
                      placeholder="내용을 입력해주세요."
                      required
                    ></textarea>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>

          <div class="btn_btm_wrap">
            <router-link class="btn_col3 ty6" to="/admin-notice"
              >취소</router-link
            >
            <button class="btn_col2 ty6" @click="saveNotice">저장</button>
          </div>
        </div>
      </div>

      <!-- v-else 제출 후 화면 -->
      <div v-else>
        <div class="cc_tab_content">
          <div class="alert alert-success" role="alert">
            공지사항이 성공적으로 등록되었습니다!
          </div>
          <div class="btn_btm_wrap">
            <router-link class="btn_col2 ty6" to="/admin-notice"
              >확인</router-link
            >
          </div>
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
  data() {
    return {
      notice: {
        ntcNo: null,
        ntcTitle: "",
        ntcContent: "",
      },
      submitted: false,
    };
  },
  components: {
    NavBlack,
  },
  methods: {
    saveNotice() {
      // 임시 객체 변수 -> springboot 전송
      // 문의번호는 자동생성되므로 빼고 전송함
      let data = {
        ntcTitle: this.notice.ntcTitle,
        ntcContent: this.notice.ntcContent,
      };

      // insert 요청 함수 호출(axios 공통함수 호출)
      AdNoticeDataService.create(data)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          this.notice.ntcNo = response.data.ntcNo;
          // 콘솔 로그 출력(response.data)
          console.log(response.data);
          // 변수 submitted
          this.submitted = true;
        })
        // 실패하면 .catch() 결과가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    initCustom();
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
.cc_title_sub {
  width: 980px;
  margin: auto;
  height: 50px;
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
}
.tb_acc_wrap thead th {
  border-top: 1px solid #bbbbbb;
  border-bottom-color: #bbbbbb;
  font-weight: bold;
}

/* Add css */
.con_top {
  position: relative;
  background-color: #f8f8f8;
  padding: 35px 0 10px 0;
  text-align: center;
}
.cc_btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  width: 60px;
  height: 38px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}
.con_top .ico_tit.qus {
  background-image: url(@/assets/images/customer/ic_qus.png);
  background-position: top 5% left;
}
.con_top .ico_tit {
  display: inline-block;
  background-repeat: no-repeat;
  background-position: top 50% left;
  min-width: 250px;
  min-height: 35px;
  line-height: 1.4;
  padding-left: 55px;
  text-align: left;
  font-size: 15px;
  font-weight: 600;
}
.cc_qna_list_txt {
  font-size: 13px;
  color: #707070;
  margin-top: 10px;
}
.contxt_type1 {
  margin: 20px 0 50px 0;
}
.contents_customer .contxt_type1 .tit {
  padding-bottom: 5px;
  font-size: 15px;
  color: #000;
}
.txt_color02 {
  color: #ff7787 !important;
}
.contxt_type1 .desc {
  font-size: 12px;
  color: #707070;
  line-height: 1.3;
}
.cc_tit_h4 {
  float: left;
  font-size: 22px;
  color: #fff;
  line-height: 50px;
}
.tit {
  display: inline-block;
}
.group_rgt {
  float: right;
  margin-top: 15px;
}
.txt_req {
  font-size: 14px;
  color: #ff243e;
}
.qna_table {
  caption-side: bottom;
  border-collapse: collapse;
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
.btn_btm_wrap {
  margin-top: 60px;
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
</style>
