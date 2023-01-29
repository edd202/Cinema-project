<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div id="contents" class="contents_event_theater" style="">
      <div class="title_top ty2">
        <h2 class="tit">시사회/무대인사</h2>
        <div class="group_rgt">
        </div>
      </div>
      <ul class="img_lst_wrap">
        <li>
                <router-link to="/event-detail2">
                <img src="https://caching.lottecinema.co.kr//Media/Event/4c1ee91aa243496ab82410c930f3cdf7.jpg" alt="<해시태그 시그네> GV"></router-link>
              </li>
              <li>
                <router-link to="/event-detail3">
                  <img src="https://caching.lottecinema.co.kr//Media/Event/b3737c2ae7e5465e8cac1d5a3b77bf1f.jpg" alt="<오늘 밤, 세계에서> 앵콜 상영회"></router-link>
              </li>
              <li>
                <router-link to="/event-detail4">
                  <img src="https://caching.lottecinema.co.kr//Media/Event/2a8c891b0b0d4d9796afb8c3360d6687.jpg" alt="<장화신은 고양이> 무대인사"></router-link>
              </li>
      </ul>
      
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
.contents_event_theater .img_lst_wrap img {height:184px;}
#contents.contents_event_theater > .title_top.ty2 {padding:0; margin-top:28px; margin-bottom:18px;}
#contents.contents_event_theater .tbl_list {margin-top:20px;}
#contents.contents_event_theater > .title_top.ty2 + .tab_wrap.outer {margin-top:32px;}
#contents > .title_top {margin-bottom:20px; padding:25px 0 14px; border-bottom:1px solid #EEE;}
#contents > .title_top:after {content:""; display:block; clear:both;}
#contents > .title_top.ty2 {margin-bottom:10px; border-bottom:none;}
#contents > .title_top .tit {float:left; font-size:24px; color:#000;}
#contents > .title_top .group_rgt {float:right;}
#contents > .title_top .group_rgt .list_lnk {margin-top:10px;}
#contents > .title_top .group_rgt .bx_search {margin-top:-7px;}
#contents > .title_top .group_rgt .bx_search.ty2 {margin-top:0;}
.bx_search {display:inline-block; overflow:hidden; height:40px; padding:0 20px 0 10px; border-radius:41px; border:1px solid #707070; background-color:#FFF;}
.bx_search input {width:240px; height:42px; margin-top:-1px; padding-left:10px; border:none; background-color:transparent;}
.bx_search.ty2 {overflow:inherit; height:30px; border:0 none; border-radius:0; padding:0; -webkit-border-radius:0; -moz-border-radius:0; -ms-border-radius:0; -o-border-radius:0;}
.bx_search.ty2 select {margin-right:9px; width:110px; height:29px; line-height:29px; font-size:13px;}
.bx_search.ty2 input {border:1px solid #DDDDDD; width:175px; height:29px; line-height:29px; padding:0 18px; font-size:13px;}
.bx_search.ty2 button {padding:0 30px; margin-left:9px;}
.btn_col2 {display:inline-block; box-sizing:border-box; border-radius:4px; border:1px solid #414141; font-size:14px; color:#ffffff !important; text-align:center; vertical-align:middle; background-color:#414141;}
.btn_col2.ty3{height:28px; line-height:26px; padding:0 14px;}
.img_lst_wrap {margin-left:-19px; }
.img_lst_wrap:after {content:''; display:block; clear:both;}
.img_lst_wrap > li {float:left; position:relative; width:300px; height:210px; margin:0 0 55px 19px;}
.img_lst_wrap > li > a {display:block; color: #000; height:150px; background:#f8f8f8; border-radius:4px ; -webkit-border-radius:4px ; -moz-border-radius:4px ; -ms-border-radius:4px ; -o-border-radius:4px ;}
.img_lst_wrap > li > a > img {overflow:hidden; width:100%; border-radius:4px;}
.img_lst_wrap > li .itm_tit {position:absolute; left:0; bottom:40px; padding-left:18px; font-size:15px; color:#FFF;}
.img_lst_wrap > li .itm_date {position:absolute; left:0; bottom:0; padding-left:18px; font-family:'Roboto'; font-size:11px;}
</style>
