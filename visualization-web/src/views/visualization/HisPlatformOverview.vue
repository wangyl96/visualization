<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <page-header-wrapper :title="false">
    <div class="vis-container">
      <!--   概览   -->
      <a-spin :spinning="overviewSpin">
        <a-card :bordered="false">
          <!--  数据总览  -->
          <div class="account-center-detail" style="padding-left: 20px">
            <p>
              <i class="title"></i><span style="margin-left: 2px;">数据总览</span>
            </p>
          </div>
        </a-card>
        <!--分割线-->
        <!--          <a-divider style="background: #F0F2F5;" />-->
        <!--  各平台卡片  -->
        <a-row :gutter="10">
          <a-spin :spinning="platFormSpin">
          <div v-for="item in this.platFormCardView.yearData" :key="item.custNmae">
            <platform-card-view ref="platformCardView" :platformCardViewInfo="item"></platform-card-view>
          </div>
          </a-spin>
        </a-row>
      </a-spin>
      <!--   数据统计   -->
      <a-spin :spinning="false" style="margin-bottom: 20px">
        <a-card :bordered="false" style="margin-top: 20px; margin-bottom: 10px">
          <div class="account-center-detail" style="padding-left: 20px">
            <p>
              <i class="title"></i><span style="margin-left: 2px">数据统计</span>
            </p>
          </div>
        </a-card>
        <a-row :gutter="10">
          <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
            <a-card :bordered="false">
              <div id="areaView1" style="width: 100%; height: 320px; margin-top: 12px; padding-bottom: 28px"></div>
            </a-card>
          </a-col>
          <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
            <a-card :bordered="false">
            <div id="areaView2" style="width: 100%; height: 320px; margin-top: 12px; padding-bottom: 28px"></div>
            </a-card>
          </a-col>
          <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
            <a-card :bordered="false">
              <div id="areaView3" style="width: 100%; height: 320px; margin-top: 12px; padding-bottom: 28px"></div>
            </a-card>
          </a-col>
        </a-row>
      </a-spin>
      <!--   地图   -->
      <a-spin :spinning="mapSpin">
        <a-card :bordered="false" :body-style="{padding: '0'}">
          <a-tabs default-active-key="app" size="large" @change="callback" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">
            <a-tab-pane v-for="(item) in tabData" :key="item.platform.code" :tab="item.platform.name" :disabled="item.platform.code !== 'app'"></a-tab-pane>
            <div class="extra-wrapper" slot="tabBarExtraContent">
              <div class="extra-item">
                <a-radio-group :value="quotaChecked" style="margin-right: 20px" @change="handleSizeChange" >
                  <a-radio-button v-for="item in quotaCheckedList" :key="item.code" :value="item.code" :disabled="item.code == 'life_day'">
                    {{item.name}}
                  </a-radio-button>
                </a-radio-group>
              </div>
            </div>
          </a-tabs>
          <a-row>
            <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
              <china-map ref="chinaMap" :china="mapData"></china-map>
            </a-col>
            <a-col :xl="6" :lg="12" :md="12" :sm="24" :xs="24">
              <rank-list v-if="rankList.length > 0" :title="title" :list="rankList"/>
            </a-col>
            <a-col :xl="2" :lg="12" :md="12" :sm="24" :xs="24">
            </a-col>
          </a-row>
        </a-card>
      </a-spin>
      <!--   饼图   -->
      <a-spin :spinning="pieSpin">
        <a-card :bordered="false" style="margin-top: 20px;" >
          <div class="account-center-detail" style="padding-left: 20px">
            <p>
              <i class="title"></i>保费数据统计
            </p>
          </div>
        </a-card>
        <!--分割线-->
        <!--          <a-divider style="background: #F0F2F5;" />-->
        <a-row :gutter="10">
          <div v-for="(item) in pieDatas" :key="item.platForm" >
            <pie-view ref="pieView" :pieDataInfo="item"></pie-view>
          </div>
        </a-row>
      </a-spin>
    </div>
  </page-header-wrapper>
</template>

