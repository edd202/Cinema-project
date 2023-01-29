<template>
  <div>
   
    <Bar
      id="my-chart-id"
      :legend="legend"
      :options="chartOptions"
      :data="chartData"
      v-if="loaded"
      class="bar-chart"
    />
  </div>
</template>
  
  <script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import MasterService from "@/services/MasterService";
ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);
ChartJS.defaults.color = 'white';
ChartJS.defaults.borderColor = 'white';
export default {
  name: "BarChart",
  components: { Bar },

  data() {
    return {
      loaded: false,
      kimHaeData: new Array(0),
      HeadData: new Array(0),
      MgData: new Array(0),
      CentumData: new Array(0),
      chartOptions: {
        responsive: true,
        Tooltip:{
          bodyFontColor:"white"
        }
      
      },
      legend: {
        labels:{fontColor: "red",
					fontSize: 18},
        
       },
    };
  },
  computed: {
    chartData() {
      return {
        labels: [
          "1월",
          "2월",
          "3월",
          "4월",
          "5월",
          "6월",
          "7월",
          "8월",
          "9월",
          "10월",
          "11월",
          "12월",
        ],
        datasets: [
          {
            label: "부산본점",
            backgroundColor: ["#41B883"],
            data: this.HeadData,
          },
          {
            label: "부산 명지",
            backgroundColor: ["#FF61D0"],
            data: this.MgData,
          },
          {
            label: "센텀시티",
            backgroundColor: ["#DABAFF"],
            data: this.CentumData,
          },
          {
            label: "김해장유",
            backgroundColor: ["#ffd966"],
            data: this.kimHaeData,
          },
        ],
      };
    },
  },
  mounted() {
    MasterService.getCinemaSales()
      .then((response) => {
        console.log(response.data);
        console.log(response.data.length);
        console.log(response.data[0].sales);
        // let arr=new Array(12);
        for (var i = 0; i < response.data.length; i++) {
          if (response.data[i].cinema.includes("장유")) {
            this.kimHaeData.push(response.data[i].sales);
          }
          if (response.data[i].cinema.includes("본점")) {
            this.HeadData.push(response.data[i].sales);
          } 
          if (response.data[i].cinema.includes("명지")) {
            this.MgData.push(response.data[i].sales) 
          }
          if (response.data[i].cinema.includes("센텀")) {
            this.CentumData.push(response.data[i].sales) ;
          }
        }
        console.log(this.kimHaeData);
        console.log("본점은"+this.HeadData);
        console.log(this.MgData);
        console.log(this.CentumData);
        this.loaded = true;
      })
      .catch((e) => {
        console.log(e);
      });
  },
};
</script>

<style>
.bar-chart{

}
</style>