<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div id="contents" class="special_theater_home new22" style="">
      <div class="special_tit_wrap">
        <div class="tit">
          <em>Special</em>
           Theater For You
        </div>
        <p style="margin-top: 60px">영화의 순간을 더욱 특별하게</p>
      </div>
      <div class="special_list_wrap">
        <ul class="special_list">
          <li class="section">
            <router-link to="/color">
            <div class="sec_img img01">
              <em>컬러리움 이미지입니다.</em>
            </div>
            <div class="bx_txt">
              <div class="tit">컬러리움</div>
              <p class="">세상의 모든 색으로 영화를 보다</p>
            </div>
            </router-link>
          </li>
          <li class="section">
            <router-link to="/comfort">
            <div class="sec_img img02">
              <em>씨네컴포트 이미지입니다.</em>
            </div>
            <div class="bx_txt">
              <div class="tit">씨네 컴포트</div>
              <p class="">몰입을 위한 나만의 리클라이너</p>
            </div>
            </router-link>
          </li>
        </ul>
        <ul class="special_list">
          <li class="section">
            <router-link to="/s">
            <div class="sec_img img03"><em>수퍼S 이미지입니다.</em></div>
            <div class="bx_txt">
              <div class="tit">수퍼S</div>
              <p class="">세계 최초 LED 시네마</p>
            </div>
            </router-link>
          </li>
          <li class="section">
            <router-link to="/4d">
            <div class="sec_img img04"><em>수퍼4D 이미지입니다.</em></div>
            <div class="bx_txt">
              <div class="tit">수퍼4D</div>
              <p class="">다이나믹한 오감 만족 체험</p>
            </div>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
    <!-- 대제목 끝 -->
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "../assets/js/custom";
import accTable from "../assets/js/accordion";

export default {
  components: {
    NavBlack,
  },
  mounted() {
    initCustom();
    accTable();
  },
};
</script>

