<template>
  <div>
    <div>
      <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close">
            <span class="icofont-close js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>

      <nav class="site-nav2 mt-3 nav2-bb">
        <div class="container">
          <div class="site-navigation2">
            <div class="row">
              <div class="col-6 col-lg-3"></div>
              <div
                class="col-lg-6 d-none d-lg-inline-block text-center nav-center-wrap"
                style="height: 30px"
              >
                <ul class="js-clone-nav text-center site-menu p-0 m-0">
                  <li class="logo_ds">
                    <router-link to="/">
                      <img
                        src="@/assets/images/icon/logo_transparent_black.png"
                        width="150px"
                        style="margin-top: -70px"
                        alt=""
                      />
                    </router-link>
                  </li>
                </ul>
              </div>
              <div class="col-6 col-lg-3 text-lg-end"></div>
            </div>
            <div class="row mt-4" style="width: 980px; margin: 0 auto; border-top: 0.5px solid rgba(0, 0, 0, 0.3);">
              <div class="col-6 col-lg-3"></div>
              <div
                class="col-lg-6 d-none d-lg-inline-block text-center nav-center-wrap"
              >
                <ul class="js-clone-nav text-center site-menu p-0 m-0">
                  <li class="has-children">
                    <router-link to="/reservation">예매</router-link>
                    <ul class="dropdown">
                      <li>
                        <router-link to="/reservation">예매하기</router-link>
                      </li>
                      <li>
                        <router-link to="/reservation-check"
                          >예매확인</router-link
                        >
                      </li>
                    </ul>
                  </li>
                  <li class="has-children">
                    <router-link to="/movie-home">영화</router-link>
                    <ul class="dropdown">
                      <li>
                        <router-link to="/movie-list/current"
                          >현재상영작</router-link
                        >
                      </li>
                      <li>
                        <router-link to="/movie-list/expected"
                          >상영예정작</router-link
                        >
                      </li>
                    </ul>
                  </li>
                  <li class="has-children">
                    <router-link to="/theaters">스페셜관</router-link>
                    <ul class="dropdown">
                    <li><router-link to="/comfort">씨네 컴포트관</router-link></li>
                    <li><router-link to="/color">컬러리움관</router-link></li>
                    <li><router-link to="/4d">수퍼4D관</router-link></li>
                    <li><router-link to="/s">수퍼S관</router-link></li>
                    </ul>
                  </li>
                  <li >
                  <router-link to="/event-premiere"
                        >시사회/무대인사</router-link
                      >
                  
                </li>
                  <li class="has-children" v-show="adminRole">
                  <router-link to="/admin-movie">관리자</router-link>
                  <ul class="dropdown">
                    <li><router-link to="/admin-movie">영화목록</router-link></li>
                    <li><router-link to="/admin-notice">공지사항</router-link></li>
                    <li><router-link to="/admin-qna">1:1문의</router-link></li>
                    <li v-show="masterRole">
                        <router-link to="/chart">매출 통계</router-link>
                      </li>
                  </ul>
                </li>
                </ul>
              </div>
              <div v-if="!currentUser" class="col-6 col-lg-3 text-lg-start">
                <ul
                  class="js-clone-nav d-none d-lg-inline-block text-end mini-menu" 
                >
                  <li><router-link to="/login" class="btn_my">로그인</router-link></li>
                  <li><router-link to="/signup" class="btn_regist">회원가입</router-link></li>
                  <li>
                    <router-link to="/customer-center">고객센터</router-link>
                  </li>
                </ul>
                <a
                  href="#"
                  class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
                  data-toggle="collapse"
                  data-target="#main-navbar"
                >
                  <span></span>
                </a>
              </div>

              <div v-if="currentUser" class="col-6 col-lg-3 text-lg-end">
                <ul
                  class="js-clone-nav d-none d-lg-inline-block text-end mini-menu"
                >
                
                <li v-show="onlyMember" ><router-link to="/mypage">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-break-fill" viewBox="0 0 16 16">
  <path d="M4 0h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V9H2V2a2 2 0 0 1 2-2zm5.5 1.5v2a1 1 0 0 0 1 1h2l-3-3zM2 12h12v2a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2v-2zM.5 10a.5.5 0 0 0 0 1h15a.5.5 0 0 0 0-1H.5z"/>
