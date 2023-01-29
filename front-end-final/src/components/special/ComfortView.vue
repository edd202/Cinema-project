<template>
  <div>
    <NavBlack></NavBlack>

    <div id="visual_top" class="visual_top visual_special cinecomfort">
      <img src="@/assets/images/special/comfort/visual_special_cinecomfort_1.jpg" alt="">
      <div class="section">
        <div class="special_txt">
          <img
              src="@/assets/images/special/comfort/special_cinecomfort_wht.png"
            alt=""
          />
          <p>몰입을 위한 나만의 리클라이너</p>
        </div>
      </div>
    </div>

    <div id="contents" class="special_theater cinecomfort">
      <div class="section img_01">
        <div class="sec_tit">
          <div class="tit_primary">Recliner</div>
          <div class="tit_secondary">
            각도 조절이 가능한<br />
            <strong>리클라이너</strong>
          </div>
          <div class="sec_content">
            내몸에 맞춘듯한 리클라이너 소파로 <br />
            보다 편안하고 아늑한 시간을 느껴보세요.
          </div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>가죽 소파 좌석 이미지입니다.</em></div>
          </div>
        </div>
      </div>
      <div class="section img_02">
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>편안한 좌석 이미지입니다.</em></div>
          </div>
        </div>
        <div class="sec_tit">
          <div class="tit_primary">Comfortable</div>
          <div class="tit_secondary">푹신한 <strong>가죽 소파</strong></div>
          <div class="sec_content">
            넓고 편안한 가죽 소파와 넓은 좌석 간격,<br />
            영화를 더욱 특별하게 즐길 수 있는 방법입니다.
          </div>
        </div>
      </div>
      <div class="section img_03">
        <div class="sec_tit">
          <div class="tit_primary"><em>Easy</em> Operation</div>
          <div class="tit_secondary"><strong>손쉬운 </strong>작동</div>
          <div class="sec_content">
            자동 버튼으로 손쉽게,<br />
            내가 원하는 높낮이로 영화에 몰입해보세요.
          </div>
        </div>
        <div class="sec_img">
          <div class="img_wrap">
            <div class="bx_img"><em>좌석 우측 버튼 이미지입니다.</em></div>
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
.visual_special.cinecomfort {background: #171717;}
.visual_special.cinecomfort .visual_slide .swiper-slide .itm.img02 {background-image:url('@/assets/images/special/comfort/visual_special_cinecomfort_1.jpg'); background-size:auto 696px;}
.visual_special.cinecomfort .visual_slide .swiper-slide .itm.img01 {background-image:url('@/assets/images/special/comfort/visual_special_cinecomfort_2.jpg'); background-size:auto 680px;}
.visual_top {overflow:hidden; position:relative; width:100%; background-color:#000;}
.visual_top {height:420px;}
.visual_top > .inner {position:absolute; left:50%; top:0; width:1920px; margin-left:-960px;}
.visual_special {height:596px; background-size:cover; background-position:50% 50%;}
.visual_special video {position:absolute; top:0; left:0; min-width:100%; min-height:100%; }
.visual_special .section > img {width:100%;}
.visual_special .special_txt {position:absolute; bottom:100px; left:50%; margin-left:-490px; z-index:1;}
.visual_special .special_txt > img {display:block; margin-bottom:30px;}
.visual_special .special_txt .special_tit {color:#ffffff; margin-bottom:15px; font-size:60px; font-family:'Roboto';}
.visual_special .special_txt p {color:#fff; font-weight:100; font-size:18px; padding-left:50px;}
#contents {width:980px; margin:0 auto; padding:50px 0 0 0;}
#contents.contents_full {width:100%;}
#contents.contents_full .contents_inner {width:980px; margin:0 auto;}
#contents.contents_mypage:before {content:""; position:absolute; z-index:-1; left:0; right:0; top:114px; height:334px; background-color:#FF7787;}
#contents.contents_movie_detail {padding-top:35px;}
#contents.contents_mall_home {padding-top:30px;}
#contents.contents_member_comn {padding-top:105px;}
#contents.contents_reserve {padding-bottom:70px; background-color:#444}
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
.cinecomfort .img_01 .sec_tit {width:310px; margin-top:20px;}
.cinecomfort .img_01 .sec_img {width:662px; height:413px;}
.cinecomfort .img_01 .sec_img .bx_img {background-image:url('@/assets/images/special/comfort/special_cinecomfort_01.jpg');}
.cinecomfort .img_02 .sec_tit {width:390px; margin-left:50px; margin-top:40px;}
.cinecomfort .img_02 .sec_img {width:504px; height:380px;}
.cinecomfort .img_02 .sec_img .bx_img {background-image:url('@/assets/images/special/comfort/special_cinecomfort_02.jpg');}
.cinecomfort .img_03 {text-align:right;}
.cinecomfort .img_03 .sec_tit {margin-right:50px; margin-top:55px;}
.cinecomfort .img_03 .sec_img {width:395px; height:390px;}
.cinecomfort .img_03 .sec_img .bx_img {background-image:url('@/assets/images/special/comfort/special_cinecomfort_03.jpg');}

</style>
