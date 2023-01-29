<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div
      id="visual_top"
      class="visual_top visual_special super4d"
      style="margin-top: 120px"
    >
      <video autoplay="" loop="" preload="auto">
        <source src="../../assets/video/4d.mp4" type="video/mp4" />
      </video>
      <div class="section">
        <div class="special_txt">
          <img
            src="@/assets/images/special/4d/special_super4d_wht.png"
            alt=""
          />
          <p>다이나믹한 오감 만족 체험</p>
        </div>
      </div>
    </div>
    <div id="contents" class="special_theater super4d">
      <div class="section img_01">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>대형스크린 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary"><em>Feel The</em>Movie</div>
          <div class="tit_secondary">
            온 몸으로 <strong>영화를 느끼다!</strong>
          </div>
          <div class="sec_content">
            영화 속 장면에 따라 좌석에 직접 느껴지는 바이브레이션, 모션 효과뿐
            아니라<br />
            바람, 빛, 버블, 안개, 향기, 물 등 다양한 특수 효과가 오감을 자극하며
            영화 속 장면에 더욱 몰입하게 해줍니다.
            <ul class="list_txt mt5">
              <li>4D 효과는 극장별 / 영화별로 상이할 수 있습니다.</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="section img_02">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>바람 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Wind</div>
          <div class="tit_secondary">바람</div>
        </div>
      </div>
      <div class="section img_03">
        <div class="sec_tit">
          <div class="tit_primary">Lightning</div>
          <div class="tit_secondary">빛</div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>빛 이미지입니다.</em></div>
          </div>
        </div>
      </div>
      <div class="section img_04">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>버블 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Bubbles</div>
          <div class="tit_secondary">버블</div>
        </div>
      </div>
      <div class="section img_05">
        <div class="sec_tit">
          <div class="tit_primary">Fog</div>
          <div class="tit_secondary">안개</div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>안개 이미지입니다.</em></div>
          </div>
        </div>
      </div>
      <div class="section img_06">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>향기 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Scents</div>
          <div class="tit_secondary">향기</div>
        </div>
      </div>
      <div class="section img_07">
        <div class="sec_tit">
          <div class="tit_primary">Water</div>
          <div class="tit_secondary">물</div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>물 이미지입니다.</em></div>
          </div>
        </div>
      </div>
      <div class="section img_08">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>모션 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Motion</div>
          <div class="tit_secondary">모션</div>
        </div>
      </div>
      <div class="section img_09">
        <div class="sec_tit">
          <div class="tit_primary">Vibration</div>
          <div class="tit_secondary">진동</div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>진동 이미지입니다.</em></div>
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
.visual_special.super4d {
  background-image: url("../../assets/images/special/4d/visual_special_super4d.jpg");
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
.super4d .sec_tit {
  width: 430px;
  margin: 91px 50px 0;
}
.super4d .sec_tit .tit_primary {
  position: relative;
  display: inline-block;
}
.super4d .sec_tit .tit_primary:after {
  content: "";
  position: absolute;
  top: -35px;
  display: inline-block;
  width: 60px;
  height: 60px;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  background-size: contain;
}
.super4d .img_02 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_wind.png");
  margin-left: 10px;
}
.super4d .img_03 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_lightning.png");
  left: -70px;
}
.super4d .img_04 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_bubbles.png");
}
.super4d .img_05 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_fog.png");
  left: -75px;
}
.super4d .img_06 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_scents.png");
}
.super4d .img_07 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_water.png");
  top: -15px;
  left: -75px;
}
.super4d .img_08 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_motion.png");
  top: -15px;
}
.super4d .img_09 .sec_tit .tit_primary:after {
  background-image: url("../../assets/images/special/4d/ic_vibration.png");
  top: -15px;
  left: -75px;
}
.super4d .sec_img {
  width: 450px;
  height: 293px;
}
.super4d .img_01 {
  margin-bottom: 60px;
}
.super4d .img_01 .sec_tit {
  margin: 80px 0 0 0;
  width: 100%;
}
.super4d .img_01 .sec_img {
  width: 100%;
  height: 304px;
}
.super4d .img_03 .sec_tit,
.super4d .img_05 .sec_tit,
.super4d .img_07 .sec_tit,
.super4d .img_09 .sec_tit {
  text-align: right;
}
.super4d .img_01 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_01.jpg");
}
.super4d .img_02 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_02.jpg");
}
.super4d .img_03 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_03.jpg");
}
.super4d .img_04 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_04.jpg");
}
.super4d .img_05 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_05.jpg");
}
.super4d .img_06 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_06.jpg");
}
.super4d .img_07 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_07.jpg");
}
.super4d .img_08 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_08.jpg");
}
.super4d .img_09 .sec_img .bx_img {
  background-image: url("../../assets/images/special/4d/special_super4d_09.jpg");
}
</style>