</svg>
                  마이페이지</router-link></li>
                  <li>
                    <a @click="logOut()" style="cursor: pointer">로그아웃</a>
                  </li>
                  <li>
                    <router-link to="/customer-center">고객센터</router-link>
                  </li>
                </ul>
                <a
                  href="#"
                  class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
                  data-toggle="collapse"
                  data-target="#main-navbar"
                >
                  <span></span>
                </a>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      adminRole:false,
      masterRole:false,
      onlyMember:false,
    };
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$store.dispatch("auth/nonUserLogout");
      this.$router.go("/");
    },
  },
  computed: {
    currentUser() {
      // console.log("값은"+this.$store.state.auth.initialState.user+this.$store.state.auth.initialState2.nonUser)
      if (
        !this.$store.state.auth.initialState.user &&
        !this.$store.state.auth.initialState2.nonUser
      ) {
        return false;
      } else {
        return true;
      }
    },
  },
  created() {
    if(this.$store.state.auth.initialState.user){
      this.onlyMember=true;
    if(JSON.parse(localStorage.getItem("user")).roles=="ROLE_ADMIN"||JSON.parse(localStorage.getItem("user")).roles=="ROLE_MASTER"){
      if(JSON.parse(localStorage.getItem("user")).roles=="ROLE_MASTER"){
        return this.adminRole=true,this.masterRole=true;
      }else{
        return this.adminRole=true;
      }
   }else{
    return this.adminRole=false;
   }
  }else{
    return this,this.adminRole=false;
  }
}
};
</script>

<style>


.site-nav2 {
  position: relative;
  top: 0;
  z-index: 9;
  width: 100%;
}

.site-nav2 {
  padding-top: 20px;
  padding-bottom: 0px;
}

.site-nav2 h1,
.site-nav2 .h1 {
  margin: 0;
  padding: 0;
  font-size: 24px;
}

.site-nav2 .site-navigation2 .site-menu {
  margin-bottom: 0;
}

.site-nav2 .site-navigation2 .site-menu > li {
  display: inline-block;
}

.site-nav2 .site-navigation2 .site-menu > li > a {
  font-size: 14px;
  padding: 10px 15px;
  display: inline-block;
  text-decoration: none !important;
  color: rgba(0, 0, 0, 0.7);
}

.site-nav2 .site-navigation2 .site-menu > li > a:hover {
  color: #f00;
}

.site-nav2 .site-navigation2 .site-menu > li.cta-button a {
  padding: 6px 20px;
  border-radius: 30px;
  background: #f17945;
  border: 2px solid #f17945;
}

.site-nav2 .site-navigation2 .site-menu > li.cta-button a:hover {
  color: #fff;
  background: transparent;
  border: 2px solid rgba(255, 255, 255, 0.2);
}

.site-nav2 .site-navigation2 .site-menu > li.active > a {
  color: #fff;
}

.site-nav2 .site-navigation2 .site-menu .has-children {
  position: relative;
}

.site-nav2 .site-navigation2 .site-menu .has-children > a {
  position: relative;
  padding-right: 20px;
}

