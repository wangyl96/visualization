<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <page-header-wrapper :title=false>
    <div class="vis-container">
      <a-card :bordered="false">
        <!--  数据总览  -->
        <div class="account-center-detail" style="padding-left: 20px">
          <p>
            <i class="title"></i>数据总览 2300-09-20
          </p>
        </div>
        <!--分割线-->
        <a-divider style="background: #F0F2F5;" />
        <!--  各平台卡片  -->
        <a-row>
          <div v-for="(item) in platform" :key="item.platformName">
            <platform-overview ref="platformOverview" :platform="item"></platform-overview>
          </div>
        </a-row>
      </a-card>

      <a-card style="margin-top: 20px" :bordered="false" :body-style="{padding: '0'}">
        <div class="salesCard">
          <a-tabs default-active-key="1" size="large" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">
            <div class="extra-wrapper" slot="tabBarExtraContent">
              <div class="extra-item">
                <a-radio-group :value="size" style="margin-right: 20px" @change="handleSizeChange">
                  <a-radio-button value="large">
                    安装量
                  </a-radio-button>
                  <a-radio-button value="default">
                    注册量
                  </a-radio-button>
                  <a-radio-button value="small">
                    日活
                  </a-radio-button>
                </a-radio-group>
              </div>
            </div>
            <a-tab-pane tab="APP平台" key="1">
              <a-row>
                <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                  <div id="myChartChina" :style="{height: '610px'}"></div>
                </a-col>
                <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                  <rank-list title="本年度注册量排名" :list="rankList"/>
                </a-col>
              </a-row>
            </a-tab-pane>
            <a-tab-pane tab="PC平台" key="2">
              <a-row>
                <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                  <div id="myChartChina2" :style="{width: '100%', height: '610px',}"></div>
                </a-col>
                <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                  <rank-list title="本年度注册量排名" :list="rankList"/>
                </a-col>
              </a-row>
            </a-tab-pane>
            <a-tab-pane tab="WAP平台" key="3">
              <a-row>
                <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                  <div id="myChartChina3" :style="{width: '100%', height: '610px',}"></div>
                </a-col>
                <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                  <rank-list title="本年度注册量排名" :list="rankList"/>
                </a-col>
              </a-row>
            </a-tab-pane>
          </a-tabs>
        </div>
      </a-card>

      <a-card :bordered="false" style="margin-top: 20px;" >
        <div class="account-center-detail" style="padding-left: 20px">
          <p>
            <i class="title"></i>保费数据统计
          </p>
        </div>
        <!--  保费数据统计饼图  -->
        <a-row>
          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie" :style="{width: '300px', height: '200px'}"></div>
          </a-col>
          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">
              <ul style="margin-left: -42px; margin-top: 5px; color: #333333">
                <li style="font-size: 14px">¥792148.18</li>
                <li style="font-size: 12px">环比<img src="../../../public/static/icon/up.png" style="margin-top: -3px" />30%</li>
              </ul>
              <ul style="margin-top: -8px">
              <li v-for="item in 5" :key="item">
              <a-divider type="vertical" />
              <span>66.6{{ item }}%</span>
              <span>¥582501.18</span>
              </li>
              </ul>
          </a-col>

          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie2" :style="{width: '300px', height: '200px'}"></div>
          </a-col>
          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">
              <ul style="margin-left: -42px; margin-top: 5px;color: #333333">
                <li style="font-size: 14px">¥792148.18</li>
                <li style="font-size: 12px">环比<img src="../../../public/static/icon/up.png" style="margin-top: -3px" />30%</li>
              </ul>
              <ul style="margin-top: -8px">
                <li v-for="item in 5" :key="item">
                  <a-divider type="vertical" />
                  <span>66.6{{ item }}%</span>
                  <span>¥582501.18</span>
                </li>
              </ul>
          </a-col>

          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie3" :style="{width: '300px', height: '200px'}"></div>
          </a-col>
          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">
              <ul style="margin-left: -42px; margin-top: 5px; color: #333333">
                <li style="font-size: 14px">¥792148.18</li>
                <li style="font-size: 12px">环比<img src="../../../public/static/icon/up.png" style="margin-top: -3px" />30%</li>
              </ul>
              <ul style="margin-top: -8px">
                <li v-for="item in 5" :key="item">
                  <a-divider type="vertical" />
                  <span>66.6{{ item }}%</span>
                  <span>¥582501.18</span>
                </li>
              </ul>
          </a-col>

        </a-row>
      </a-card>
    </div>
  </page-header-wrapper>
