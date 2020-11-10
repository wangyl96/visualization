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
        const myChartChina = echarts.init(myChartContainer)
        // 指定高亮颜色
        let mapColor
        const that = this
        // 绘制图表
        optionMap = {
          title: {
            text: '分省平台和指标数据趋势情况',
            left: 20,
            textStyle: {
              fontWeight: 'normal',
              fontSize: '16',
              color: '#333',
              fontFamily: 'MicrosoftYaHei'
            }
          },
          tooltip: {
            trigger: 'item',
            backgroundColor: 'none',
            formatter: function (params) {
              const value = params.value.toString() === 'NaN' ? 0 : params.value
              const str = '<div class="tooltip"> <div class="tooltip-title note-circle-blue">' + params.name + '</div> <div class="tooltip-content"> <span class="tooltip-title-left">' + that.chinaInfo.quota.name + '</span> <sapn class="tooltip-title-right"> ' + value + ' </sapn> </div> </div>'
              return str
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
                  show: true,
                  fontSize: '14',
                  fontFamily: 'MicrosoftYaHei'
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
<style>
.tooltip {
  border-radius: 4px;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.5);
  background: #fff;

}

.tooltip-title {
  padding: 0 10px;
  width: 128px;
  height: 30px;
  line-height: 30px;
  border-bottom: 1px solid #ebf0f5;
  color: #333;
}

.tooltip-content {
  padding: 5px 10px;
  height: 32px;
}

.tooltip-title-right {
  float: right;
  font-size: 16px;
  color: #30a8e7;
}

.tooltip-title-left {

  color: #333;
}
</style>
