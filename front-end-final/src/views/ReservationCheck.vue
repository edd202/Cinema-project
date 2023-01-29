<template>
  <div>

    <!-- 로그인 되어있으면 바로 확인가능 -->
    <!-- 로그인 안되어있으면 로그인페이지로 이동 -->
    <!-- 비회원은 비회원예매확인 페이지로 이동 -->

    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    


    <!-- 대제목 시작 -->
    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h2 class="cc_tit">예약 티켓 확인</h2>
      </div>
    </div>
    <!-- 대제목 끝 -->



    <!-- 예매 티켓 확인 시작 -->
    <div>
      <div class="cc_tab_content">

        <!-- 영화 이미지 -->
        <div class="cc_movie_img">
          <img
            :src="poster"
            style="width: 350px; height: 500px"
            alt="예약 티켓"
          />
        </div>

        <!-- 영화정보 테이블 -->
        <div v-for="(data, index) in ticket" :key="index">
        <div class="cc_movie_info">
          <table class="tb_acc_wrap">
            <colgroup>
              <col style="width: 15%" />
              <col style="width: auto" />
            </colgroup>
            <tbody>
              <tr>
                <th>제목</th>
                <td>
                  <div class="font">{{ data.tkTitle }}</div>
                </td>
              </tr>
              <tr>
                <th>일시</th>
                <td>
                  <div class="font">  {{ startDate }}일</div>
                  <div> <br> </div>
                  <div class="font"> 시작시간 : {{ startTime }}</div>
                  <div class="font"> 종료시간 : {{ data.tkEndTime }}</div>
                </td>
              </tr>
              <tr>
                <th>상영관</th>
                <td>
                  <div class="font">{{ data.tkTheater }}</div>
                </td>
              </tr>
              <tr>
                <th>좌석</th>
                <td>
                  <div v-for="(data,index) in seatNum.sort()" :key="index" class="font" >{{ data }}</div>
                </td>
              </tr>
            </tbody>
          </table>

          <div class="cc_pay">
            <span class="cc_pay_tit">결제금액</span>
            <span class="cc_pay_amount">{{price}}원</span>
          </div>
          
        </div>

        <div class="btn_btm_wrap">
          <a class="btn_col3 ty6" @click="cancelTicket(ticketId)">예매취소</a>
          <router-link to="/" class="btn_col2 ty6">확인</router-link>
        </div>

      </div>
    </div>
    </div>
    <!-- 예매 티켓 확인 끝 -->


    


  </div>
</template>

<script>
// @ is an alias to /src
import NavBlack from "@/components/common/NavBlack.vue";
import ReservationCheckService from "@/services/ReservationCheckService";
import userService from "@/services/userServices/user.service";
import User from "@/models/user";
export default {
  data() {
    return {
      ticket: [],
      currentIndex: -1,
      userId:'',
      phone:'',
      password:'',
      price:'',
      startTime:'',
      startDate:'',
      seatNum:'',
      poster:'',
      ticketId:'',
      userID:'',
      user:new User('','','','','','','','',''),
      loginPopup:false,

    };
  },
  components: {
    NavBlack,
  },
  methods: {

    cancelTicket(ticketId){

      console.log("티켓아이디"+this.ticketId);

      ReservationCheckService.cancelReservationTicket(ticketId)
      .then(response=>{
        console.log("취소하고 받은 값"+response.data)
        alert("예약이 취소 되었습니다.")
        this.$router.push("/");
      })
      .catch(e=>{
        console.log(e)
      })
    },

    // 목록을 클릭했을때 현재 부서객체, 인덱스번호를 저장하는 함수
    setActiveDept(data, index) {
      this.currentDept = data;
      this.currentIndex = index;
    },


    // 회원 티켓 정보 가져오기
    getMemTicket(Id){
      ReservationCheckService.getMovieReservationCheckMem(Id)
      .then(response=>{
        this.ticket=response.data.Ticket;
        this.price = this.ticket[0].tkPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        let day = this.ticket[0].tkStartTime.split("일");
        this.startDate=day[0];
        console.log("일"+day[0])
        console.log("시간"+day[1])
        this.startTime=day[1];
        this.seatNum = this.ticket[0].tkSeat.split("좌석");
        this.poster = response.data.PosterUri;
        this.ticketId=this.ticket[0].tkId;
      })
      .catch(e=>{
        console.log(e);
      })
    },

    // 비회원 티켓 정보 가져오기
    getNonmemTicket(number , pass ){

      ReservationCheckService.getMovieReservationCheckNonmem(number , pass)
      .then(response=>{
        this.ticket=response.data.Ticket;
        this.price = this.ticket[0].tkPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        let day = this.ticket[0].tkStartTime.split("일");
        this.startDate=day[0];
        console.log("일"+day[0])
        console.log("시간"+day[1])
        this.startTime=day[1];
        this.seatNum = this.ticket[0].tkSeat.split("좌석");
        this.poster = response.data.PosterUri;
        this.ticketId=this.ticket[0].tkId;
      })
      .catch(e=>{
        console.log(e);
      })
    },
    getUser(username) {
      userService
        .getUserProfile(username)
        .then((reponse) => {
          this.geteduser = reponse.data;
          this.user = this.geteduser;
          localStorage.setItem("user",JSON.stringify(this.user))
        })
        .catch((e) => {
          console.log(e);
        });
    },

  },
  mounted() {
    
    //  회원&비회원 로그인 여부로 함수 실행
    // if(this.loggedIn){
    // 
    //   this.getMemTicket(JSON.parse(localStorage.getItem("user")).id);
    //   this.userId=JSON.parse(localStorage.getItem("user")).id;
    // }
    // else if(this.loggedIn2){
    //   this.phone= JSON.parse(localStorage.getItem("nonUser")).phone;
    //   this.password= JSON.parse(localStorage.getItem("nonUser")).password;
    //     console.log("phone"+this.phone + ", pass :"+ this.password);
    //   this.getNonmemTicket(this.phone , this.password );
    //   }
  
  },
  created(){
    console.log("회원로그인여부:"+this.loggedIn+"비회원로그인여부:"+this.loggedIn2)
    if(!this.loggedIn&&!this.loggedIn2){
      this.loginPopup=true;
      alert("로그인이 필요합니다.")
      this.$router.push("/login");
      } else if(this.loggedIn2){
      this.phone= JSON.parse(localStorage.getItem("nonUser")).phone;
      this.password= JSON.parse(localStorage.getItem("nonUser")).password;
        console.log("phone"+this.phone + ", pass :"+ this.password);
      this.getNonmemTicket(this.phone , this.password );
      }else if(this.loggedIn){
        if(JSON.parse(localStorage.getItem("user")).username.includes("@")){
          this.userID = JSON.parse(localStorage.getItem("user")).username;
      this.getUser(this.userID);
        }
        this.getMemTicket(JSON.parse(localStorage.getItem("user")).id);
        this.userId=JSON.parse(localStorage.getItem("user")).id;
      }
      
    },
   
  
   

  computed:{
    // 로그인여부 가져오는 함수
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    // 비회원로그인여부 가져오는 함수
    loggedIn2() {
      return this.$store.state.auth.initialState2.status.loggedIn2;
    },
  },
};
</script>

