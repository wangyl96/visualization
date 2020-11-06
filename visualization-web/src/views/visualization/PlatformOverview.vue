<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <page-header-wrapper :title="false">
    <div class="vis-container">
      <a-spin :spinning="overviewSpin" tip="加载中...">
        <a-card :bordered="false">
          <!--  数据总览  -->
          <div class="account-center-detail" style="padding-left: 20px">
            <p>
              <i class="title"></i>数据总览 2020-09-20
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
      </a-spin>
      <a-spin :spinning="mapSpin" tip="加载中...">
        <a-card style="margin-top: 20px" :bordered="false" :body-style="{padding: '0'}">
          <div class="salesCard">
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
              <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                <rank-list v-if="rankList.length > 0" :title="title" :list="rankList"/>
              </a-col>
            </a-row>
          </div>
        </a-card>
      </a-spin>
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
                <li  class= "total-amount-font">¥{{pieData.barViewMap[0].num}}</li>
                <span  class="mom-font" >环比
                  <span v-if="((pieData.mom[0].num-1)*100) < 0" >
                    <img src="../../../public/static/icon/drop.png" style="width: 7px;height: 14px;"/>
                     <span  style="color: #3CB800FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[0].num-1)*100)).toFixed(2)}}%</span>
                  </span>
                  <span v-else>
                    <img src="../../../public/static/icon/up.png" style="width: 7px;height: 14px;"/>
                    <span  style="color: #F44242FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[0].num-1)*100)).toFixed(2)}}%</span></span>
                  </span>
              </ul>
              <ul style="margin-top: -8px">
              <li >
              <a-divider type="vertical" />
              <span class="per-data" v-if="pieData.barViewMap[0].product/pieData.barViewMap[0].num>0">{{ ((pieData.barViewMap[0].product/pieData.barViewMap[0].num)*100).toFixed(2) }}%</span>
                <span v-else class="per-data">0%</span>
              <span class="premium-data">¥{{ pieData.barViewMap[0].product }}</span>
              </li>
                <li >
                  <a-divider type="vertical" />
                  <span class="per-data"v-if="pieData.barViewMap[0].life/pieData.barViewMap[0].num>0">{{ ((pieData.barViewMap[0].life/pieData.barViewMap[0].num)*100).toFixed(2) }}%</span>
                  <span class="per-data" v-else>0%</span>
                  <span class="premium-data">¥{{ pieData.barViewMap[0].life }}</span>
                </li>
                <li >
                  <a-divider type="vertical" />
                  <span class="per-data" v-if="pieData.barViewMap[0].health/pieData.barViewMap[0].num>0">{{ ((pieData.barViewMap[0].health/pieData.barViewMap[0].num)*100).toFixed(2) }}%</span>
                  <span class="per-data" v-else>0%</span>
                  <span class="premium-data">¥{{ pieData.barViewMap[0].health }}</span>
                </li>
                <li >
                  <a-divider type="vertical" />
                  <span class="per-data" v-if="pieData.barViewMap[0].wealth/pieData.barViewMap[0].num>0">{{ ((pieData.barViewMap[0].wealth/pieData.barViewMap[0].num)*100).toFixed(2) }}%</span>
                  <span class="per-data" v-else>0%</span>
                  <span class="premium-data">¥{{ pieData.barViewMap[0].wealth }}</span>
                </li>
                <li >
                  <a-divider type="vertical" />
                  <span class="per-data" v-if="pieData.barViewMap[0].gold/pieData.barViewMap[0].num>0">{{ ((pieData.barViewMap[0].gold/pieData.barViewMap[0].num)*100).toFixed(2) }}%</span>
                  <span  class="per-data" v-else>0%</span>
                  <span class="premium-data">¥{{ pieData.barViewMap[0].gold }}</span>
                </li>
              </ul>
          </a-col>

          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie2" :style="{width: '300px', height: '200px'}"></div>
          </a-col>
          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">
              <ul style="margin-left: -42px; margin-top: 5px;color: #333333">
                <li  class= "total-amount-font">¥{{pieData.barViewMap[1].num}}</li>
                <span  class="mom-font" >环比
                  <span v-if="((pieData.mom[1].num-1)*100) < 0" >
                    <img src="../../../public/static/icon/drop.png" style="width: 7px;height: 14px;"/>
                     <span  style="color: #3CB800FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[1].num-1)*100)).toFixed(2)}}%</span>
                  </span>
                  <span v-else>
                    <img src="../../../public/static/icon/up.png" style="width: 7px;height: 14px;"/>
                    <span  style="color: #F44242FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[1].num-1)*100)).toFixed(2)}}%</span></span>
                  </span>
              </ul>
            <ul style="margin-top: -8px">
              <li >
                <a-divider type="vertical" />
                <span class="per-data" v-if="pieData.barViewMap[1].product/pieData.barViewMap[1].num>0">{{ ((pieData.barViewMap[1].product/pieData.barViewMap[1].num)*100).toFixed(2) }}%</span>
                <span v-else class="per-data">0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[1].product }}</span>
              </li>
              <li >
                <a-divider type="vertical" />
                <span class="per-data"v-if="pieData.barViewMap[1].life/pieData.barViewMap[1].num>0">{{ ((pieData.barViewMap[1].life/pieData.barViewMap[1].num)*100).toFixed(2) }}%</span>
                <span class="per-data" v-else>0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[1].life }}</span>
              </li>
              <li >
                <a-divider type="vertical" />
                <span class="per-data" v-if="pieData.barViewMap[1].health/pieData.barViewMap[1].num>0">{{ ((pieData.barViewMap[0].health/pieData.barViewMap[1].num)*100).toFixed(2) }}%</span>
                <span class="per-data" v-else>0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[1].health }}</span>
              </li>
              <li >
                <a-divider type="vertical" />
                <span class="per-data" v-if="pieData.barViewMap[1].wealth/pieData.barViewMap[1].num>0">{{ ((pieData.barViewMap[0].wealth/pieData.barViewMap[1].num)*100).toFixed(2) }}%</span>
                <span class="per-data" v-else>0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[1].wealth }}</span>
              </li>
              </ul>
          </a-col>

          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie3" :style="{width: '300px', height: '200px'}"></div>
          </a-col>
          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">
              <ul style="margin-left: -42px; margin-top: 5px; color: #333333">
                <li  class= "total-amount-font">¥{{pieData.barViewMap[2].num}}</li>
                <span  class="mom-font" >环比
                  <span v-if="((pieData.mom[2].num-1)*100) < 0" >
                    <img src="../../../public/static/icon/drop.png" style="width: 7px;height: 14px;"/>
                     <span  style="color: #3CB800FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[2].num-1)*100)).toFixed(2)}}%</span>
                  </span>
                  <span v-else>
                    <img src="../../../public/static/icon/up.png" style="width: 7px;height: 14px;"/>
                    <span  style="color: #F44242FF;" class="mom-font-num">&nbsp{{ Math.abs(((pieData.mom[2].num-1)*100)).toFixed(2)}}%</span></span>
                  </span>
              </ul>
            <ul style="margin-top: -8px">
              <li >
                <a-divider type="vertical" />
                <span class="per-data" v-if="pieData.barViewMap[2].product/pieData.barViewMap[2].num>0">{{ ((pieData.barViewMap[2].product/pieData.barViewMap[2].num)*100).toFixed(2) }}%</span>
                <span v-else class="per-data">0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[2].product }}</span>
              </li>
              <li >
                <a-divider type="vertical" />
                <span class="per-data"v-if="pieData.barViewMap[2].life/pieData.barViewMap[2].num>0">{{ ((pieData.barViewMap[2].life/pieData.barViewMap[2].num)*100).toFixed(2) }}%</span>
                <span class="per-data" v-else>0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[2].life }}</span>
              </li>
              <li >
                <a-divider type="vertical" />
                <span class="per-data" v-if="pieData.barViewMap[2].health/pieData.barViewMap[2].num>0">{{ ((pieData.barViewMap[2].health/pieData.barViewMap[2].num)*100).toFixed(2) }}%</span>
                <span class="per-data" v-else>0%</span>
                <span class="premium-data">¥{{ pieData.barViewMap[2].health }}</span>
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
import { getTodayMapData, getPieView, getTodayOverview } from '@/api/business/visOverview'
import ChinaMap from '@/components/Charts/chinaMap'

