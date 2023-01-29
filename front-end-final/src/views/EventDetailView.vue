<template>
  <div>
    <NavBlack></NavBlack>
    <div id="contents" class="contents_event">
      <div class="evnt_img">
        <img :src="`http://` + currentHost + `/api/movie/event/thumb/${imgNum}`" />
      </div>
      <div class="btn_btm_wrap">
        <button class="btn_col2 ty6" href="#none">목록</button>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";

export default {
  // TODO: 4) imgNum 변수 추가
  data() {
    return {
      imgNum: 0,
      currentHost: "",
    };
  },
  components: {
    NavBlack,
  },
  mounted() {
    // TODO: 3) URL 에 imgNum 값을 data() 의 변수 imgNum 에 넣기
    this.currentHost = window.location.host;
    this.imgNum = this.$route.params.imgNum;
    console.log("this.$route.params.imgNum : ", this.$route.params.imgNum);
  },
};
</script>

<style scoped>
#contents {
  width: 980px;
  margin: 0 auto;
  padding: 50px 0 0 0;
  text-align: center;
}
#contents.contents_full {
  width: 100%;
}
#contents.contents_full .contents_inner {
  width: 980px;
  margin: 0 auto;
}
#contents.contents_mypage:before {
  content: "";
  position: absolute;
  z-index: -1;
  left: 0;
  right: 0;
  top: 114px;
  height: 334px;
  background-color: #ff7787;
}
#contents.contents_movie_detail {
  padding-top: 35px;
}
#contents.contents_mall_home {
  padding-top: 30px;
}
#contents.contents_member_comn {
  padding-top: 105px;
}
#contents.contents_reserve {
  padding-bottom: 70px;
  background-color: #444;
}
.contents_event .evnt_img > img {
  width: 100%;
  margin-top: 40px;
  margin-bottom: 100px;
}
.contents_event .time_wrap {
  margin-bottom: 0;
}
.contents_event .list_bdr_box {
  margin-top: 60px;
}
.contents_event .time_wrap .txt_total {
  float: none;
}
.contents_event .btn_btm_wrap {
  position: relative;
}
.contents_event .btn_btm_wrap .share {
  position: absolute;
  right: 0;
}
.contents_event .btn_btm_wrap .share span.btn_share {
  margin-right: 10px;
}
.contents_event .btn_btm_wrap select {
  width: 176px;
  height: 45px;
  line-height: 45px;
  vertical-align: middle;
}
.contents_event .evnt_movie {
  position: relative;
  display: block;
}
.contents_event .evnt_movie:after {
  content: "";
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 50px;
  height: 50px;
  margin-top: -25px;
  margin-left: -25px;
}
.contents_event .tbl_dtal td img {
  width: 100%;
}
.contents_event .evnt_chk_wrap {
  margin: 60px 0;
}
.contents_event .evnt_chk_wrap .tit_wrap {
  float: left;
  border-top: 1px solid #000;
  width: 220px;
  padding: 40px 0;
}
.contents_event .evnt_chk_wrap .tit_wrap .tit {
  display: inline-block;
  font-size: 45px;
  margin-bottom: 50px;
}
.contents_event .evnt_chk_wrap .tit_wrap .tit span {
  font-family: "Roboto";
  font-size: 50px;
  color: #ff243e;
}
.contents_event .evnt_chk_wrap .tit_wrap .cunt {
  font-size: 18px;
}
.contents_event .evnt_chk_wrap .tit_wrap .cunt strong {
  font-family: "Roboto";
  font-weight: bold;
  color: #ff243e;
}
</style>