<style scoped>
/* 포괄 css */
ul {
  list-style: none;
}
.img01 {
  background-image: url("../assets/images/special/color.jpg");
  margin-left: 100px;
}
.img02 {
  background-image: url("../assets/images/special/comfort.jpg");
  margin-left: 100px;
}
.img03 {
  background-image: url("../assets/images/special/S.jpg");
  margin-left: 100px;
}
.img04 {
  background-image: url("../assets/images/special/4d.jpg");
  margin-left: 100px;
}
.special_theater_home {position:relative;}
.special_theater_home .special_tit_wrap {position:absolute; width:625px; margin-top: 40px; padding:18px 0;} /* 2020.03.30 */
.special_theater_home .special_tit_wrap .tit {position:relative; padding:45px 0 0 40px; font-size:45px; font-family:'Roboto'; margin-bottom:18px;}
.special_theater_home .special_tit_wrap .tit > em {position:absolute; top:0; left:0;}
.special_theater_home .special_tit_wrap p {padding-left:20px; font-size:15px;}
.special_theater_home .special_list_wrap {margin-top:275px;} /* 2020.03.30 */
.special_theater_home .special_list_wrap:after {content:''; display:table; clear:both;}
.special_theater_home .special_list {position:relative; margin-top:80px; font-size:0;}
.special_theater_home .special_list > li {position:relative; display:inline-block; vertical-align:top;}
.special_theater_home .special_list > li:nth-child(2n) {top:-43px;}
.special_theater_home .special_list > li:last-child {top:-217px;}
.special_theater_home .special_list > li+li {margin-left:85px;}
.special_theater_home .special_list > li > a {display:block;}
.special_theater_home .special_list > li > a .sec_img {width:270px; height:270px; margin-bottom:22px;}
.special_theater_home .special_list > li > a .bx_txt {padding-left:20px;}
.special_theater_home .special_list > li > a .bx_txt .tit {margin-bottom:10px; font-size:18px;}
.special_theater_home .special_list > li > a .bx_txt p {font-size:11px;}
.special_theater {position:relative;}
.special_theater .special_list_tit {font-size:18px; margin-bottom:18px;}
.special_theater ul.special_list {font-size:0; border:1px solid #DEDEDE; border-bottom:0 none; overflow:hidden;}
.special_theater ul.special_list:after {content:''; display:block; border-top:1px solid #DEDEDE; margin-top:-1px; width:100%; height:1px;}
.special_theater ul.special_list li {display:inline-block; width:14.2857%;}
.special_theater ul.special_list li > a {display:block; border-bottom:1px solid #DEDEDE; border-right:1px solid #DEDEDE; width:100%; padding:20px 0 17px 0; text-align:center; font-size:13px; color:#666666; box-sizing:border-box;}
.special_theater ul.special_list li:nth-of-type(7n) > a,
.special_theater ul.special_list li:first-child:nth-last-child(1) > a,
.special_theater ul.special_list li:first-child:nth-last-child(2)~li > a,
.special_theater ul.special_list li:first-child:nth-last-child(3)~li~li > a {border-right:0 none;}
.special_theater ul.special_list li:first-child:nth-last-child(1) {width:100%;}
.special_theater ul.special_list li:first-child:nth-last-child(2),
.special_theater ul.special_list li:first-child:nth-last-child(2)~li {width:50%;}
.special_theater ul.special_list li:first-child:nth-last-child(3),
.special_theater ul.special_list li:first-child:nth-last-child(3)~li,
.special_theater ul.special_list li:first-child:nth-last-child(3)~li~li {width:33.33333%;}
.special_theater .section {position:relative; margin-bottom:180px; font-size:0;}
.special_theater .section:first-child {margin-bottom:80px;}
.special_theater .section.last {margin-bottom:0px;}
.special_theater .section .sec_tit {display:inline-block; vertical-align:top; letter-spacing:-1px;}
.special_theater .section .sec_tit .tit_primary {margin-bottom:18px; line-height:1; font-size:70px; font-family:'Roboto'; color:#C0BFBE;}
.special_theater .section .sec_tit .tit_primary em {display:block; font-size:29px;}
.special_theater .section .sec_tit .tit_secondary {font-size:28px; line-height:1.2; margin-bottom:24px;}
.special_theater .section .sec_tit .tit_secondary strong {font-weight:700;}
.special_theater .section .sec_tit .sec_content {font-size:15px; line-height:1.4;}
.special_theater .section .sec_tit .sec_slide_wrap {position:relative;}
.special_theater .section .sec_img {position:relative; display:inline-block; overflow:hidden;}
.special_theater .section .sec_img .img_wrap {position:absolute; top:0; left:0; width:100%; height:100%;}
.special_theater .section .sec_img em {text-indent:-9999em; color:transparent; font-size:0;}
.special_theater .section .sec_img .bx_img {width:100%; height:100%; background-repeat:no-repeat; background-size:cover;}
.special_theater .section .sec_img .img_wrap:after {content:''; display:block; position:absolute; top:0; left:0; right:0; bottom:0; box-sizing:border-box;}
.special_theater .section div[class*="mask"] .img_wrap:after {background-color:#DDDDDD;}
.special_theater .section .img_primary {position:relative;}
.special_theater .section .img_primary > .img_itm,
.special_theater .section .img_secondary {position:absolute;}
.special_theater .section .img_primary > .img_itm > .bx_img,
.special_theater .section .img_secondary > .bx_img {display:block; position:absolute; top:0; left:0; width:100%; height:100%; background-repeat:no-repeat; background-position:0 0; background-size:cover; transition:opacity 1.2s 1s linear; -webkit-transition:opacity 1.2s 1s linear; -moz-transition:opacity 1.2s 1s linear; -ms-transition:opacity 1.2s 1s linear; -o-transition:opacity 1.2s 1s linear;}
.special_theater .section .lent_wrap {position:relative; border:1px solid #DDDDDD; padding:35px 50px; font-size:0;}
.special_theater .section .lent_wrap .bx_img {display:inline-block; border-radius:50%; background-repeat:no-repeat; background-position:50% 50%; background-size:cover; width:130px; height:130px; margin-right:40px; -webkit-border-radius:50%; -moz-border-radius:50%; -ms-border-radius:50%; -o-border-radius:50%; vertical-align:middle;}
.special_theater .section .lent_wrap .bx_info {display:inline-block; font-size:13px; line-height:1.4; vertical-align:middle;}
.special_theater .section .lent_wrap .bx_info strong {font-weight:bold;}
.special_theater .section .lent_wrap .bx_info img {display:block; margin-bottom:20px;}
.special_theater .section .lent_wrap .btn_more {position:absolute; right:50px; top:50%; margin-top:-8px;}
.special_theater .section .lent_wrap .btn_more .line {display:inline-block; background-color:#EEEEEE; width:211px; height:1px; margin-right:60px;}
.special_theater .section .lent_wrap .btn_more .btn_txt {border:0 none; font-size:15px; color:#000;}

.wh_cover{width:270px;height:270px;background-color:#fff;z-index:2;}
a {
  text-decoration: none;
}
div {
  color: rgb(0, 0, 0);
}
.bx_txt {
  margin-left: 100px;
}
</style>
