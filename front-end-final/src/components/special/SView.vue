<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div
      id="visual_top"
      class="visual_top visual_special supers"
      style="margin-top: 120px"
    >
      <video autoplay="" loop="" preload="auto">
        <source src="../../assets/video/visual_supers.mp4" type="video/mp4" />
      </video>
      <div class="section">
        <div class="special_txt">
          <img
            src="../../assets/images/special/S/special_supers_wht.png"
            alt=""
          />
          <p>세계 최초 LED 시네마</p>
        </div>
      </div>
    </div>

    <div id="contents" class="special_theater supers">
      <div class="section img_01">
        <div class="sec_tit">
          <div class="tit_primary"><em>The</em>World's First</div>
          <div class="tit_secondary">
            <strong>처음 만나는</strong> LED 시네마
          </div>
          <div class="sec_content">
            세계 최초로 도입된 LED 스크린으로 영사기 없는 상영관을 선보입니다.
          </div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>LED 스크린 이미지입니다.</em></div>
          </div>
        </div>
      </div>
      <div class="section img_02">
        <div class="img_primary">
          <div class="img_itm"><div class="bx_img"></div></div>
          <div class="img_itm ico_arr"><div class="bx_img"></div></div>
          <div class="sec_img">
            <div class="img_wrap">
              <div class="bx_img"><em>삼성 시네마 LED 이미지입니다.</em></div>
            </div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">10X</div>
          <div class="tit_secondary">
            <strong>삼성이 만들어낸 극강의 화질</strong>
          </div>
          <div class="sec_content">
            일반 영사기 대비 10배에 이르는 밝기와<br />무한대의 명암비로 극강의
            화질을 선사 합니다.
          </div>
        </div>
        <div class="img_secondary">
          <div class="bx_img"></div>
          <div class="bx_img motion fade_in"></div>
        </div>
      </div>
      <div class="section img_03">
        <div class="img_primary">
          <div class="img_itm"><div class="bx_img"></div></div>
          <div class="img_itm ico_arr"><div class="bx_img"></div></div>
          <div class="sec_img">
            <div class="img_wrap">
              <div class="bx_img"><em>Real Immersion 이미지입니다.</em></div>
            </div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Immersion</div>
          <div class="tit_secondary">
            처음 느껴보는 <br /><strong>완벽한 실재감</strong>
          </div>
          <div class="sec_content">
            스크린 어느 부분에서도 균일한 밝기를 유지하고<br />
            숨겨진 디테일까지 찾아주어 현실을 그대로 보는 듯한 몰입감을
            제공합니다.
          </div>
        </div>
        <div class="img_secondary">
          <div class="bx_img"></div>
          <div class="bx_img motion fade_in"></div>
        </div>
      </div>
      <div class="section img_04">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>Powerful Sound 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary"><em>Powerful</em> Sound</div>
          <div class="tit_secondary">
            <strong>정교하고 파워풀한</strong><br />사운드
          </div>
          <div class="sec_content">
            세계 최고 음향기기 제조사 HARMAN의 음향기술이 적용되어<br />
            최상의 음질을 경험할 수 있습니다.
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
export default {
  components: {
    NavBlack,
  },
};
</script>

