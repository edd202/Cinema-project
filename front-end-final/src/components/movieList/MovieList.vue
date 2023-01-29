<template>
  <div>
    <!-- 헤더 컴포넌트 -->
    <NavBlack></NavBlack>

    <!-- 대형 포스터 시작 -->
    <div class="img-bg rellax">
        <div class="container">
        <img
          src="@/assets/images/header/AA_1920420.jpg"
          alt="Image"
          class="img-fluid"
        />
        </div>
      </div>
      <!-- 대형 포스터 끝 -->

    <!-- 현재 상영작, 상영 예정작 페이지 -->
    <div id="contents" class="contents_movie_list">
      <h2 class="hidden" v-if="false">영화목록</h2>
      <div class="movie_screen_box">
        <ul class="tab_btn_type1">
          <li class="" :class="{ active: isActive }">
            <button type="button" @click="current()"><span><router-link to="/movie-list/current" >현재 상영작</router-link></span></button>
            
          </li>
          <li class="" :class="{ active: !isActive }">
            <button type="button" @click="expected()"><span><router-link to="/movie-list/expected">상영 예정작</router-link></span></button>
          </li>
        </ul>
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from '@/components/common/NavBlack.vue'

export default {
  components: {
    NavBlack
  },
  data() {
    return {
      isActive : true, //현재상영작 <-> 상영예정작 토글
      currentUrl: "", // url 가져오기
      currentHost: "", 
    }
  },
  methods: {
    current(){
      this.isActive = true;
    },
    expected(){
      this.isActive = false;
    }
  },
  mounted() {
    this.currentUrl = window.location.href;
    this.currentHost = window.location.host;
    // console.log(this.currentHost)
    if(this.currentUrl == "http://"+this.currentHost+"/movie-list/expected"){
      this.isActive = false;
    }else if(this.currentUrl == "http://192.168.0.12:"+this.currentPort+"/movie-list/expected"){
      this.isActive = false;
    }else{
      this.isActive = true;
    }
  },
};
</script>