<script>
import PlatformOverview from '@/components/overview/index'
// import echarts from 'echarts'
import '../../../node_modules/echarts/map/js/china.js'
import {
  RankList
} from '@/components'
import { getTodayMapData, getPieView, getTodayOverview } from '@/api/business/visOverview'
import ChinaMap from '@/components/Charts/chinaMap'
import PieView from '@/components/overview/pieView'
import PlatformCardView from '@/components/overview/platformCardView'
import echarts from 'echarts'
import { getPlatformInstOrVis } from '@/api/business/hisPlatformOverview'
// import { beforeYesterday, yesterday } from '@/utils/dateUtil'
let areaView1 = null
let areaView2 = null
let areaView3 = null
export default {
  name: 'HistPlatformOverview',
  components: { PlatformCardView, PieView, ChinaMap, PlatformOverview, RankList },
  data () {
    return {
      platform: [],
      visCardHeadStyle: { 'border-bottom': '0px', 'margin-bottom': '-20px', 'font-size': '18px', 'color': '#333333' },
      color: '',
      size: 'large',
      screenWidth: document.body.clientWidth / 3 + 'px',
      rankList: [],
      pieDatas: {},
      // 今日数据概览spin
      overviewSpin: true,
      // 地图标签页和按钮
      tabData: [
        {
          'platform': {
            'name': 'APP平台',
            'code': 'app',
            'quota': [{
              'name': '安装量',
              'code': 'app_installation'
            },
              {
                'name': '注册量',
                'code': 'registrations'
              },
              {
                'name': '日活',
                'code': 'life_day'
              }
            ]
          }
        },
        {
          'platform': {
            'name': 'PC平台',
            'code': 'pc',
            'quota': [{
              'name': '注册量',
              'code': 'registrations'
            },
              {
                'name': '访问量',
                'code': 'visits'
              },
              {
                'name': '访客量',
                'code': 'visitors'
              }
            ]
          }
        },
        {
          'platform': {
            'name': 'WAP平台',
            'code': 'wap',
            'quota': [{
              'name': '注册量',
              'code': 'registrations'
            },
              {
                'name': '访问量',
                'code': 'visits'
              },
              {
                'name': '访客量',
                'code': 'visitors'
              }
            ]
          }
        }
      ],
      mapData: {},
      // 选中的指标列表
      quotaCheckedList: [],
      // 选中的标签页(平台)
      platformChecked: '',
      // 选中的按钮(指标)
      quotaChecked: '',
      // 选中按钮名称
      quotaCheckedName: '',
      // 排名标题
      title: '',
      // 地图加载spin
      mapSpin: true,
      // 饼图加载spin
      pieSpin: true,
      // 数据展示日期
      // yesterday: yesterday,
      yesterday: '2020-10-09',
      // 环比日期
      beforeYesterday: '2020-10-08',
      platFormCardView: {},
      platForm: this.$route.params.platForm,
      platFormSpin: true
    }
  },
  mounted () {
    // 数据总览
    this.getTodayOverview()
    // 数据统计面积图
    this.drawAreaView()
    // 地图
    this.drawLine()
    // 饼图
    this.getPieView()
    this.getPlatformInstOrVis()
  },
  methods: {
    /**
     * 标签页点击函数
     * @param key, 选中的标签标志
     */
    callback (key) {
      this.mapSpin = true
      this.tabData.forEach(item => {
        if (item.platform.code === key) {
          // 根据平台code和指标code获取地图数据
          const quotaCode = item.platform.quota[0].code
          this.quotaCheckedList = item.platform.quota
          this.platformChecked = key
          this.quotaChecked = quotaCode
          this.quotaCheckedName = item.platform.quota[0].name
          this.title = '本年度' + this.quotaCheckedName + '排名'
          const query = {
            'platformCode': this.platformChecked,
            'quotaCode': this.quotaChecked,
            'quotaName': this.quotaCheckedName,
            'queryDate': this.yesterday
          }
          // 再根据平台编码和指标编码获取地图数据
          this.getTodayMapData(query)
          return false
        }
      })
    },
    /**
     * 按钮点击函数
     * @param e, 选中的按钮
     */
    handleSizeChange (e) {
      this.mapSpin = true
      this.quotaChecked = e.target.value
      this.quotaCheckedList.forEach(item => {
        if (item.code === e.target.value) {
          this.quotaCheckedName = item.name
          return false
        }
      })
      this.title = '本年度' + this.quotaCheckedName + '排名'
      const query = {
        'platformCode': this.platformChecked,
        'quotaCode': this.quotaChecked,
        'quotaName': this.quotaCheckedName,
        'queryDate': this.yesterday
      }
      // 再根据平台编码和指标编码获取地图数据
      this.getTodayMapData(query)
    },
    /**
     * 获取今日概览数据函数
     */
    getTodayOverview () {
      const query = {
        'queryDate': this.yesterday,
        'ratioDate': this.beforeYesterday
      }
      getTodayOverview(query).then(response => {
        const result = response.data
        this.platform = result
        this.overviewSpin = false
      })
    },
    /**
     * 获取数据统计面积图
     */
    drawAreaView () {
      areaView1 = echarts.init(document.getElementById('areaView1'))
      areaView2 = echarts.init(document.getElementById('areaView2'))
      areaView3 = echarts.init(document.getElementById('areaView3'))
      const option = {
        title: {
          text: '近七日数据(单位: 个)',
          left: 20,
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16,
            color: '#333',
            fontFamily: 'MicrosoftYaHei'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          icon: 'rect',
          itemWidth: 4, // 设置宽度
          itemHeight: 14, // 设置高度
          itemGap: 16,
          x: '20',
          y: '30',
          data: ['安装量', '注册量', '活跃度'],
          textStyle: {
            fontSize: 14,
            color: '#333',
            fontFamily: 'MicrosoftYaHei'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          y: 70,
          containLabel: true
        },
        xAxis:
          {
            type: 'category',
            boundaryGap: false,
            axisTick: {
              show: false
            },
            // 坐标的字体颜色
            axisLabel: {
              textStyle: {
                fontSize: 14,
                color: '#333',
                fontFamily: 'MicrosoftYaHei'
              }
            },
            // Y轴颜色
            axisLine: {
              lineStyle: {
                color: '#ECECEC',
                width: 1
              }
            },
            data: ['10-03', '10-04', '10-05', '10-06', '10-07', '10-08', '10-09']
          },
        yAxis:
          {
            type: 'value',
            // Y轴刻度线
            axisTick: {
              show: false
            },
            // 分割线颜色
            splitLine: {
              lineStyle: {
                color: '#ECECEC'
              }
            },
            // 坐标的字体颜色
            axisLabel: {
              textStyle: {
                fontSize: 14,
                color: '#333',
                fontFamily: 'MicrosoftYaHei'
              }
            },
            // Y轴颜色
            axisLine: {
              lineStyle: {
                color: '#ECECEC',
                width: 1
              }
            }
          },
        series: [
          {
            name: '安装量',
            type: 'line',
            stack: '总量',
            itemStyle: {
              normal: {
                color: '#F3470D', // 折线条的颜色
                borderColor: '#F3470D', // 拐点边框颜色
                areaStyle: {
                  type: 'default',
                  opacity: 0.1
                }
              }
            },
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '注册量',
            type: 'line',
            stack: '总量',
            itemStyle: {
              normal: {
                color: '#22BAED',
                borderColor: '#22BAED',
                areaStyle: {
                  type: 'default',
                  opacity: 0.1
                }
              }
            },
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '活跃度',
            type: 'line',
            stack: '总量',
            itemStyle: {
              normal: {
                color: '#05B54F',
                borderColor: '#05B54F',
                areaStyle: {
                  type: 'default',
                  opacity: 0.1
                }
              }
            },
            data: [150, 232, 201, 154, 190, 330, 410]
          }
        ]
      }
      areaView1.setOption(option)
      areaView2.setOption(option)
      areaView3.setOption(option)
      setTimeout(() => {
          areaView1.resize()
          areaView2.resize()
          areaView3.resize()
      }, 100)
      this.pieSpin = false
      window.addEventListener('resize', () => {
        areaView1.resize()
        areaView2.resize()
        areaView3.resize()
      }, false)
    },
    /**
     * 绘制地图
     */
    drawLine () {
      // 先调用后台获取tab页数据
      const platform = JSON.parse(JSON.stringify(this.tabData[0]))
      this.quotaCheckedList = platform.platform.quota
      this.quotaChecked = platform.platform.quota[0].code
      this.quotaCheckedName = platform.platform.quota[0].name
      this.platformChecked = platform.platform.code
      this.title = '本年度' + this.quotaCheckedName + '排名'
      const query = {
        'platformCode': this.platformChecked,
        'quotaCode': this.quotaChecked,
        'quotaName': this.quotaCheckedName,
        'queryDate': this.yesterday
      }
      // 再根据平台编码和指标编码获取地图数据
      this.getTodayMapData(query)
    },
    /**
     * 调用后台获取地图数据
     * @param query
     */
    getTodayMapData (query) {
      getTodayMapData(query).then(response => {
        const result = response.data
        this.mapData = result
        this.rankList = result.map.slice(0, 10)
        this.mapSpin = false
      })
    },
    /**
     * 获取饼图所需数据
     */
    getPieView () {
      const query = {
        'queryDate': this.yesterday,
        'ratioDate': this.beforeYesterday
      }
      getPieView(query).then(response => {
        this.pieDatas = JSON.parse(JSON.stringify(response.data))
        this.pieSpin = false
      })
    },
    getPlatformInstOrVis () {
      const query = {
        'platForm': this.platForm
      }
      getPlatformInstOrVis(query).then(response => {
        this.platFormCardView = JSON.parse(JSON.stringify(response.data))
        this.platFormSpin = false
      })
    }
  },
  beforeDestroy () {
    areaView1.clear()
    areaView2.clear()
    areaView3.clear()
  }
}
</script>
<style lang="less" >

.ant-card-body {
  padding: 16px 0px 0px 0px;
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
  font-size: 16px;
  font-family: MicrosoftYaHei;
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

.total-amount-font{
  width: 94px;
  height: 24px;
  font-size: 16px;
  font-weight: bold;
  color: #333333;
  line-height: 24px;

}

.mom-font{
  width: 28px;
  height: 19px;
  font-size: 14px;
  color: #333333;
  line-height: 19px;
}

.mom-font-num{
  width: 52px;
  height: 19px;
  font-size: 14px;
  font-weight: bold;
  color: #3CB800;
  line-height: 19px;

}

.premium-data{
  width: 73px;
  height: 15px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 17px;
  position: absolute;
  left: 105px;
  /* top: 46px; */
  margin-top: 3px;

}

.per-data{
  width: 36px;
  height: 17px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 17px;
}

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

.ant-layout-footer {
  padding: 0 50px 0 50px;
}

</style>
