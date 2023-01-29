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

      <!-- 공지사항 파트 시작 -->
      <!-- v-if 제출 전 화면 -->
      <div v-if="!submitted">
        <div class="cc_tab_content">
          <div class="cc_title_wrap">
            <div class="cc_title_sub">
              <h4 class="cc_tit_h4">영화 추가</h4>
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
                <th scope="row">영화코드<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="영화코드를 입력해주세요"
                      title="영화코드 입력"
                      id="mcode"
                      v-model="mcode"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">상영등급<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="상영등급을 입력해주세요"
                      title="상영등급 입력"
                      id="mage"
                      v-model="mage"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">제목 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="제목을 입력해주세요"
                      title="영화제목 입력"
                      id="mtitle"
                      v-model="mtitle"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">시놉시스 <span class="txt_req">*</span></th>
                <td>
                  <div class="qna_textarea">
                    <textarea
                      class="ty2"
                      cols="10"
                      rows="10"
                      id="msimpleInfo"
                      title="영화의 시놉시스(줄거리)를 입력해주세요"
                      placeholder="시놉시스(간략 줄거리)를 입력해주세요."
                      v-model="msimpleInfo"
                      required
                    ></textarea>
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">감독 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="영화 감독을 입력해주세요"
                      title="영화감독 입력"
                      id="mdirector"
                      v-model="mdirector"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">배우 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="영화에 출연한 배우들을 입력해주세요"
                      title="영화배우 입력"
                      id="mactor"
                      v-model="mactor"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">상영시간 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="상영시간을 입력해주세요"
                      title="상영시간 입력"
                      id="mrunningTime"
                      v-model="mrunningTime"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">상영 시작일 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="상영 시작일을 입력해주세요"
                      title="상영시작일 입력"
                      id="mreleaseDate"
                      v-model="mreleaseDate"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">상영 종료일 <span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="상영 종료일을 입력해주세요"
                      title="상영종료일 입력"
                      id="mclosingDate"
                      v-model="mclosingDate"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">트레일러 링크</th>
                <td>
                  <div class="bx_textarea">
                    <input
                      type="text"
                      class="ty2 w_full"
                      placeholder="링크를 입력해주세요"
                      title="트레일러 링크 입력"
                      id="mtrailerLink"
                      v-model="mtrailerLink"
                      required
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="row">영화 포스터<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file"
                          @change="selectImage"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage"
                        width="300px"
                        height="auto"
                        alt="poster"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
              <tr>
                <th scope="row">영화 이미지1<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file1"
                          @change="selectImage1"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage1">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage1"
                        width="700px"
                        height="auto"
                        alt="image1"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
              <tr>
                <th scope="row">영화 이미지2<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file2"
                          @change="selectImage2"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage2">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage2"
                        width="700px"
                        height="auto"
                        alt="image2"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
              <tr>
                <th scope="row">영화 이미지3<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file3"
                          @change="selectImage3"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage3">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage3"
                        width="700px"
                        height="auto"
                        alt="image3"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
              <tr>
                <th scope="row">영화 이미지4<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file4"
                          @change="selectImage4"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage4">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage4"
                        width="700px"
                        height="auto"
                        alt="image4"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
              <tr>
                <th scope="row">영화 이미지5<span class="txt_req">*</span></th>
                <td>
                  <div class="bx_textarea">
                    <div class="mb-3 col-md-5">
                      <label class="btn btn-default p-0">
                        <input
                          type="file"
                          accept="image/*"
                          ref="file5"
                          @change="selectImage5"
                          class="uploaded_poster"
                          required
                        />
                      </label>
                    </div>
                  </div>
                  <!-- 미리보기 이미지 시작 -->
                  <div v-if="previewImage5">
                    <div>
                      <img
                        class="preview my-3"
                        :src="previewImage5"
                        width="700px"
                        height="auto"
                        alt="image5"
                      />
                    </div>
                  </div>
                  <!-- 미리보기 이미지 끝 -->
                </td>
              </tr>
            </tbody>
          </table>

          <div class="btn_btm_wrap">
            <router-link class="btn_col3 ty6" to="/admin-movie"
              >취소</router-link
            >
            <button class="btn_col2 ty6" @click="upload">추가하기</button>
          </div>
        </div>
      </div>

      <!-- v-else 제출 후 화면 -->
      <div v-else>
        <div class="cc_tab_content">
          <div class="alert alert-success" role="alert">
            영화가 성공적으로 등록되었습니다!
          </div>
          <div class="btn_btm_wrap">
            <router-link class="btn_col2 ty6" to="/admin-movie"
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
import AdMovieDataService from "@/services/AdMovieDataService";