<style>
.visual_top {
  overflow: hidden;
  position: relative;
  width: 100%;
  background-color: #000;
}
.visual_top {
  height: 420px;
}
.visual_top > .inner {
  position: absolute;
  left: 50%;
  top: 0;
  width: 1920px;
  margin-left: -960px;
}
.visual_special {
  height: 596px;
  background-size: cover;
  background-position: 50% 50%;
}
.visual_special video {
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 100%; /* z-index 삽입시, 작동안됨*/
}
.visual_special .section > img {
  width: 100%;
}
.visual_special .special_txt {
  position: absolute;
  bottom: 100px;
  left: 50%;
  margin-left: -490px;
  z-index: 1;
}
.visual_special .special_txt > img {
  display: block;
  margin-bottom: 30px;
}
.visual_special .special_txt .special_tit {
  color: #ffffff;
  margin-bottom: 15px;
  font-size: 60px;
  font-family: "Roboto";
}
.visual_special .special_txt p {
  color: #fff;
  font-weight: 100;
  font-size: 18px;
  padding-left: 50px;
}
.special_theater {
  position: relative;
}
.special_theater .special_list_tit {
  font-size: 18px;
  margin-bottom: 18px;
}
.special_theater ul.special_list {
  font-size: 0;
  border: 1px solid #dedede;
  border-bottom: 0 none;
  overflow: hidden;
}
.special_theater ul.special_list:after {
  content: "";
  display: block;
  border-top: 1px solid #dedede;
  margin-top: -1px;
  width: 100%;
  height: 1px;
}
.special_theater ul.special_list li {
  display: inline-block;
  width: 14.2857%;
}
.special_theater ul.special_list li > a {
  display: block;
  border-bottom: 1px solid #dedede;
  border-right: 1px solid #dedede;
  width: 100%;
  padding: 20px 0 17px 0;
  text-align: center;
  font-size: 13px;
  color: #666666;
  box-sizing: border-box;
}
.special_theater ul.special_list li:nth-of-type(7n) > a,
.special_theater ul.special_list li:first-child:nth-last-child(1) > a,
.special_theater ul.special_list li:first-child:nth-last-child(2) ~ li > a,
.special_theater
  ul.special_list
  li:first-child:nth-last-child(3)
  ~ li
  ~ li
  > a {
  border-right: 0 none;
}
.special_theater ul.special_list li:first-child:nth-last-child(1) {
  width: 100%;
}
.special_theater ul.special_list li:first-child:nth-last-child(2),
.special_theater ul.special_list li:first-child:nth-last-child(2) ~ li {
  width: 50%;
}
.special_theater ul.special_list li:first-child:nth-last-child(3),
.special_theater ul.special_list li:first-child:nth-last-child(3) ~ li,
.special_theater ul.special_list li:first-child:nth-last-child(3) ~ li ~ li {
  width: 33.33333%;
}
.special_theater .section {
  position: relative;
  margin-bottom: 180px;
  font-size: 0;
}
.special_theater .section:first-child {
  margin-bottom: 80px;
}
.special_theater .section.last {
  margin-bottom: 0px;
}
.special_theater .section .sec_tit {
  display: inline-block;
  vertical-align: top;
  letter-spacing: -1px;
}
.special_theater .section .sec_tit .tit_primary {
  margin-bottom: 18px;
  line-height: 1;
  font-size: 70px;
  font-family: "Roboto";
  color: #c0bfbe;
}
.special_theater .section .sec_tit .tit_primary em {
  display: block;
  font-size: 29px;
}
.special_theater .section .sec_tit .tit_secondary {
  font-size: 28px;
  line-height: 1.2;
  margin-bottom: 24px;
}
.special_theater .section .sec_tit .tit_secondary strong {
  font-weight: 700;
}
.special_theater .section .sec_tit .sec_content {
  font-size: 15px;
  line-height: 1.4;
}
.special_theater .section .sec_tit .sec_slide_wrap {
  position: relative;
}
.special_theater .section .sec_img {
  position: relative;
  display: inline-block;
  overflow: hidden;
}
.special_theater .section .sec_img .img_wrap {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.special_theater .section .sec_img em {
  text-indent: -9999em;
  color: transparent;
  font-size: 0;
}
.special_theater .section .sec_img .bx_img {
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-size: cover;
}
.special_theater .section .sec_img .img_wrap:after {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  box-sizing: border-box;
}
.special_theater .section div[class*="mask"] .img_wrap:after {
  background-color: #dddddd;
}
.special_theater .section .img_primary {
  position: relative;
}
.special_theater .section .img_primary > .img_itm,
.special_theater .section .img_secondary {
  position: absolute;
}
.special_theater .section .img_primary > .img_itm > .bx_img,
.special_theater .section .img_secondary > .bx_img {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: cover;
  transition: opacity 1.2s 1s linear;
  -webkit-transition: opacity 1.2s 1s linear;
  -moz-transition: opacity 1.2s 1s linear;
  -ms-transition: opacity 1.2s 1s linear;
  -o-transition: opacity 1.2s 1s linear;
}
.special_theater .section .lent_wrap {
  position: relative;
  border: 1px solid #dddddd;
  padding: 35px 50px;
  font-size: 0;
}
.special_theater .section .lent_wrap .bx_img {
  display: inline-block;
  border-radius: 50%;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  background-size: cover;
  width: 130px;
  height: 130px;
  margin-right: 40px;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  -ms-border-radius: 50%;
  -o-border-radius: 50%;
  vertical-align: middle;
}
.special_theater .section .lent_wrap .bx_info {
  display: inline-block;
  font-size: 13px;
  line-height: 1.4;
  vertical-align: middle;
}
.special_theater .section .lent_wrap .bx_info strong {
  font-weight: bold;
}
.special_theater .section .lent_wrap .bx_info img {
  display: block;
  margin-bottom: 20px;
}
.special_theater .section .lent_wrap .btn_more {
  position: absolute;
  right: 50px;
  top: 50%;
  margin-top: -8px;
}
.special_theater .section .lent_wrap .btn_more .line {
  display: inline-block;
  background-color: #eeeeee;
  width: 211px;
  height: 1px;
  margin-right: 60px;
}
.special_theater .section .lent_wrap .btn_more .btn_txt {
  border: 0 none;
  font-size: 15px;
  color: #000;
}
#contents {
  width: 980px;
  margin: 0 auto;
  padding: 50px 0 0 0;
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
.visual_special.supers {
  background-image: url("@/assets/images/special/S/visual_special_supers.jpg");
}
.supers .img_01 .sec_tit {
  margin-bottom: 40px;
}
.supers .img_01 .sec_img {
  width: 100%;
  height: 488px;
}
.supers .img_01 .sec_img .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_01.jpg");
}
.supers .img_02 {
  text-align: right;
}
.supers .img_02 .sec_tit {
  width: 880px;
  margin-top: 40px;
  margin-right: 50px;
  text-align: right;
}
.supers .img_02 .sec_img {
  width: 517px;
  height: 333px;
  margin-right: 50px;
}
.supers .img_02 .sec_img .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_02_02.jpg");
}
.supers .img_02 .img_primary .img_itm {
  top: 0px;
  left: 85px;
  width: 280px;
  height: 188px;
}
.supers .img_02 .img_primary .img_itm .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_02_01.jpg");
}
.supers .img_02 .img_primary .img_itm.ico_arr {
  top: 90px;
  left: 375px;
  width: 30px;
  height: 6px;
  transition-delay: 0.3s;
}
.supers .img_02 .img_primary .img_itm.ico_arr .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_arrow.jpg");
}
.supers .img_02 .img_primary .sec_img.motion {
  transition-delay: 0.5s;
}
.supers .img_02 .img_secondary {
  left: 85px;
  bottom: 100px;
  width: 255px;
  height: 203px;
}
.supers .img_02 .img_secondary .bx_img:first-child {
  background-image: url("@/assets/images/special/S/motion_supers_01_01.jpg");
}
.supers .img_02 .img_secondary .bx_img.motion {
  background-image: url("@/assets/images/special/S/motion_supers_01_02.jpg");
  transition-delay: 1.2s;
  transition-duration: 0.55s;
}
.supers .img_03 .sec_tit {
  position: relative;
  top: -100px;
}
.supers .img_03 .sec_img {
  width: 427px;
  height: 474px;
  margin-right: 50px;
}
.supers .img_03 .sec_img .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_03_02.jpg");
}
.supers .img_03 .img_primary {
  text-align: right;
}
.supers .img_03 .img_primary .img_itm {
  top: 0px;
  left: 155px;
  width: 280px;
  height: 310px;
}
.supers .img_03 .img_primary .img_itm .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_03_01.jpg");
}
.supers .img_03 .img_primary .img_itm.ico_arr {
  top: 150px;
  left: 455px;
  width: 30px;
  height: 6px;
  transition-delay: 0.3s;
}
.supers .img_03 .img_primary .img_itm.ico_arr .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_arrow.jpg");
}
.supers .img_03 .img_primary .sec_img.motion {
  transition-delay: 0.5s;
}
.supers .img_03 .img_secondary {
  bottom: -40px;
  right: 50px;
  width: 427px;
  height: 227px;
}
.supers .img_03 .img_secondary .bx_img:first-child {
  background-image: url("@/assets/images/special/S/motion_supers_02_01.jpg");
}
.supers .img_03 .img_secondary .bx_img.motion {
  background-image: url("@/assets/images/special/S/motion_supers_02_02.jpg");
  transition-delay: 1.05s;
  transition-duration: 0.55s;
}
.supers .img_04 .sec_tit {
  width: 352px;
  margin-left: 48px;
  margin-top: 20px;
}
.supers .img_04 .sec_img {
  width: 580px;
  height: 325px;
}
.supers .img_04 .sec_img .bx_img {
  background-image: url("@/assets/images/special/S/special_supers_04.jpg");
}
</style>
