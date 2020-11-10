<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <!--  保费数据统计饼图  -->
  <div class="header-info">
    <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24" >
      <a-card :bordered="false" :style="{ marginBottom: '20px' }">
        <div :id="this.pieData.platForm" :style="{height: '240px'}"></div>
        <div style="position: absolute; margin-top: -220px; margin-left: 36.6%">
          <ul style=" margin-top: 5px; color: #333333">
            <li class= "total-amount-font">¥{{pieData.barViewMap[0].realSum}}</li>
            <span class="mom-font" >环比
                <span v-if="((pieData.mom)*100) < 0" >
                  <img src="../../../public/static/icon/drop.png" style="margin-top: -3px"/>
                   <span style="color: #3CB800FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom)*100)).toFixed(2)}}%</span>
                </span>
                <span v-else>
                  <img src="../../../public/static/icon/up.png" style="margin-top: -3px"/>
                  <span style="color: #F44242FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom)*100)).toFixed(2)}}%</span></span>
                </span>
          </ul>
        </div>
      </a-card>
    </a-col>
  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'PieView',
  props: {
    pieDataInfo: {
      type: Object,
      default: function () {
        return {}
      }
    },
    pieView: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      pieData: this.pieDataInfo
    }
  },
  watch: {
    pieDataInfo: {
      deep: true,
      handler (newVal, oldVal) {
        this.pieData = JSON.parse(JSON.stringify(newVal))
        this.drawPie(newVal)
      }
    }
  },
  mounted () {
    // 饼图
    this.drawPie()
  },
  methods: {
    /**
     * 判读正负，正则返回原数 负数返回0
     * @param e
     * @returns {number|*}
     * @constructor
     */
    JudgePositiveNegative (e) {
      if (e >= 0) {
        return e
      } else {
        return 0
      }
    },
    drawPie () {
      this.$nextTick(() => {
        // 初始化echarts实例
        const myPie = echarts.init(document.getElementById(this.pieData.platForm))
        const that = this
        // 指定图标的配置和数据
        var option = {
          title: {
            text: this.pieData.platForm,
            left: 20,
            top: 28,
            button: 100
          },
          tooltip: {
            // 悬浮框提示相关
            trigger: 'item',
            backgroundColor: 'none',
            formatter: function (params) {
              const str = '<div class="tooltip"><div class="tooltip-title note-circle-blue">' + params.seriesName + '</div> <div class="tooltip-content"> <span class="tooltip-title-left">' + params.name + '</span> <sapn class="tooltip-title-right">' + params.value + '</sapn> </div><div class="tooltip-content"> <span class="tooltip-title-left">比例</span> <sapn class="tooltip-title-right">' + params.percent + '%</sapn> </div> </div>'
              return str
            }
          },
          legend: {
            // legend 图例相关
            type: 'scroll',
            orient: 'vertical',
            x: '44%',
            y: '80',
            itemWidth: 8,
            itemHeight: 8,
            data: this.pieData.legend,
            textStyle: {
              rich: {
                a: {
                  width: 24,
                  color: '#333',
                  fontSize: 12,
                  fontWeight: 400,
                  fontFamily: 'apple-system BlinkMacSystemFont'
                },
                b: {
                  color: '#999',
                  width: 60,
                  fontSize: 12,
                  fontWeight: 400,
                  fontFamily: 'apple-system BlinkMacSystemFont'
                },
                c: {
                  color: '#333',
                  fontSize: 12,
                  fontWeight: 400,
                  fontFamily: 'apple-system BlinkMacSystemFont'
                }
              }
            },
            formatter: function (name) {
              const barViewMap = that.pieData.barViewMap[0]
              let ratio = ''
              let value = ''
              switch (name) {
                case '产':
                  ratio = that.JudgePositiveNegative(((barViewMap.product / barViewMap.falseSum) * 100).toFixed(2)) + '%'
                  value = '¥' + that.JudgePositiveNegative(barViewMap.product)
                  break

                case '寿':
                  ratio = that.JudgePositiveNegative(((barViewMap.life / barViewMap.falseSum) * 100).toFixed(2)) + '%'
                  value = '¥' + that.JudgePositiveNegative(barViewMap.life)
                  break

                case '健':
                  ratio = that.JudgePositiveNegative(((barViewMap.health / barViewMap.falseSum) * 100).toFixed(2)) + '%'
                  value = '¥' + that.JudgePositiveNegative(barViewMap.health)
                  break

                case '资':
                  ratio = that.JudgePositiveNegative(((barViewMap.wealth / barViewMap.falseSum) * 100).toFixed(2)) + '%'
                  value = '¥' + that.JudgePositiveNegative(barViewMap.wealth)
                  break

                case '金服':
                  ratio = that.JudgePositiveNegative(((barViewMap.gold / barViewMap.falseSum) * 100).toFixed(2)) + '%'
                  value = '¥' + that.JudgePositiveNegative(barViewMap.gold)
                  break
              }
              var arr = [
                '{a|' + name + '}' +
                '{b|' + '|  ' + ratio + '}' +
                '{c|' + value + '}'
              ]
              return arr
            }
          },
          series: {
            name: this.pieData.platForm + '平台',
            type: 'pie',
            center: ['22%', '60%'],
            radius: '56%',
            selectedMode: true, // 是否支持多选，默认为false,鼠标点击后选中饼图分裂出来
            data: [
              { name: '产', value: this.JudgePositiveNegative(this.pieData.barViewMap[0].product) },
              { name: '寿', value: this.JudgePositiveNegative(this.pieData.barViewMap[0].life) },
              { name: '健', value: this.JudgePositiveNegative(this.pieData.barViewMap[0].health) },
              { name: '资', value: this.JudgePositiveNegative(this.pieData.barViewMap[0].wealth) },
              { name: '金服', value: this.JudgePositiveNegative(this.pieData.barViewMap[0].gold) }
            ],
            label: {
              normal: {
                position: 'inner',
                show: false
              }
            },
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              },
              normal: {
                color: function (params) {
                  // 自定义颜色
                  var colorList = [
                    '#5680BB', '#7CBC5C', '#DDCB67', '#C6736C', '#9CAAB3'
                  ]
                  return colorList[params.dataIndex]
                }
              }
            }
          }
        }
        myPie.setOption(option)
        this.pieSpin = false
        window.addEventListener('resize', () => {
          myPie.resize()
        }, false)
      })
    }
  }
}
</script>
<style lang="less">
@import "~ant-design-vue/es/style/themes/default.less";

.header-info {
  position: relative;
  margin-top: 10px;
  & > em {
    position: absolute;
    top: 0;
    right: 0;
    width: 1px;
    height: 56px;
    background-color: @border-color-split;
  }

  tbody tr:hover>td {
    background-color: transparent !important
  }

  .ant-table-tbody > tr > td{
    border-bottom: none;
  }

  .ant-table-thead > tr > th, .ant-table-tbody > tr > td {
    padding: 5px 16px 5px 16px;
  }

  .ant-card-head-title {
    font-size: 18px;
    color: #333;
  }

}
</style>