.site-nav2 .site-navigation2 .site-menu .has-children > a:before {
  position: absolute;
  content: "\e313";
  font-size: 14px;
  top: 50%;
  right: 0;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  font-family: "icomoon";
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown {
  visibility: hidden;
  opacity: 0;
  top: 100%;
  z-index: 999;
  position: absolute;
  text-align: left;
  -webkit-box-shadow: 0 2px 10px -2px rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 10px -2px rgba(0, 0, 0, 0.1);
  padding: 20px 0;
  margin-top: 20px;
  margin-left: 0px;
  background: #fff;
  -webkit-transition: 0.2s 0s;
  -o-transition: 0.2s 0s;
  transition: 0.2s 0s;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown.arrow-top {
  position: absolute;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown.arrow-top:before {
  display: none;
  bottom: 100%;
  left: 20%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown.arrow-top:before {
  display: none;
  border-color: rgba(136, 183, 213, 0);
  border-bottom-color: #fff;
  border-width: 10px;
  margin-left: -10px;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown a {
  font-size: 14px;
  text-transform: none;
  letter-spacing: normal;
  -webkit-transition: 0s all;
  -o-transition: 0s all;
  transition: 0s all;
  color: #000;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown .active a {
  color: #f17945 !important;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown > li {
  list-style: none;
  padding: 0;
  margin: 0;
  min-width: 180px;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown > li > a {
  padding: 5px 20px;
  display: block;
}

.site-nav2 .site-navigation2 .site-menu .has-children .dropdown > li > a:hover {
  color: #f17945;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children
  > a:before {
  content: "\e315";
  right: 20px;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children
  > .dropdown,
.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children
  > ul {
  left: 100%;
  top: 0;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children
  > .dropdown
  li
  a:hover,
.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children
  > ul
  li
  a:hover {
  color: #f17945;
}

.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children:hover
  > a,
.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children:active
  > a,
.site-nav2
  .site-navigation2
  .site-menu
  .has-children
  .dropdown
  > li.has-children:focus
  > a {
  color: #f17945;
}

.site-nav2 .site-navigation2 .site-menu .has-children:hover > a,
.site-nav2 .site-navigation2 .site-menu .has-children:focus > a,
.site-nav2 .site-navigation2 .site-menu .has-children:active > a {
  color: #f00;
}

.site-nav2 .site-navigation2 .site-menu .has-children:hover,
.site-nav2 .site-navigation2 .site-menu .has-children:focus,
.site-nav2 .site-navigation2 .site-menu .has-children:active {
  cursor: pointer;
}

.site-nav2 .site-navigation2 .site-menu .has-children:hover > .dropdown,
.site-nav2 .site-navigation2 .site-menu .has-children:focus > .dropdown,
.site-nav2 .site-navigation2 .site-menu .has-children:active > .dropdown {
  -webkit-transition-delay: 0s;
  -o-transition-delay: 0s;
  transition-delay: 0s;
  margin-top: 0px;
  visibility: visible;
  opacity: 1;
}

.nav2-bb {
  border-bottom: 1px solid #eee;
}

.site-nav2 .site-navigation2 .mini-menu {
  margin-bottom: 0;
  margin-top: 4px;
  /* padding-right: 20px; */
  padding-left: 10px;
}
.site-nav2 .site-navigation2 .mini-menu > li .btn_my {
  padding-left: 15px;
  background-size: auto;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/nav_side_my.png) 0 center;
  background-repeat: no-repeat;
  background-position-y: 11px;
}


.site-nav2 .site-navigation2 .mini-menu > li {
  display: inline-block;
}

.site-nav2 .site-navigation2 .mini-menu > li > a {
  font-size: 10px;
  padding: 10px 8px;
  display: inline-block;
  text-decoration: none !important;
  color: rgba(0, 0, 0, 0.7);
}
.site-nav2 .site-navigation2 .mini-menu > li > a:hover {
  color: #f17945;
}
.site-nav2 .site-navigation2 .mini-menu > li.cta-button a {
  padding: 6px 20px;
  border-radius: 30px;
  background: #f17945;
  border: 2px solid #f17945;
}
.site-nav2 .site-navigation2 .mini-menu > li.cta-button a:hover {
  color: #fff;
  background: transparent;
  border: 2px solid rgba(0, 0, 0, 0.2);
}
.site-nav2 .site-navigation2 .mini-menu > li.active > a {
  color: #fff;
}
</style>
