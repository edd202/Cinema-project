<template>
  <Doughnut :options="options" :data="chartData" v-if="loaded" />
</template>
  
  <script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { Doughnut } from "vue-chartjs";
import MasterService from "@/services/MasterService";

ChartJS.register(ArcElement, Tooltip, Legend);
ChartJS.defaults.color = 'white';
ChartJS.defaults.borderColor = 'white';
export default {
  name: "DoughnutChart",
  components: {
    Doughnut,
  },

  data() {
    return {
      salesData:"",
      movieTitle:new Array(),
      movieSales:new Array(),
      loaded:false,
     
      options: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  mounted(){
    this.movieTitle[0]="현재 집계중"
        this.movieSales[0]=0;
    MasterService.getMoiveSale()
      .then((response)=>{
        for(let i=0;i<response.data.length;i++){
          this.movieTitle[i]=response.data[i].title
        this.movieSales[i]=response.data[i].sales
        }
        console.log("영화제목들"+this.movieTitle);
        console.log("각영화매출"+this.movieSales);
        this.loaded = true;
      })
      .catch((e)=>{
        console.log("오류는"+e)
        this.movieTitle[0]="현재 집계중"
        this.movieSales[0]=0;
        this.loaded = true;
      })
    
  },
  computed:{
    chartData(){
      return{
        labels: this.movieTitle,
        datasets: [
          {
            backgroundColor: ["#FF61D0", "#DABAFF","#ffd966","#41B883","#0000ff","#4b0082","#800080"],
            data: this.movieSales,
          },
        ],
      }
       
      },
  }

};
</script>
  