<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div id="contents" class="contents_event_home" style="">
      <div class="title_top">
        <h2 class="tit">전체 이벤트</h2>
      </div>
      <div class="lst_wrap">
        <h3 class="tit">영화</h3>
        <ul class="img_lst_wrap">
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/e3ce64ffb17e4d4090f5f022ff47c8e6.jpg"
                alt="<장화신은 고양이> 현장 경품 이벤트 (키 체인)"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2023.01.04 ~ 2023.01.10</div></a
            >
          </li>
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/af67679171274497872584fc4314fa5a.jpg"
                alt="<패닉 런> 관람인증 이벤트"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2023.01.04 ~ 2023.01.15</div></a
            >
          </li>
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/c7adface476647c3b4b68860fb28d688.jpg"
                alt="<코르사주>굿즈패키지 상영회"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2022.12.30 ~ 2022.12.30</div></a
            >
          </li>
        </ul>
        <a class="btn_txt_more ty2" href="#none">더보기</a>
      </div>
      <div class="lst_wrap">
        <h3 class="tit">시사회/무대인사</h3>
        <ul class="img_lst_wrap">
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/4825f7017234482ca8a81536af82345c.png"
                alt="<캐리와 슈퍼콜라> 스페셜 상영회"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2022.12.31 ~ 2022.12.31</div></a
            >
          </li>
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/d5eb238c16a64dbaab26c63193b67360.jpg"
                alt="<영웅> 2주차 무대인사"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2022.12.31 ~ 2023.01.01</div></a
            >
          </li>
          <li>
            <a href="#none"
              ><img
                src="https://caching.lottecinema.co.kr//Media/Event/331802b3305148ea986e14d432a8f468.jpg"
                alt="<핑크퐁 시네마 콘서트2: 원더스타 콘서트 대작전>2주차 무대인사"
              />
              <div class="itm_tit"></div>
              <div class="itm_date">2022.12.31 ~ 2023.01.01</div></a
            >
          </li>
        </ul>
        <a class="btn_txt_more ty2" href="#none">더보기</a>
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
.contents_event_home .img_lst_wrap img{height:184px;}
.contents_event_home .lst_wrap {margin-top:54px;}
.contents_event_home .title_top + .lst_wrap {margin-top:34px;}
/* 상단제목 */
.contents_event_home .lst_wrap .btn_txt_more.ty2 {top:8px;}
/* 리스트 */
.contents_event_home .lst_wrap .img_lst_wrap > li {margin-bottom:0;}
.contents_event_home .lst_wrap .img_lst_wrap > li > a {height:auto; color: #000;}
.contents_event_home .lst_wrap .img_lst_wrap > li > a .bd_txt {top:10px;}
#contents.contents_event_home > .title_top {padding-top:0;}
#contents.contents_event_home .banner_event_btm {margin-top:50px;}
.lst_wrap {position:relative;}
.lst_wrap .tit {display:inline-block; margin-bottom:18px; font-size:18px;}
.lst_wrap .btn_txt_more.ty2 {position:absolute; right:0; top:0; cursor:pointer;}
.btn_txt_more {width:100%; padding:8px 0; border:1px solid #ECECEC; background-color:#ffffff;}
.btn_txt_more.ty2 {display:inline-block; background:url(https://event.lottecinema.co.kr/NLCHS/Content/images/icon/arr_rg_6.png) no-repeat right 2px; border:0 none; width:auto; padding:0 10px 0 0; color:#666666; font-size:10px;}
.img_lst_wrap {margin-left:-19px;}
.img_lst_wrap:after {content:''; display:block; clear:both;}
.img_lst_wrap > li {float:left; position:relative; width:300px; height:210px; margin:0 0 55px 19px;}
.img_lst_wrap > li > a {display:block; height:150px; background:#f8f8f8; border-radius:4px ; -webkit-border-radius:4px ; -moz-border-radius:4px ; -ms-border-radius:4px ; -o-border-radius:4px ;}
.img_lst_wrap > li > a > img {overflow:hidden; width:100%; border-radius:4px;}
.img_lst_wrap > li .itm_tit {position:absolute; left:0; bottom:40px; padding-left:18px; font-size:15px; color:#FFF;}
.img_lst_wrap > li .itm_date {position:absolute; left:0; bottom:0; padding-left:18px; font-family:'Roboto'; font-size:11px;}
</style>
