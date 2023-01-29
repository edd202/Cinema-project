<template>
  <div>
    <div class="tab_con">
      <h4 class="hidden" style="color: #fff">평점 및 관람평</h4>
      <div class="wrap_reviewstarscore starReg" id="movie_review_box">
        <div class="star_score_box">
          <h4 class="h4_tit">평점 · 관람평 작성</h4>
          <div class="star_info">
            <div class="star_rate type5">
              <star-rating
                v-model="audience.aurate"
                v-bind:increment="1"
                v-bind:max-rating="10"
              ></star-rating>
            </div>
          </div>
          <p class="txt_info">
            영화 관람 후 관람평 작성 시
            <span class="num">POINT 50P</span> 적립
          </p>
        </div>
        <div class="movi_review_box">
          <div class="review_write_box">
            <textarea
              id="txtComment"
              placeholder="평점 및 영화 관람평을 작성해주세요. 주제와 무관한 리뷰 또는 스포일러는 표시제한 또는 삭제될 수 있습니다.
작성하신 평점 및 관람평은 롯데시네마 App, 홈페이지, SNS 등에 인용될 수 있습니다."
              title="관람평 작성"
              maxlength="220"
              v-model="audience.aureview"
            ></textarea
            ><span class="byte_info"
              ><strong class="byte">{{audience.aureview.length}}</strong>/<em>220</em></span
            >
          </div>
          <button type="submit" id="btnComment" class="btn_submit" @click="saveReview()">
            관람평 작성
          </button>
        </div>
      </div>
      <div class="movi_review_list">
        <h5 class="hidden">관람평 목록</h5>
        <div class="review_top">
          <span class="tit"
            ><span class="txt_ic_score"
              ><span class="starimg">관람객 관람평</span></span
            ></span
          >
          <div class="wrap_sort_right">
            <span class="total_num"
              >총 <em>{{ userCount }}</em
              >건</span
            >
            <ul class="sort_list">
              <li id="reviewsortseq1" :class="{ active: isActive }">
                <button class="latest" @click="recentReview()">최신순</button>
              </li>
              <li id="reviewsortseq3" :class="{ active: !isActive }">
                <button class="empathy" @click="agreeReview()">공감순</button>
              </li>
            </ul>
          </div>
        </div>
        <ul class="review_con_list" id="review_con_list1"></ul>
        <ul class="review_con_list" id="review_con_list2">
          <li
            v-for="(reviewer, index) in review.slice().reverse()"
            :key="index"
          >
            <span class="img_info">
              <span
                                  :class="{
                                    'ic_survey gr_01': reviewer.aurate > 7,
                                    'ic_survey gr_02': reviewer.aurate <= 7 && reviewer.aurate > 4 ,
                                    'ic_survey gr_03': reviewer.aurate <= 4,
                                  }"
                                ></span>
              <!-- <img :src="require(emotion(reviewer.aurate))" alt=""/> -->
              </span>
            <div class="top_info">
              <span class="name">{{ reviewer.memName }}</span
              ><span class="txt_ic_score"
                ><span class="stargradebg"
                  ><span :class="'blindbg grade' + reviewer.aurate"
                    ><span class="stargradecolor"></span></span></span
                ><span class="gradenum">{{ reviewer.aurate }}</span></span
              ><span class="date_info">{{
                changeDateStr(reviewer.insertTime)
              }}</span>
            </div>
            <div class="review_info">{{ reviewer.aureview }}</div>
            <div class="btm_info">
              <div class="edit_review"><em></em></div>
              <button
                type="button"
                @click="btnGood(reviewer.audno)"
                class="btn_ic_good"
                :class="{ active: isActive2 }"
                :id="'reviewid_' + reviewer.audno"
              >
                <em>좋아요</em
                ><span :id="'recommandcount_' + reviewer.audno">{{
                  nullCheck(reviewer.likeCount)
                }}</span>
              </button>
              <div :class="{ dim200: loginPopup == true }"></div>
              <div
                id="layerCommonAlert"
                class="layer_wrap layer_alert layer_wrap200 active"
                v-if="loginPopup"
                tabindex="0"
                style="
                  left: 50%;
                  top: 50%;
                  margin-top: -96.5px;
                  margin-left: -190px;
                "
              >
                <strong class="hidden">레이어 팝업 시작</strong>
                <div class="layer_contents">
                  <div class="bx_alert1">
                    <p class="txt_para ty2">로그인이 필요한 서비스 입니다.</p>
                  </div>
                </div>
                <div class="layer_footer ty2">
                  <ul>
                    <li>
                      <button
                        type="button"
                        class="btn_close btnCloseLayer"
                        @click="loginPopup = false"
                      >
                        <router-link to="/login">확인</router-link>
                      </button>
                    </li>
                  </ul>
                </div>
                <strong class="hidden txtTabIndex" tabindex="0"
                  >레이어 팝업 끝</strong
                >
              </div>
            </div>
          </li>
        </ul>
        <b-pagination v-model="page" pills :total-rows="count" @change="handlePageChange" align="center" :per-page="size"></b-pagination>
      </div>
      <div class="list_bdr_box">
        <h3 class="title txt_caution2">유의사항</h3>
        <ul class="list_txt">
          <li>
            관람평 작성에 대한 포인트는 익일 적립되며, 관람 후 초기 1회에
            대해서만 적립됩니다.
          </li>
          <li>수정/삭제 후 재등록 시에는 포인트 적립이 되지 않습니다.</li>
          <li>
            관람평은 관람 내역당 1회만 작성 가능하며, 상영종료된 영화의 관람평은
            작성 불가합니다.
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import StarRating from "vue-star-rating";
import MainHomeService from "@/services/MainHomeService.js";

