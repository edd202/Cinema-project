<template>
  <div>
    <NavBlack />
    <!-- 본문시작 -->
    <div class="section" style="background-color:black;">
      <!-- Sale & Revenue Start -->
      <div class="container-fluid pt-4 px-4">
        <div class="row g-4">
          <div class="col-sm-6 col-xl-3">
            <div
              class="
                bg-lightgrey
                rounded
                d-flex
                align-items-center
                justify-content-between
                p-4
              "
            >
              <i class="fa fa-chart-line fa-3x"></i>
              <div class="ms-3">
                <p class="mb-2">{{ year }}년도 {{ month }}월 매출액</p>
                <h6 class="mb-0">{{monthSales}}원</h6>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div
              class="
                bg-lightgrey
                rounded
                d-flex
                align-items-center
                justify-content-between
                p-4
              "
            >
              <i class="fa fa-chart-bar fa-3x"></i>
              <div class="ms-3">
                <p class="mb-2">{{ year }}년도 총 매출액</p>
                <h6 class="mb-0">{{ yearSales }}원</h6>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div
              class="
                bg-lightgrey
                rounded
                d-flex
                align-items-center
                justify-content-between
                p-4
              "
            >
              <i class="fa fa-chart-area fa-3x"></i>
              <div class="ms-3">
                <p class="mb-2">{{ year }}년도 총방문객</p>
                <h6 class="mb-0">{{ yearVisitor }}명</h6>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div
              class="
                bg-lightgrey
                rounded
                d-flex
                align-items-center
                justify-content-between
                p-4
              "
            >
              <i class="fa fa-chart-pie fa-3x"></i>
              <div class="ms-3">
                <p class="mb-2">현재 총 회원수</p>
                <h6 class="mb-0">{{ userCount }}명</h6>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Sale & Revenue End -->

      <!-- 차트 뷰 -->
      <div class="container-fluid pt-4 px-4">
        <div class="row g-4">
          <!-- 1번차트 -->
          <div class="col-sm-12 col-xl-6">
            <div class="bg-lightgrey text-center rounded p-4" style="height:391px !important;">
              <div
                class="d-flex align-items-center justify-content-between mb-4"
              >
                <h6 class="mb-0">{{year}}년도 지점별 매출</h6>
              </div>
              <div style="width: 600px">
                <barChart/>
              </div>
            </div>
          </div>
          <!-- 2번차트 -->
          <div class="col-sm-12 col-xl-6">
            <div class="bg-lightgrey text-center rounded p-4"  style="height:391px !important;">
              <div
                class="d-flex align-items-center justify-content-between mb-4"
              >
                <h6 class="mb-0">월별 영화 예매 수 현황</h6>
              </div>
              <div style="width: 600px; height: 261px">
                <lineChart />
              </div>
            </div>
          </div>
          <!-- 3번 차트 -->
          <div class="col-sm-12 col-xl-6">
            <div class="bg-lightgrey text-center rounded p-4">
              <div
                class="d-flex align-items-center justify-content-between mb-4"
              >
                <h6 class="mb-0">{{ month }}월 영화별 매출 비율</h6>
              </div>
              <div style="width: 600px; height: 261px">
                <DoughnutChart2 />
              </div>
            </div>
          </div>
          <!-- 4번 차트 -->
          <div class="col-sm-12 col-xl-6">
            <div class="bg-lightgrey text-center rounded p-4">
              <div
                class="d-flex align-items-center justify-content-between mb-4"
              >
                <h6 class="mb-0">
                  회원, 비회원 예매 비율
                </h6>
              </div>
              <div style="width: 600px; height: 261px">
                <DoughnutChart />
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Sales Chart End -->
      <!-- 본문 끝 -->
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import barChart from "./BarChart";
import lineChart from "./LineChart";
import DoughnutChart from "./DoughnutChart";
import DoughnutChart2 from "./DoughnutChart2";
import MasterService from "@/services/MasterService";

export default {
  components: {
    NavBlack,
    barChart,
    lineChart,
    DoughnutChart,
    DoughnutChart2
  },
  data() {
    return {
    
      today: new Date(),
      sales: 0,
      userCount: 0,
      yearSale: 0,
      yearVisitor: 0,
      monthSales:0,
      yearSales:0,
      customerCount:0,
      memTk:0,
      nonMemTk:0,
      
    };
  },
  computed: {
    month() {
      let todayMonth = new String;
      todayMonth=(this.today.getMonth() + 1).toString();
      if(todayMonth.length<2){
        todayMonth="0"+todayMonth
      }
      return todayMonth;
    },
    year() {
      let todayYear = this.today.getFullYear();
      return todayYear;
    },
  },
  mounted() {
    MasterService.getCinemaSales()
      .then((response) => {
        // console.log(response.data);
        // console.log(response.data.length);
        // console.log(response.data[0].sales);
        // let arr=new Array(12);
        let result =0;
        for (var i = 0; i < response.data.length; i++) {
          // console.log("이번달은"+this.month);
          //   console.log("이번달 매출"+response.data[i].sales);
            console.log(i+"월"+response.data[i].month.substr(5,6))
          if (response.data[i].month.substr(5,6)==this.month) {
          
           result=result+response.data[i].sales;
          }
        }
        this.monthSales = result.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        // console.log("이번달 매출"+result);
      })
      .catch((e) => {
        console.log(e);
      });
      MasterService.getCinemaSales()
      .then((response) => {
        // console.log(response.data);
        // console.log(response.data.length);
        // console.log(response.data[0].sales);
        // let arr=new Array(12);
        let result =0;
        for (var i = 0; i < response.data.length; i++) {
           result=result+response.data[i].sales;
        }
        this.yearSales = result.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        console.log(this.yearSales);
      })
      .catch((e) => {
        console.log(e);
      });
      MasterService.getCountUser()
      .then((response)=>{
        this.userCount=response.data[0].userCount;
        console.log("총방문객은"+response.data[0].userCount);
      })
      .catch((e)=>{
        console.log(e)
      });
      MasterService.getCountMemTk()
      .then((response)=>{
        this.yearVisitor=this.yearVisitor+response.data[0].userCount
        console.log("회원예매"+response.data[0].userCount)
      })
      .catch((e)=>{
        console.log(e)
      })
      MasterService.getCountNonMemTk()
      .then((response)=>{
        this.yearVisitor=this.yearVisitor+response.data[0].userCount
        console.log("비회원예매"+response.data[0].userCount)
      })
      .catch((e)=>{
        console.log(e)
      })
      MasterService.getMonthVistor()
      .then((response)=>{
        console.log(response.data)
      })
  },
  
};
</script>

<style scoped>
.bg-lightgrey {
  background-color: rgb(28, 28, 28);
  color: white;
}
.section {
  border-bottom: 1px solid black;
  border-top: 1px solid black;
  padding-bottom: 35px;
  margin-top: 35px;
}
</style>