export default {
  name: 'Overview',
  components: { ChinaMap, PlatformOverview, RankList },
  data () {
    return {
      platform: [],
      visCardHeadStyle: { 'border-bottom': '0px', 'margin-bottom': '-20px', 'font-size': '18px', 'color': '#333333' },
      color: '',
      size: 'large',
      screenWidth: document.body.clientWidth / 3 + 'px',
      rankList: [],
      pieData: {},
      // pieData: {
      //   'barViewMap': [{
      //     'wealth': '0',
      //     'life': -959,
      //     'product': 432296.23,
      //     'gold': '371000',
      //     'health': 622,
      //     'platForm': 'app',
      //     'num': 802959.23
      //   }, {
      //     'wealth': '0',
      //     'health': 0,
      //     'product': 181674.12,
      //     'gold': 'nan',
      //     'life': 9290,
      //     'platForm': 'pc',
      //     'num': 190964.12
      //   }, {
      //     'num': 274856.45,
      //     'health': 0,
      //     'platForm': 'wap',
      //     'product': 265490.45,
      //     'wealth': 'nan',
      //     'gold': 'nan',
      //     'life': 9366
      //   }],
      //   'mom': [{
      //     'num': 0.4569334074517331,
      //     'platform': 'app'
      //   }, {
      //     'num': 1.4438188820610052,
      //     'platform': 'pc'
      //   }, {
      //     'num': 1.1699560371080828,
      //     'platform': 'wap'
      //   }]
      // },
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
      mapSpin: true
    }
  },
  mounted () {
    // 数据总览
    this.getTodayOverview()
    // 地图
    this.drawLine()
    // 饼图
    this.getPieView()
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
            'quotaName': this.quotaCheckedName
          }
          // 再根据平台编码和指标编码获取地图数据
          this.getTodayMapData(query)
          return false
        }
      })
    },
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
        'quotaName': this.quotaCheckedName
      }
      // 再根据平台编码和指标编码获取地图数据
      this.getTodayMapData(query)
    },
    getTodayOverview () {
        getTodayOverview().then(response => {
          const result = response.data
          this.platform = result
          this.overviewSpin = false
        })
    },
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
        'quotaName': this.quotaCheckedName
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
    getPieView () {
      getPieView().then(response => {
        this.pieData = JSON.parse(JSON.stringify(response.data))
        this.drawPie()
      })
    },
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
        const myPie = echarts.init(document.getElementById('myPie'))
        const myPie2 = echarts.init(document.getElementById('myPie2'))
        const myPie3 = echarts.init(document.getElementById('myPie3'))
        // 指定图标的配置和数据
        var option = {
          title: {
            text: this.pieData.barViewMap[0].platForm,
            left: 20,
            button: 100
          },
          tooltip: {
            // 悬浮框提示相关
            trigger: 'item',
            backgroundColor: 'none',
            formatter: function (params) {
              const str = '<div class="tooltip"> <div class="tooltip-title note-circle-blue">' + params.seriesName + '</div> <div class="tooltip-content"> <span class="tooltip-title-left">' + params.name + '</span> <sapn class="tooltip-title-right">' + params.value + '</sapn> </div><div class="tooltip-content"> <span class="tooltip-title-left">比例</span> <sapn class="tooltip-title-right">' + params.percent + '%</sapn> </div> </div>'
              return str
            }
          },
          legend: {
            // legend 图例相关
            type: 'scroll',
            orient: 'vertical',
            left: '60%',
            top: 55,
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
            name: this.pieData.barViewMap[0].platForm + '平台',
            type: 'pie',
            center: ['35%', '50%'],
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

        var option1 = {
          title: {
            text: this.pieData.barViewMap[1].platForm,
            left: 20,
            button: 100
          },
          tooltip: {
            // 悬浮框提示相关
            trigger: 'item',
            backgroundColor: 'none',
            formatter: function (params) {
              const str = '<div class="tooltip"> <div class="tooltip-title note-circle-blue">' + params.seriesName + '</div> <div class="tooltip-content"> <span class="tooltip-title-left">' + params.name + '</span> <sapn class="tooltip-title-right">' + params.value + '</sapn> </div><div class="tooltip-content"> <span class="tooltip-title-left">比例</span> <sapn class="tooltip-title-right">' + params.percent + '%</sapn> </div> </div>'
              return str
            }
          },
          legend: {
            // legend 图例相关
            type: 'scroll',
            orient: 'vertical',
            left: '60%',
            top: 55,
            itemWidth: 8,
            itemHeight: 8,
            data: [
              { name: '产', icon: 'circle' },
              { name: '寿', icon: 'circle' },
              { name: '健', icon: 'circle' },
              { name: '资', icon: 'circle' }
            ]
          },
          series: {
            name: this.pieData.barViewMap[1].platForm + '平台',
            type: 'pie',
            center: ['35%', '50%'],
            selectedMode: true, // 是否支持多选，默认为false,鼠标点击后选中饼图分裂出来
            data: [
              { name: '产', value: this.JudgePositiveNegative(this.pieData.barViewMap[1].product) },
              { name: '寿', value: this.JudgePositiveNegative(this.pieData.barViewMap[1].life) },
              { name: '健', value: this.JudgePositiveNegative(this.pieData.barViewMap[1].health) },
              { name: '资', value: this.JudgePositiveNegative(this.pieData.barViewMap[1].wealth) }
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
                    '#5680BB', '#9CAAB3', '#7CBC5C', '#DDCB67', '#C6736C'
                  ]
                  return colorList[params.dataIndex]
                }
              }
            }
          }
        }

        var option2 = {
          title: {
            text: this.pieData.barViewMap[2].platForm,
            left: 20,
            button: 100
          },
          tooltip: {
            // 悬浮框提示相关
            trigger: 'item',
            backgroundColor: 'none',
            formatter: function (params) {
              const str = '<div class="tooltip"> <div class="tooltip-title note-circle-blue">' + params.seriesName + '</div> <div class="tooltip-content"> <span class="tooltip-title-left">' + params.name + '</span> <sapn class="tooltip-title-right">' + params.value + '</sapn> </div><div class="tooltip-content"> <span class="tooltip-title-left">比例</span> <sapn class="tooltip-title-right">' + params.percent + '%</sapn> </div> </div>'
              return str
            }
          },
          legend: {
            // legend 图例相关
            type: 'scroll',
            orient: 'vertical',
            left: '60%',
            top: 55,
            itemWidth: 8,
            itemHeight: 8,
            data: [
              { name: '产', icon: 'circle' },
              { name: '寿', icon: 'circle' },
              { name: '健', icon: 'circle' }
            ]
          },
          series: {
            name: this.pieData.barViewMap[2].platForm + '平台',
            type: 'pie',
            center: ['35%', '50%'],
            selectedMode: true, // 是否支持多选，默认为false,鼠标点击后选中饼图分裂出来
            data: [
              { name: '产', value: this.JudgePositiveNegative(this.pieData.barViewMap[2].product) },
              { name: '寿', value: this.JudgePositiveNegative(this.pieData.barViewMap[2].life) },
              { name: '健', value: this.JudgePositiveNegative(this.pieData.barViewMap[2].health) }
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
                    '#5680BB', '#9CAAB3', '#7CBC5C', '#DDCB67', '#C6736C'
                  ]
                  return colorList[params.dataIndex]
                }
              }
            }
          }
        }
        // 使用制定的配置项和数据显示图表
        myPie.setOption(option)
        myPie2.setOption(option1)
        myPie3.setOption(option2)
        window.onresize = function () {
          // myPie.resize()
          // myPie2.resize()
          // myPie3.resize()
        }
      })
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

.total-amount-font{
  width: 94px;
  height: 24px;
  font-size: 16px;
  font-family: MicrosoftYaHei-Bold, MicrosoftYaHei;
  font-weight: bold;
  color: #333333;
  line-height: 24px;

}

.mom-font{
  width: 28px;
  height: 19px;
  font-size: 14px;
  font-family: MicrosoftYaHei;
  color: #333333;
  line-height: 19px;
}

.mom-font-num{
  width: 52px;
  height: 19px;
  font-size: 14px;
  font-family: MicrosoftYaHei-Bold, MicrosoftYaHei;
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

</style>