export default {
  data() {
    return {
      currentItmage: new Blob([0],{type: "image/jpg"}),
      currentImage1: new Blob([0],{type: "image/jpg"}),
      currentImage2: new Blob([0],{type: "image/jpg"}),
      currentImage3: new Blob([0],{type: "image/jpg"}),
      currentImage4: new Blob([0],{type: "image/jpg"}),
      currentImage5: new Blob([0],{type: "image/jpg"}),

      previewImage: undefined,
      previewImage1: undefined,
      previewImage2: undefined,
      previewImage3: undefined,
      previewImage4: undefined,
      previewImage5: undefined,

      message: "",

      mtitle: "",
      mcode: "",
      mrunningTime: "",
      mreleaseDate: "",
      mclosingDate: "",
      mdirector: "",
      mactor: "",
      msimpleInfo: "",
      mtrailerLink: "",
      mage: "",

      submitted: false,
    };
  },
  methods: {
    // 파일 선택상자에서 선택한 이미지를 저장하는 함수
    selectImage() {
      this.currentImage = this.$refs.file.files.item(0);
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.message = "";
    },
    selectImage1() {
      this.currentImage1 = this.$refs.file1.files.item(0);
      this.previewImage1 = URL.createObjectURL(this.currentImage1);
      this.message = "";
    },
    selectImage2() {
      this.currentImage2 = this.$refs.file2.files.item(0);
      this.previewImage2 = URL.createObjectURL(this.currentImage2);
      this.message = "";
    },
    selectImage3() {
      this.currentImage3 = this.$refs.file3.files.item(0);
      this.previewImage3 = URL.createObjectURL(this.currentImage3);
      this.message = "";
    },
    selectImage4() {
      this.currentImage4 = this.$refs.file4.files.item(0);
      this.previewImage4 = URL.createObjectURL(this.currentImage4);
      this.message = "";
    },
    selectImage5() {
      this.currentImage5 = this.$refs.file5.files.item(0);
      this.previewImage5 = URL.createObjectURL(this.currentImage5);
      this.message = "";
    },
    upload() {
      AdMovieDataService.create(
        this.mtitle,
        this.mcode,
        this.mrunningTime,
        this.mreleaseDate,
        this.mclosingDate,
        this.mdirector,
        this.mactor,
        this.msimpleInfo,
        this.mtrailerLink,
        this.mage,
        this.currentImage,
        this.currentImage1,
        this.currentImage2,
        this.currentImage3,
        this.currentImage4,
        this.currentImage5
      )
        // 성공하면 then 으로 들어옴(서버에서 응답한 객체)
        .then((response) => {
          // 서버쪽 응답 메시지 저장
          console.log(response.data);
          this.message = response.data;
          this.submitted = true;
        })
        // 실패하면 catch으로 들어옴 : 화면에 실패메세지 출력
        .catch((err) => {
          console.log(err);
          this.currentImage = undefined;
        });
    },
  },
  components: {
    NavBlack,
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
  padding: 20px 20px;
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

/* select */
.form-select {
  display: inline-block;
}
.form-select:focus {
  border-color: #000;
  outline: 0;
  box-shadow: 0 0 0 0rem rgba(7, 13, 89, 0.25);
}
.cc_noti_select {
  width: 180px;
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

.uploaded_poster {
  color: #fff;
}
</style>
