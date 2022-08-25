<template>
    <div style="width:200px;height:200px;">
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
  </div>

  <div class="row" style="margin-top: 30px;">
          <div class="col-4">
            <div class="card">
              <div class="card-body">
                <h4 class="card-title">Select start and end date</h4>
                <Datepicker v-model="createNewTerm.date" range></Datepicker>
                <select class="form-select" v-model="createNewTerm.type" style="margin-top: 30px;">
                  <option value="AVAILABILE">AVAILABILE</option>
                  <option value="UNAVAILABLE">UNAVAILABLE</option>
                </select> <br>

                <p class="card-text text-danger">{{createNewTerm.msg}}</p>
                <button class="btn btn-danger m-1" v-on:click="createNewTerm.formVisible=false">Cancel</button>
                <button :disabled="!createNewTerm.valid" class="btn btn-success m-1" v-on:click="addNewTerm()">Add</button>
              </div>
            </div>
          </div>
  </div>


</template>

<script>
import { Bar,Line } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
import {
  LineElement,
  PointElement,
  Plugin
} from 'chart.js'

import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)


export default {
  name: 'BoatBusinessReport',
  components: {
      Bar,Line
  },
  props: {
    chartId: {
      type: String,
      default: 'bar-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 200
    },
    height: {
      type: Number,
      default: 200
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Object,
      default: () => {}
    }
  },
  data: function(){
    return {
       chartData: {
        labels: [ 'January', 'February', 'March' ],
        // datasets: [ { data: [40, 20, 12] } ],
        datasets: [
        {
          label: 'Number of reservations',
          backgroundColor: '#f87979',
          data: [40, 39, 10]
        }
      ]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  mounted: function(){
  },
  methods: {
    
  }
  
}
</script>