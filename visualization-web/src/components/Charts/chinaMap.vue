<template>
  <div>
    <div id="myChartChina" :style="{height: '610px'}"></div>
  </div>
</template>

<script>
  import echarts from 'echarts'

  let optionMap
  export default {
    name: 'ChinaMap',
    props: {
      china: {
        type: Object,
        default: function () {
          return {
          }
        }
      },
      chinaMap: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
        chinaInfo: this.china
      }
    },
    watch: {
      china: {
        deep: true,
        handler (newVal, oldVal) {
          this.chinaInfo = JSON.parse(JSON.stringify(newVal))
          this.drawLine()
        }
      }
    },
    mounted () {
      this.drawLine()
    },
    methods: {
      drawLine () {
        // 基于准备好的dom，初始化echarts实例
        const myChartContainer = document.getElementById('myChartChina')
        const resizeMyChartContainer = function () {
          // myChartContainer.style.width = ((document.body.offsetWidth) / 3 * 2) + 'px'// 页面一半的大小
        }
        resizeMyChartContainer()
        const myChartChina = echarts.init(myChartContainer)
        // 指定高亮颜色
        let mapColor
        const that = this
        // 绘制图表
        optionMap = {
          tooltip: {
            trigger: 'item',
            formatter: function (params) {
              return params.name + '<br><b>' + that.chinaInfo.quota.name + '：' + params.value + '</b> '
            }
          },
          legend: {
            orient: 'horizontal',
            left: 'left',
            data: ['']
          },
          grid: {
            left: '8%',
            right: '0',
            bottom: '50%',
            containLabel: true
          },
          visualMap: {
            min: 0,
            max: this.chinaInfo.map[0].value,
            left: '10%',
            top: 'bottom',
            text: ['高', '低'],
            calculable: true,
            color: ['#336EBE', '#4BA0FB', '#82C7FB', '#33C6F7', '#6CD8FB', '#91E3FF']
          },
          selectedMode: 'single',
          series: [
            {
              name: '',
              type: 'map',
              // 控制缩放和移动
              roam: false,
              mapType: 'china',
              zoom: 1.25, // 地图大小
              itemStyle: {
                normal: {
                  // 分割线颜色
                  borderColor: '#fff'
                },
                emphasis: {
                  shadowOffsetX: 0,
                  shadowOffsetY: 0,
                  // 阴影深度
                  shadowBlur: 1,
                  // 分割线线宽
                  borderWidth: 2,
                  // 阴影颜色
                  shadowColor: '#fff',
                  // 高亮颜色
                  areaColor: mapColor
                }
              },
              // 各省市小红点
              showLegendSymbol: false,

              label: {
                normal: {
                  show: true
                  // 地图上的字体颜色
                  // color: '#fff'
                },
                emphasis: {
                  show: true
                }
              },
              data: this.chinaInfo.map
            }
          ]
        }

        myChartChina.setOption(optionMap)
        window.addEventListener('resize', () => {
          resizeMyChartContainer()
          myChartChina.resize()
        }, false)
        // 设置鼠标移入指定省份后保持高亮颜色与原色相同
        myChartChina.on('mouseover', function (params) {
          // 指定高亮颜色
          mapColor = '\'' + params.color + '\''
        })
      }
    }
  }
</script>
