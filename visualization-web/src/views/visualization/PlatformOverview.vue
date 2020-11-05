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
          <div>
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
          </div>

          <div>
            <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">
              <div id="myPie2" :style="{width: '300px', height: '200px'}"></div>
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
          </div>

          <div>
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
          </div>
<!--          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">-->
<!--            <div id="myPie2" :style="{width: '300px', height: '200px'}"></div>-->
<!--          </a-col>-->
<!--          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">-->
<!--            <ul style="margin-left: -42px; margin-top: 5px;color: #333333">-->
<!--              <li style="font-size: 14px">¥792148.18</li>-->
<!--              <li style="font-size: 12px">环比<img src="../../../public/static/icon/up.png" style="margin-top: -3px" />30%</li>-->
<!--            </ul>-->
<!--            <ul style="margin-top: -8px">-->
<!--              <li v-for="item in 5" :key="item">-->
<!--                <a-divider type="vertical" />-->
<!--                <span>66.6{{ item }}%</span>-->
<!--                <span>¥582501.18</span>-->
<!--              </li>-->
<!--            </ul>-->
<!--          </a-col>-->

<!--          <a-col :xl="3" :lg="12" :md="12" :sm="24" :xs="24">-->
<!--            <div id="myPie3" :style="{width: '300px', height: '200px'}"></div>-->
<!--          </a-col>-->
<!--          <a-col :xl="5" :lg="12" :md="12" :sm="24" :xs="24">-->
<!--            <ul style="margin-left: -42px; margin-top: 5px; color: #333333">-->
<!--              <li style="font-size: 14px">¥792148.18</li>-->
<!--              <li style="font-size: 12px">环比<img src="../../../public/static/icon/up.png" style="margin-top: -3px" />30%</li>-->
<!--            </ul>-->
<!--            <ul style="margin-top: -8px">-->
<!--              <li v-for="item in 5" :key="item">-->
<!--                <a-divider type="vertical" />-->
<!--                <span>66.6{{ item }}%</span>-->
<!--                <span>¥582501.18</span>-->
<!--              </li>-->
<!--            </ul>-->
<!--          </a-col>-->

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
import { getTodayMapData, getTodayOverview } from '@/api/business/visOverview'
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
    this.drawPie()
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
