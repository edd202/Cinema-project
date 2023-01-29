<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <!-- 대제목 시작 -->
    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h2 class="cc_tit">고객센터</h2>
      </div>
    </div>
    <!-- 대제목 끝 -->

    <!-- 3대장 시작 -->
    <div class="container col-6">
      <ul class="cc_tab_wrap cc_outer">
        <div class="row">
          <div class="col">
            <li class="nav-item">
              <router-link to="/customer-center">
                <button class="cc_tab_tit" type="button" style="width: 100%">
                  <span>FAQ</span>
                </button>
              </router-link>
            </li>
          </div>
          <div class="col">
            <li class="nav-item">
              <router-link to="/notice">
                <button class="cc_tab_tit" type="button" style="width: 100%">
                  <span>공지사항</span>
                </button>
              </router-link>
            </li>
          </div>
          <div class="col">
            <li class="nav-item">
              <router-link to="/qna">
                <button
                  class="cc_tab_tit active"
                  type="button"
                  style="width: 100%"
                >
                  <span>1:1문의</span>
                </button>
              </router-link>
            </li>
          </div>
        </div>
      </ul>
    </div>
    <!-- 3대장 끝 -->

    <!-- AddQnA 시작 -->

    <!-- v-if 제출 전 화면 -->
    <div v-if="!submitted">
      <!-- 질문, 질문자 -> 로그인 필요서비스.. 아무튼 질문자는 로그인 이름or아이디 -->
      <!-- 답변, 답변자는 고객 권한x 여기선 필요없음 -->
      <div class="cc_tab_content">
        <div class="con_top">
          <div class="ico_tit qus">
            FAQ를 이용하시면 궁금증을 더 빠르게 해결하실 수 있습니다.
            <ul class="cc_qna_list_txt">
              <li>* 1:1 문의글 답변 운영시간 10:00 ~ 19:00</li>
              <li>* 문의에 대한 답변은 회원님의 이메일로도 전송됩니다.</li>
            </ul>
          </div>
        </div>

        <dl class="contxt_type1">
          <dt class="cc_qna_tit">
            고객님의 문의에
            <span class="txt_color02"
              >답변하는 직원은 고객 여러분의 가족 중 한 사람</span
            >일 수 있습니다.
          </dt>
          <dd class="desc">
            고객의 언어폭력(비하, 욕설, 반말, 성희롱 등)으로부터 직원을 보호하기
            위해 관련 법에 따라 수사기관에 필요한 조치를 요구할 수 있으며,
            형법에 의해 처벌 대상이 될 수 있습니다.
          </dd>
        </dl>

        <div class="cc_title_wrap">
          <div class="cc_title_top">
            <h4 class="cc_tit_h4">문의내용</h4>
            <div class="group_rgt"><span class="txt_req">* 필수입력</span></div>
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
                    v-model="qna.subject"
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
                    title="문의내용을 입력해주세요"
                    placeholder="내용 및 첨부파일에 개인정보(카드번호, 계좌번호, 주민번호)가 포함되지 않도록 유의하여 입력해주세요."
                    v-model="qna.text"
                    required
                  ></textarea>
                </div>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="btn_btm_wrap">
          <router-link class="btn_col3 ty6" to="/qna">취소</router-link>
          <button class="btn_col2 ty6" @click="saveQna">확인</button>
        </div>
      </div>
    </div>

    <!-- v-else 제출 후 화면 -->
    <div v-else>
      <div class="cc_tab_content">
        <div class="alert alert-success" role="alert">
          1:1문의가 성공적으로 접수되었습니다!
        </div>
        <div class="btn_btm_wrap">
          <router-link class="btn_col2 ty6" to="/qna">확인</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
// axios 공통함수
import QnaDataService from "@/services/QnaDataService";

export default {
  data() {
    return {
      qna: {
        qid: null,
        subject: "",
        text: "",
      },
      // submit 버튼을 클릭하면 true
      submitted: false,
    };
  },
  components: {
    NavBlack,
  },
  methods: {
    saveQna() {
      // 임시 객체 변수 -> springboot 전송
      // 문의번호는 자동생성되므로 빼고 전송함
      let id =JSON.parse(localStorage.getItem("user")).id
      console.log(id)
      let data = {
        writer:id,
        subject: this.qna.subject,
        text: this.qna.text,
      };

      // insert 요청 함수 호출(axios 공통함수 호출)
      QnaDataService.create(data)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          this.qna.qid = response.data.qid;
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
  border: 0px solid #ddd;
  cursor: pointer;
  background-color: #fff;
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
input[type="password"].ty2,
input[type="text"].ty2,
input[type="number"].ty2,
input[type="tel"].ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
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

/* 3가지 버튼 css */
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
.cc_tab_wrap {
  position: relative;
}
.cc_tab_tit {
  height: 50px;
  color: #000;
}
.cc_tab_tit.active {
  border-bottom: 2px solid #111;
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
}
.tb_acc_wrap thead th {
  border-bottom-color: #bbbbbb;
  font-size: 15px;
  font-weight: 900;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  padding: 15px 0;
  font-size: 15px;
  text-align: center;
  line-height: 24px;
}

/* qna css */
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
  color: #000;
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
  margin: 60px 0;
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
