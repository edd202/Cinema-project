<template>
  <Doughnut :options="options" :data="chartData" v-if="loaded1&&loaded2" />
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
      yearVisitor:new Array(2),
      loaded:false,
      loaded1:false,
      loaded2:false,
     
      options: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  mounted(){
    MasterService.getCountMemTk()
      .then((response)=>{
        this.yearVisitor[0]=response.data[0].userCount
        this.loaded1 = true;
      })
      .catch((e)=>{
        console.log(e)
      })
      MasterService.getCountNonMemTk()
      .then((response)=>{
        this.yearVisitor[1]=response.data[0].userCount
        console.log("도넛 데이터"+this.yearVisitor)
        this.loaded2 = true;
        
      })
      
  },
  computed:{
    chartData(){
      return{
        labels: [ "회원","비회원"],
        datasets: [
          {
            backgroundColor: ["#2986cc", "#f44336"],
            data: this.yearVisitor,
          },
        ],
      }
       
      },
  },


};
</script>
  