</template>

<script>
import PlatformOverview from '@/components/overview/index'
import echarts from 'echarts'
import '../../../node_modules/echarts/map/js/china.js'
import {
  RankList
} from '@/components'
const rankList = []
for (let i = 0; i < 10; i++) {
  rankList.push({
    name: '北京',
    total: 1234.56
  })
}

export default {
  name: 'Overview',
  components: { PlatformOverview, RankList },
  data () {
    return {
      platform: [
        {
          'platformName': 'APP',
          'isEnd': false,
          'platformContent': [{
            'contentName': '注册量',
            'contentData': '12,3120',
            'ratio': '80%',
            upOrDown: 'up'
          },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'up'
            },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'up'
            }
          ]
        },
        {
          'platformName': 'PC',
          'isEnd': true,
          'platformContent': [{
            'contentName': '注册量',
            'contentData': '12312',
            'ratio': '80%',
            upOrDown: 'up'
          },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'down'
            },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'up'
            }
          ]
        },
        {
          'platformName': 'WAP',
          'isEnd': true,
          'platformContent': [{
            'contentName': '注册量',
            'contentData': '12312',
            'ratio': '80%',
            upOrDown: 'down'
          },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'up'
            },
            {
              'contentName': '注册量',
              'contentData': '12312',
              'ratio': '80%',
              upOrDown: 'up'
            }
          ]
        }
      ],
      visCardHeadStyle: { 'border-bottom': '0px', 'margin-bottom': '-20px', 'font-size': '18px', 'color': '#333333' },
      color: '',
      size: 'large',
      screenWidth: document.body.clientWidth / 3 + 'px',
      rankList: rankList
    }
  },
  mounted () {
    this.drawLine()
    this.drawPie()
  },

  methods: {
    callback (key) {
      console.log(key)
    },
    handleSizeChange (e) {
      this.size = e.target.value
    },
    drawLine () {
      // 基于准备好的dom，初始化echarts实例
      const myChartContainer = document.getElementById('myChartChina')
      const resizeMyChartContainer = function () {
        console.log(((document.body.offsetWidth - 250) / 3) * 2 + 'px')
        // myChartContainer.style.width = ((document.body.offsetWidth) / 3 * 2) + 'px'// 页面一半的大小
      }
      resizeMyChartContainer()
      const myChartChina = echarts.init(myChartContainer)

      function randomData () {
        return Math.round(Math.random() * 5000)
      }

      // 指定高亮颜色
      let mapColor
      // 绘制图表
      const optionMap = {
        tooltip: {
          trigger: 'item',
          formatter: function (params) {
            console.log(params)
            return params.name + '<br><b>安装量：' + params.value + '</b> '
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
          max: 5000,
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
            data: [
              { name: '北京', value: randomData() },
              { name: '天津', value: randomData() },
              { name: '上海', value: randomData() },
              { name: '重庆', value: randomData() },
              { name: '河北', value: randomData() },
              { name: '河南', value: randomData() },
              { name: '云南', value: randomData() },
              { name: '辽宁', value: randomData() },
              { name: '黑龙江', value: randomData() },
              { name: '湖南', value: randomData() },
              { name: '安徽', value: randomData() },
              { name: '山东', value: randomData() },
              { name: '新疆', value: randomData() },
              { name: '江苏', value: randomData() },
              { name: '浙江', value: randomData() },
              { name: '江西', value: randomData() },
              { name: '湖北', value: randomData() },
              { name: '广西', value: randomData() },
              { name: '甘肃', value: randomData() },
              { name: '山西', value: randomData() },
              { name: '内蒙古', value: randomData() },
              { name: '陕西', value: randomData() },
              { name: '吉林', value: randomData() },
              { name: '福建', value: randomData() },
              { name: '贵州', value: randomData() },
              { name: '广东', value: randomData() },
              { name: '青海', value: randomData() },
              { name: '西藏', value: randomData() },
              { name: '四川', value: randomData() },
              { name: '宁夏', value: randomData() },
              { name: '海南', value: randomData() },
              { name: '台湾', value: randomData() },
              { name: '香港', value: randomData() },
              { name: '澳门', value: randomData() }
            ]
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
    },
    drawPie () {
      // 初始化echarts实例
      const myPie = echarts.init(document.getElementById('myPie'))
      const myPie2 = echarts.init(document.getElementById('myPie2'))
      const myPie3 = echarts.init(document.getElementById('myPie3'))
      // 指定图标的配置和数据
      var option = {
        title: {
          text: 'APP',
          left: 20
        },
        tooltip: {
          // 悬浮框提示相关
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          // legend 图例相关
          type: 'scroll',
          orient: 'vertical',
          left: '60%',
          top: 50,
          itemWidth: 8,
          itemHeight: 8,
          data: [
            { name: '产', icon: 'circle' },
            { name: '寿', icon: 'circle' },
            { name: '健', icon: 'circle' },
            { name: '资', icon: 'circle' },
            { name: '金服', icon: 'circle' }
          ]
        },
        series: {
          name: 'APP平台',
          type: 'pie',
          center: ['35%', '50%'],
          selectedMode: true, // 是否支持多选，默认为false,鼠标点击后选中饼图分裂出来
          data: [
            { name: '产', value: 600 },
            { name: '寿', value: 310 },
            { name: '健', value: 200 },
            { name: '资', value: 800 },
            { name: '金服', value: 800 }
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
                  '#22BAED', '#FFAF30', '#1FC519', '#F362A5', '#B16DC2'
                ]
                return colorList[params.dataIndex]
              }
            }
          }
        }
      }
      // 使用制定的配置项和数据显示图表
      myPie.setOption(option)
      myPie2.setOption(option)
      myPie3.setOption(option)
      window.onresize = function () {
        // myPie.resize()
        // myPie2.resize()
        // myPie3.resize()
      }
    }
  }
}
</script>
<style lang="less" >

