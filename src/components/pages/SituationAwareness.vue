<template>
  <div class="flex flex-col">
    <div class="text-center">
      <h4>安全态势感知总览</h4>
    </div>
    <div class="flex flex-rol flex-wrap">
      <div id="chart1" style="width: 300px; height: 350px"></div>
      <div id="chart2" style="width: 550px; height: 350px"></div>
      <div id="chart3" style="width: 400px; height: 400px"></div>
      <div id="chart4" style="width: 330px; height: 350px"></div>
      <div id="chart5" style="width: 550px; height: 350px"></div>
      <div id="chart6" style="width: 400px; height: 400px"></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
import instance from "~/services/api";
onMounted(() => {
  instance({
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    method: "get",
    url: "/system/user/selectByPage/1/10000",
  }).then(() => {});
  drawChart();
});

const drawChart = () => {
  echarts.init(document.getElementById("chart1")).dispose();
  echarts.init(document.getElementById("chart2")).dispose();
  echarts.init(document.getElementById("chart3")).dispose();
  echarts.init(document.getElementById("chart4")).dispose();
  echarts.init(document.getElementById("chart5")).dispose();
  echarts.init(document.getElementById("chart6")).dispose();

  const myChart1 = echarts.init(document.getElementById("chart1"));
  const myChart2 = echarts.init(document.getElementById("chart2"));
  const myChart3 = echarts.init(document.getElementById("chart3"));
  const myChart4 = echarts.init(document.getElementById("chart4"));
  const myChart5 = echarts.init(document.getElementById("chart5"));
  const myChart6 = echarts.init(document.getElementById("chart6"));

  const option1 = {
    title: {
      text: "风险指数",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      orient: "vertical",
      left: "left",
    },
    series: [
      {
        name: "风险等级",
        type: "pie",
        radius: ["40%", "70%"], // 这里调整内半径和外半径制作环形图
        avoidLabelOverlap: false,
        label: {
          show: false,
          position: "center",
        },
        emphasis: {
          label: {
            show: true,
            fontSize: "30",
            fontWeight: "bold",
          },
        },
        labelLine: {
          show: false,
        },
        data: [
          { value: 107, name: "高危风险" },

          { value: 150, name: "中等风险" },

          { value: 300, name: "一般风险" },
        ],
      },
    ],
  };
  myChart1.setOption(option1);
  const option2 = {
    title: {
      text: "危险事件发展态势",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["一般风险", "中等风险", "高危风险"],
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    toolbox: {
      feature: {
        saveAsImage: {},
      },
    },
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: [
        "1月19日",
        "1月20日",
        "1月21日",
        "1月22日",
        "1月23日",
        "1月24日",
        "1月25日",
      ],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "一般风险",
        type: "line",
        stack: "Total",
        data: [100, 140, 230, 100, 130, 174, 121],
      },
      {
        name: "中等风险",
        type: "line",
        stack: "Total",
        data: [150, 100, 200, 140, 100, 211, 187],
      },
      {
        name: "高危风险",
        type: "line",
        stack: "Total",
        data: [194, 186, 120, 228, 133, 193, 105],
      },
    ],
  };

  myChart2.setOption(option2);

  const option3 = {
    title: {
      text: "漏洞挖掘态势",
    },
    xAxis: {
      type: "category",
      data: ["1/16", "1/17", "1/18", "1/19", "1/20", "1/21", "1/22"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: [100, 140, 230, 100, 130, 194, 158],
        type: "bar",
        itemStyle: {
          color: "blue",
        },
      },
    ],
  };
  myChart3.setOption(option3);

  const option4 = {
    title: {
      text: "TOP5潜在攻击类型",
    },
    xAxis: {
      type: "value",
    },
    yAxis: {
      type: "category",
      data: [
        "设计错误",
        "输入验证错误",
        "边界条件错误",
        "访问验证错误",
        "竞争条件错误",
      ],
    },
    grid: {
      x: 100,
      y: 25,
      x2: 30,
      y2: 35,
    },
    series: [
      {
        data: [50, 80, 100, 140, 230],
        type: "bar",
        itemStyle: {
          color: "blue",
        },
      },
    ],
  };

  myChart4.setOption(option4);
  const option5 = {
    title: {
      text: "安全事件防御态势",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["一般风险", "中等风险", "高危风险"],
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    toolbox: {
      feature: {
        saveAsImage: {},
      },
    },
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: [
        "1月19日",
        "1月20日",
        "1月21日",
        "1月22日",
        "1月23日",
        "1月24日",
        "1月25日",
      ],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "一般风险",
        type: "line",
        stack: "Total",
        data: [100, 140, 230, 100, 130, 174, 121],
      },
      {
        name: "中等风险",
        type: "line",
        stack: "Total",
        data: [150, 100, 200, 140, 100, 211, 187],
      },
      {
        name: "高危风险",
        type: "line",
        stack: "Total",
        data: [194, 186, 120, 228, 133, 193, 105],
      },
    ],
  };
  myChart5.setOption(option5);

  const option6 = {
    title: {
      text: "TOP5防御情况",
    },
    xAxis: {
      type: "value",
    },
    yAxis: {
      type: "category",
      data: ["镜像1", "镜像2", "镜像3", "镜像4", "镜像5"],
    },
    series: [
      {
        data: [30, 45, 60, 80, 160],
        type: "bar",
        itemStyle: {
          color: "green",
        },
      },
    ],
  };

  myChart6.setOption(option6);
  window.onresize = function () {
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart4.resize();
    myChart5.resize();
    myChart6.resize();

    // 重复这行代码以适应其他图表
  };
};
</script>
