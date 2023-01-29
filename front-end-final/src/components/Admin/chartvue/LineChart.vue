<template>
  <div>
    <LineChartGenerator :data="chartData" :options="options" v-if="loaded" :height="300" />
  </div>
</template>

<script lang="ts">
/* eslint-disable */
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Line as LineChartGenerator } from "vue-chartjs";
import MasterService from "@/services/MasterService";
ChartJS.defaults.color = 'white';
ChartJS.defaults.borderColor = 'white';
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

export default {
  name: "LineChart",
  components: {
    LineChartGenerator,
  },
  data() {
    return {
      loaded: false,
      monthVistor: new Array(),
      options: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  mounted() {
    MasterService.getMonthVistor()
      .then((response) => {
        console.log("월별방문객은" + response.data[0].vistorCount);
        // let arr=new Array(12);
        for (var i = 0; i < response.data.length; i++) {
          this.monthVistor.push(response.data[i].vistorCount);
        }

        this.loaded = true;
      })
      .catch((e) => {
        console.log(e);
      });
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
            label: "예매 횟수",
            backgroundColor: "#f87979",
            data: this.monthVistor,
          },
        ],
      };
    },
  },
};
</script>