.ant-card-body {
  padding: 16px 0px 0px 0px;
}

.vis-div {
  // 取消div之间边距
  font-size: 0px;
}

.vis-tag {
  width: 4px;
  height: 18px;
  background-color: #1890FF;
}

.vis-main {
  // 上 右 下 左
  //padding: 0px 20px 0px 20px;
}

.vis-font {
  font-family: MicrosoftYaHei;
  font-size: 18px;
  line-height: 24px;
  color: #333333;
}

.ant-col-1 {
  width: 30px;
}

.ant-divider-horizontal {
  margin: 0;
}

.ant-tabs-nav-container {
  font-size: 18px;
}

.account-center-detail {
  font-family: MicrosoftYaHei;
  font-size: 18px;
  line-height: 24px;
  color: #333333;
  p {
    margin-bottom: 16px;
    padding-left: 8px;
    position: relative;
  }

  i {
    position: absolute;
    width: 4px;
    height: 18px;
    left: 0;
    top: 4px;
    background-color: #1890FF;
  }

  .title {
    background-position: 0 0;
  }
  .group {
    background-position: 0 -22px;
  }
  .address {
    background-position: 0 -44px;
  }
}

.bubble {
  width: 28px;
  height: 28px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  background-color: #1890FF;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;

}

.bubble-white {
  width: 28px;
  height: 28px;
  font-size: 16px;
  color: #999999;
  text-align: center;
  background-color: #ECECEC;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
.rank .list li span.active[data-v-7c181384] {
  background-color: #1890FF;
}

.antd-pro-pages-dashboard-analysis-twoColLayout {
  position: relative;
  display: flex;
  display: block;
  flex-flow: row wrap;
}

</style>