<style scoped>


button {
  border: 0px solid #ddd;
  cursor: pointer;
  background-color: #fff;
}
input[type="password"],
input[type="text"],
input[type="number"],
input[type="tel"] {
  height: 40px;
  line-height: 40px;
  padding: 0 18px;
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  background-color: #fff;
  font-size: 14px;
  box-sizing: border-box;
  border: 1px solid #ddd;
}
input[type="password"].ty2,
input[type="text"].ty2,
input[type="number"].ty2,
input[type="tel"].ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
}
input,
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}
a,
a:link,
a:visited,
a:active,
a:hover {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
ul,
ol {
  list-style: none;
}

.cc_title_wrap {
  margin-top: 50px;
}
.cc_title_top {
  width: 980px;
  margin: auto;
  height: 50px;
  border-bottom: 1px solid #eee;
}
.cc_title_top .cc_tit {
  float: left;
  font-size: 24px;
  color: #000;
  line-height: 50px;
}
.cc_tab_wrap.cc_outer {
  padding-top: 20px;
}
.cc_tab_content {
  overflow: inherit;
  position: relative;
  width: 980px;
  height: 750px;
  margin: 60px auto;
}

.cc_movie_info {
  position: relative;
  left: 400px;
  bottom: 500px;
  width: 580px;
}
.tb_acc_wrap {
  width: 580px;
  margin: auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
  border-bottom: 1px solid #eee;
}
.tb_acc_wrap th {
  font-size: 15px;
  color: #666666;
  text-align: center;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  padding: 27px 0;
}
.tb_acc_wrap td {
  text-align: left;
  font-size: 19px;
  font-weight: bold;
  padding-left: 40px;
}
.cc_pay {
  margin-top: 20px;
  padding-right: 40px;
  text-align: right;
}
.cc_pay_tit {
  margin-right: 20px;
  font-size: 15px;
  font-weight: 600;
  color: #666666;
}
.cc_pay_amount {
  font-size: 30px;
  font-weight: bold;
  color: #ff243e;
}

.btn_btm_wrap {
  position: relative;
  bottom: 400px;
  margin: auto;
  text-align: center;
}
.btn_col2.ty6,
.btn_col3.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_btm_wrap a,
.btn_btm_wrap button {
  margin: 0 3px;
  min-width: 160px;
}
.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #fff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}
.btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}


h1,
h2,
h3,
h4,
h5,
h6,
address,
caption,
em,
th {
  font-style: normal;
  font-weight: normal;
}


.font {
  font-size: 17px;
  font-style: normal;
  font-weight: bold;
}

ul,
ol {
  list-style: none;
}

ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}

li {
  display: list-item;
  text-align: -webkit-match-parent;
  list-style: none;
}

</style>