<style>
ul{list-style-type: none;}
a{text-decoration: none; color: #000;}
.contents_movie_list{ font-family: "Noto Sans KR" , "Roboto" , "dotum" , "sans-serif"; }
.contents_movie_list .tit_type1 {margin-bottom: 17px;}
.movie_list.type2 {margin:0 auto; width: 900px; }
.movie_list.type2 li {height:310px; margin-bottom:42px;}
.screen_add_box>a {display:block; width:184px; height:310px; overflow: hidden;}
.screen_add_box>a>img {width:100%; min-height: 100%; border-radius:4px;}
.screen_add_box>a>.num_info{ display:block; position:absolute; left:0; right:0; bottom:0; border-radius: 0 0 4px 4px; padding-left:10px; color:#fff; font-weight:bold; font-size:24px; font-style:italic; font-family:'Roboto', "Noto Sans KR"; text-align:left; background:-moz-linear-gradient(top, rgba(0,0,0,0) 0%, rgba(0,0,0,0.15) 40%, rgba(0,0,0,0.5) 100%); background:-webkit-linear-gradient(top, rgba(0,0,0,0) 0%,rgba(0,0,0,0.15) 40%,rgba(0,0,0,0.5) 100%); background: linear-gradient(to bottom, rgba(0,0,0,0) 0%,rgba(0,0,0,0.15) 40%,rgba(0,0,0,0.5) 100%); -ms-filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#00000000', endColorstr='#80000000', GradientType=0); -webkit-border-radius: 0 0 4px 4px; -moz-border-radius: 0 0 4px 4px; -ms-border-radius: 0 0 4px 4px; -o-border-radius: 0 0 4px 4px;}
.contents_movie_detail .list_lnk button {color:#666;}
.contents_movie_detail .list_lnk .active button {font-weight:normal; color:#000;}
.movie_screen_box {position:relative;}
.movie_screen_box .list_lnk {position:absolute; top:15px; right:0}
.movie_screen_box .list_lnk li {float: left;}
.movie_screen_box .list_lnk li button {font-size:14px; color:#666; border: none; background: none;}
.movie_screen_box .list_lnk li.active button {color:#000; font-weight:normal;}
.tab_btn_type1 {margin-bottom:17px;}
.tab_btn_type1:after {content:''; display:block; clear:both;}
.tab_btn_type1 > li {float:left; margin-right:30px;}
.tab_btn_type1 > li button {padding:0; border:none; background-color: #fff;}
.tab_btn_type1 > li button > span {display:block; height:32px; line-height:32px; font-size:18px; color:#000; opacity:0.6}
.tab_btn_type1 > li.active button > span {border-bottom:1px solid #000; opacity:1}
.movie_list {overflow:hidden; margin-bottom:50px; margin:0 -7.5px}
.movie_list li {float:left; position:relative; width:184px; margin:0 7.5px; text-align:center;}
.movie_list li .top_info {overflow:hidden; position:relative; height:262px; border-radius:4px;}
.movie_list li .top_info .poster_info {display:block; height:262px;}
.movie_list li .top_info .poster_info img {width:100%;}
.movie_list li .top_info .poster_info .num_info {display:block; position:absolute; left:0; right:0; bottom:0; padding-left:10px; color:#fff; font-weight:bold; font-size:26px; font-style:italic; font-family:'Roboto', "Noto Sans KR"; text-align:left; background:-moz-linear-gradient(top, rgba(0,0,0,0) 0%, rgba(0,0,0,0.15) 40%, rgba(0,0,0,0.5) 100%); background:-webkit-linear-gradient(top, rgba(0,0,0,0) 0%,rgba(0,0,0,0.15) 40%,rgba(0,0,0,0.5) 100%); background: linear-gradient(to bottom, rgba(0,0,0,0) 0%,rgba(0,0,0,0.15) 40%,rgba(0,0,0,0.5) 100%); filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#00000000', endColorstr='#80000000',GradientType=0 );}
.movie_list li .top_info .poster_info .ic_grade {position:absolute; top:10px; right:8px;}
/* .movie_list li .top_info .over_box {overflow:hidden; position:absolute; width:100%; height:0;} */
/* .movie_list li.active .top_info .over_box {overflow:inherit; position:absolute; z-index:1; top:0; left:0; width:100%; height:100%;} */
/* .movie_list li.active .top_info .over_box:before {content:''; position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.7;} */
/* .movie_list li.active .top_info .over_box .inner {position:absolute; z-index:1; top:50%; left:0; right:0; padding:0 18px;} */
/* .movie_list li .top_info .over_box .btn_col3 {display:block; line-height:26px; margin-top:10px; background:transparent; color:#fff !important; border-color:#fff; outline-color: white;} */
/* .movie_list li .top_info .over_box .btn_col3:first-child {margin-top:0} */
/* .movie_list li .top_info .over_box .hall_info_box {position:absolute; top:0; left:0; width:100%; background:red} */
/* .movie_list li .top_info .over_box .btn_end {border-color:#666; color:#666 !important} */
.movie_list li .top_info .over_box {width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.7); line-height: 200px; position: absolute; top: 0; left: 0; opacity: 0; transition: 0.3s; }
.movie_list li .top_info .over_box .inner {position:absolute; z-index:1; top:50%; left:0; right:0; padding:0 18px;}
.movie_list li .top_info .over_box .btn_col3{display:block; line-height:26px; margin-top:10px; background:transparent; color:#fff !important; border-color:#fff; outline-color: white; border: 1px solid; border-radius: 5px;}
.movie_list li .top_info:hover .over_box, .movie_list li .top_info:focus .over_box {opacity: 1;}
.movie_list li .btm_info {display:block; margin-top:14px;}
.movie_list li .btm_info .tit_info {display:block; overflow:hidden; white-space:nowrap; text-overflow:ellipsis; margin-bottom:6px; font-size:13px;}
.movie_list li .btm_info .sub_info1 {display:block; margin-bottom:10px; font-size:11px;}
.movie_list li .btm_info .sub_info1 > span {position:relative; margin-left:8px; font-family:'Roboto', "Noto Sans KR";}
.movie_list li .btm_info .sub_info1 > span:before {content:''; display:inline-block; position:absolute; top:2px; left:0; width:1px; height:8px; background:#B2B2B2}
.movie_list li .btm_info .sub_info1 > span:first-child {margin-left:0;}
.movie_list li .btm_info .sub_info1 > span:first-child:before{display:none;}
.movie_list li .btm_info .sub_info1 .rate_info em {font-weight:bold;}
.movie_list li .btm_info .sub_info1 .star_info {padding-left:22px; font-weight:bold; background:url(https://www.lottecinema.co.kr/NLCHS/Content/images/movie/ic_star.png) no-repeat 9px 20%;}
.movie_list li .btm_info .sub_info1 .remain_info {padding-left:10px; }
.movie_list li .btm_info .sub_info1 .heart_info {display:inline-block; padding-left:10px;}
.movie_list li .btm_info .sub_info1 .heart_info .btn_heart_rnd {margin-top:-4px;}
.movie_list li .btm_info .sub_info2 {display:block;}
.movie_list li .btm_info .sub_info2 > span {display:inline-block; padding:4px 0 4px 21px; font-family:'Roboto', "Noto Sans KR"; font-size:11px;}
</style>