// const review =[
//   {
//     rname: "서*봉",
//     gradenum: 10,
//     dateInfo: "2022.12.20",
//     reviewInfo: "미친 CG 최고였어요. ??????",
//     reviewId: "2757102",
//     recommandcount: 3
//   },
//   {
//     rname: "이*섭",
//     gradenum: 8,
//     dateInfo: "2022.12.20",
//     reviewInfo: "지루하지 않고 재미있게 봤어요",
//     reviewId: "2757100",
//     recommandcount: 2
//   },
//   {
//     rname: "김*철",
//     gradenum: 8,
//     dateInfo: "2022.12.20",
//     reviewInfo: "미친 CG 최고였어요. ??????",
//     reviewId: "2757099",
//     recommandcount: 1
//   }
// ]

//  로컬스토리지에서 저장된 user 객체 가져오기 - 리뷰 등록시 로그인된 사용자 이름 가져오기 위함
import User from '@/models/user'

export default {
  data() {
    return {
      audience: {
        audno: null,
        aurate: 10, 
        aureview: "",
        memName: "",
        mid: null,
      },
      isActive: true,
      isActive2: false,
      review: null,
      userCount: 0,
      page: 1,
      size: 10,
      count: 0,
      user: new User('','','','','','','','',''),
      // 로그인 필요 서비스 알림 팝업
      loginPopup:false,
    };
  },
  components: {
    StarRating,
  },

  computed:{
     
     // 로그인여부 가져오는 함수
     loggedIn() {
       return this.$store.state.auth.initialState.status.loggedIn;
     },
     // 비회원로그인여부 가져오는 함수
     loggedIn2() {
       return this.$store.state.auth.initialState2.status.loggedIn2;
     },
 
   },

  methods: {
    // 댓글불러오기(최신순) 함수
    getReviewRecent(mid) {
      MainHomeService.getReviewRecent(mid, this.page-1, this.size)
        .then((response) => {
          const { Review, ReviewCount, totalItems } = response.data;
          this.review = Review;
          this.userCount = ReviewCount.userCount;
          this.count = totalItems;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 댓글불러오기(좋아요순) 함수
    getReviewLike(mid) {
      MainHomeService.getReviewLike(mid, this.page, this.size)
        .then((response) => {
          const { Review, ReviewCount } = response.data;
          this.review = Review;
          this.userCount = ReviewCount.userCount;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 댓글 날짜에 구분자(.) 넣어주기 위한 함수
    changeDateStr(date) {
      let year = date.substr(0, 4);
      let month = date.substr(5, 2);
      let day = date.substr(8, 2);
      return year + "." + month + "." + day;
    },
    // null 들어올시 처리함수
    nullCheck(isNull) {
      if (isNull == null) {
        return 0;
      } else {
        return isNull;
      }
    },
    // 최신순 정렬 함수
    recentReview() {
      this.isActive = true;
      this.getReviewRecent(this.$route.params.mid);
    },
    // 공감순 정렬 함수
    agreeReview() {
      this.isActive = false;
      this.getReviewLike(this.$route.params.mid);
    },
    // 댓글,관람평 추가 함수
    saveReview() {
      // 임시 객체 변수 -> springboot 전송
      // 부서번호는(no) 자동생성되므로 빼고 전송함
      let data = {
        aurate: this.audience.aurate,
        aureview: this.audience.aureview,
        memName: this.audience.memName,
        mid: this.audience.mid
      };

      // insert 요청 함수 호출(axios 공통함수 호출)
      MainHomeService.saveAudienceReview(data)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          // alert("관람평이 등록되었습니다.")
          this.audience.audno = response.data.audno;
          // 콘솔 로그 출력(response.data)
          console.log(response.data);
          // 변수 submitted
          this.audience.aurate = 10;
          this.audience.aureview = "";
          // console.log(this.audience)
          // console.log("isActive: " + this.isActive)
          if(this.isActive==true){
            // console.log("최신순 실행")
            this.getReviewRecent(this.$route.params.mid);
          }else{
            // console.log("좋아요순 실행")
            this.getReviewLike(this.$route.params.mid);
          }
        })
        // 실패하면 .catch() 결과가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 별점에 따른 이모티콘 변화 함수
    // emotion(num){
    //   console.log("num: "+num)
    //   if(num>7){
    //     return "@/assets/images/customer/ic_survey_01.png"
    //   }else if(num>3){
    //     return "@/assets/images/customer/ic_survey_02.png"
    //   }else{
    //     return "@/assets/images/customer/ic_survey_03.png"
    //   }
    // },
    // newReview() {
    //   // 새양식 다시 보여주기 함수, 변수 초기화
    //   this.audience = {};
    // },
    // 좋아요 버튼클릭 함수
    btnGood(audno){
      console.log("회원로그인여부:"+this.loggedIn+"비회원로그인여부:"+this.loggedIn2)
    // 회원&비회원 로그인 여부로 함수 실행
    if(!this.loggedIn&&!this.loggedIn2){
      this.loginPopup=true;
    }else{
      let memId = this.user.username;
      // let memId = "tester";
      MainHomeService.commentLike(audno, memId)
      .then((response) => {
        
          console.log(response.data);
          console.log("isActive: " + this.isActive)

          if(this.isActive==true){
            console.log("최신순 실행")
            this.getReviewRecent(this.$route.params.mid);
          }else{
            console.log("좋아요순 실행")
            this.getReviewLike(this.$route.params.mid);
          }
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
      }  
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      if(this.isActive==true){
            this.getReviewRecent(this.$route.params.mid);
          }else{
            this.getReviewLike(this.$route.params.mid);
          }
    },
  },
  mounted() {
    // alert(this.$route.params.mid)
    this.user=JSON.parse(localStorage.getItem("user"));
    this.getReviewRecent(this.$route.params.mid);
    this.audience.memName= this.user.username;
    this.audience.mid= this.$route.params.mid;
  },
};
</script>

<style>
.dim200 {
    position: fixed;
    top: 0;
    right: 0;
    bottom: -1px;
    left: 0;
    z-index: 199;
    background: rgba(0, 0, 0, 0.5);
    overflow-y: scroll;
}
.layer_wrap.active.layer_alert {
    width: 380px;
}
.layer_wrap200 {
    z-index: 200 !important;
}
.bx_alert1 {
    padding: 30px 0;
}
.txt_para {
    line-height: 1.4;
    text-align: center;
}

.txt_para.ty2 {
    font-size: 16px;
}
.layer_wrap .layer_footer {
    clear: both;
    border-top: 1px solid #DDD;
    text-align: center;
    background-color: #F8F8F8;
}
.layer_wrap .layer_footer.ty2 ul li {
    width: 100%;
}
.layer_wrap .layer_footer ul li {
    position: relative;
    float: left;
    width: 50%;
}
.layer_wrap .layer_footer ul li a, .layer_wrap .layer_footer ul li button {
    display: block;
    width: 100%;
    height: 50px;
    line-height: 50px;
    border: none;
    font-size: 16px;
    background-color: #F8F8F8;
}
.ic_survey_01{
  background-color: transparent;
  width: 42px;
  height: 42px;
  background: url("@/assets/images/customer/ic_survey_01.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey_02{
  background-color: transparent;
}
.ic_survey_03{
  background-color: transparent;
}
.ic_survey_01:before {
  background: url("@/assets/images/customer/ic_survey_01.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_survey_02:before {
  background: url("@/assets/images/customer/ic_survey_02.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_survey_03:before {
  background: url("@/assets/images/customer/ic_survey_03.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey_01:before
.ic_survey_02:before
.ic_survey_03:before{
  content: "";
  display: block;
  width: 42px;
  height: 42px;
}

/*  */
.ic_survey.gr_01 {
  background-color: transparent;
}
.ic_survey {
  display: inline-block;
  width: 42px;
  height: 42px;
  line-height: 42px;
  font-size: 0;
  text-align: center;
  vertical-align: middle;
}
.ic_survey.gr_01:before {
  background: #e9b630 url("@/assets/images/customer/ic_survey_01.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey.gr_01:before,
.ic_survey.gr_02:before,
.ic_survey.gr_03:before,
.ic_survey.gr_non:before,
.ic_survey:before {
  content: "";
  display: block;
  width: 42px;
  height: 42px;
}
.ic_survey:before {
  margin-left: -1px;
  font-size: 13px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}
.ic_survey.gr_01 {
  background-color: transparent;
}
.ic_survey.gr_02 {
  background-color: transparent;
}
.ic_survey.gr_03 {
  background-color: transparent;
}
.ic_survey.gr_01:before,
.ic_survey.gr_02:before,
.ic_survey.gr_03:before,
.ic_survey.gr_non:before,
.ic_survey:before {
  content: "";
  display: block;
  width: 42px;
  height: 42px;
}
.ic_survey:before {
  margin-left: -1px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}

.ic_survey.gr_01:before {
  background: url("@/assets/images/customer/ic_survey_01.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey.gr_02:before {
  background: url("@/assets/images/customer/ic_survey_02.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey.gr_03:before {
  background:  url("@/assets/images/customer/ic_survey_03.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_survey.gr_02:before {
  background:  url("@/assets/images/customer/ic_survey_02.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_survey.gr_01:before {
  background:  url("@/assets/images/customer/ic_survey_01.png") 0 0 no-repeat;
  background-size: 100%;
}
</style>
