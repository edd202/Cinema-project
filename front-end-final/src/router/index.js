import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/reservation',
    name: 'reservation',
    component: () => import('../views/ReservationView.vue')
  },
  {
    path: '/reservation-check',
    name: 'reservation-check',
    component: () => import('../views/ReservationCheck.vue')
  },{
    path: '/customer-center',
    name: 'customer-center',
    component: () => import('../components/customercenter/CustomerCenter.vue')
  },
  {
    path: '/notice',
    name: 'notice',
    component: () => import('../components/customercenter/notiboard/NotiList.vue')
  },
  {
    path: '/notice/:ntcNo',
    name: 'notice-detail',
    component: () => import('../components/customercenter/notiboard/NotiDetail.vue')
  },
  {
    path: '/qna',
    name: 'qna',
    component: () => import('../components/customercenter/qnaboard/QnaList.vue')
  }, 
  {
    path: '/add/qna',
    name: 'add-qna',
    component: () => import('../components/customercenter/qnaboard/AddQna.vue')
  },
  {
    // TODO: path: '/qna/:qno'  로 수정할것
    path: '/qna/:qid',
    name: 'qna-detail',
    component: () => import('../components/customercenter/qnaboard/QnaDetail.vue')
  },
  // Admin Menu
  {
    path: '/admin-movie',
    name: 'admin-movie',
    component: () => import('../components/Admin/movie/AdMovieList.vue')
  },
  {
    path: '/admin-movie/:mid',
    name: 'admin-movie-detail',
    component: () => import('../components/Admin/movie/AdMovieDetail.vue')
  },
  {
    path: '/add/admin-movie',
    name: 'add-admin-movie',
    component: () => import('../components/Admin/movie/AdMovieAdd.vue')
  },
  {
    path: '/admin-qna',
    name: 'admin-qna',
    component: () => import('../components/Admin/qna/AdQnaList.vue')
  },
  {
    path: '/admin-qna/:qid',
    name: 'admin-qna-detail',
    component: () => import('../components/Admin/qna/AdQnaDetail.vue')
  },
  {
    path: '/admin-notice',
    name: 'admin-notice',
    component: () => import('../components/Admin/notice/AdNotiList.vue')
  },
  {
    path: '/add/admin-notice',
    name: 'add-admin-notice',
    component: () => import('../components/Admin/notice/AdNotiAdd.vue')
  },
  {
    path: '/admin-notice/:ntcNo',
    name: 'admin-notice-detail',
    component: () => import('../components/Admin/notice/AdNotiDetail.vue')
  },
  {
    path: '/movie-home',
    name: 'movie-home',
    component: () => import('../components/MovieHome.vue')
  },
  {
    path: '/movie/movieinfo',
    name: 'movie-detail',
    component: () => import('../views/MovieDetail.vue'),
    children:[
      {
        path: '/movie/movieinfo/info/:mid',
        name: 'info',
        component: () => import('../components/moviedetailbar/tab_info.vue')
      },
      {
        path: '/movie/movieinfo/review/:mid',
        name: 'review',
        component: () => import('../components/moviedetailbar/tab_review.vue')
      },
    ]
  },
  {
    path: '/movie-list',
    name: 'movie-list',
    component: () => import('../components/movieList/MovieList.vue'),
    children:[
      {
        path: '/movie-list/current',
        name: 'current',
        component: () => import('../components/movieList/currentCom.vue')
      },
      {
        path: '/movie-list/expected',
        name: 'expected',
        component: () => import('../components/movieList/expectedCom.vue')
      },
    ]
  },
  // 이벤트
  {
    path: '/event',
    name: 'event',
    component: () => import('../views/EventView.vue')
  },
  {
    path: '/event-detail',
    name: 'event-detail',
    component: () => import('../views/EventDetailView.vue')
  },
  {
    path: '/event-detail2',
    name: 'event-detail2',
    component: () => import('../views/EventDetailView2.vue')
  },
  {
    path: '/event-detail3',
    name: 'event-detail3',
    component: () => import('../views/EventDetailView3.vue')
  },
  {
    path: '/event-detail4',
    name: 'event-detail4',
    component: () => import('../views/EventDetailView4.vue')
  },
  // 이벤트
  {
    path: '/event-home',
    name: 'event-home',
    component: () => import('../components/eventContents/eventHome.vue')
  },
  {
    path: '/event-theater',
    name: 'event-theater',
    component: () => import('../components/eventContents/eventTheater.vue')
  },
  {
    path: '/event-premiere',
    name: 'event-premiere',
    component: () => import('../components/eventContents/eventPremiere.vue')
  }, // 로그인 페이지
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/LoginView.vue')
  },
  // 회원가입페이지
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/login/SignUpView.vue')
  },
  // 리다이렉트
  {
    path: '/auth-redirect',
    name: 'auth-redirect',
    // lazy-loaded
    component: () => import('../views/login/Auth2View.vue')
  },
  // 마이페이지
  {
    path: '/mypage',
    name: 'mypage',
    // lazy-loaded
    component: () => import('../views/login/myPage.vue')
  },
  // 소셜회원 마이페이지
  {
    path: '/mypage2',
    name: 'mypage2',
    // lazy-loaded
    component: () => import('../views/login/myPage2.vue')
  },
// 아이디 찾기
{
  path: '/findid',
  name: 'findid',
  // lazy-loaded
  component: () => import('../views/login/findIDView.vue')
},
// 비밀번호찾기
{
  path: '/findpw',
  name: 'findpw',
  // lazy-loaded
  component: () => import('../views/login/findPasswordView.vue')
},

// 스페셜관
{
  path: '/theaters',
  name: 'theaters',
  component: () => import('../views/TheatersView.vue')
},
{
    path: '/4d',
    name: '4d',
    component: () => import('../components/special/4DView.vue')
  },
  {
    path: '/color',
    name: 'color',
    component: () => import('../components/special/ColorView.vue')
  },
  {
    path: '/comfort',
    name: 'comfort',
    component: () => import('../components/special/ComfortView.vue')
  },
  {
    path: '/s',
    name: 's',
    component: () => import('../components/special/SView.vue')
  },
  // 아이디 찾기
{
  path: '/findid',
  name: 'findid',
  // lazy-loaded
  component: () => import('../views/login/findIDView.vue')
},
// 비밀번호찾기
{
  path: '/findpw',
  name: 'findpw',
  // lazy-loaded
  component: () => import('../views/login/findPasswordView.vue')
},
{
  path: '/chart',
  name: 'chart',
  // lazy-loaded
  component: () => import('../components/Admin/chartvue/ChartView.vue')
},

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
