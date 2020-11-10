<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="header-info">
    <a-col :sm="8" :xs="24">
      <a-card :title="platformInfo.platformName" :bordered="false" :style="{fontSize: '16px', color: '#333'}" style="padding-top: 4px; marginBottom:20px" @click="platformDetails()">
        <div class="list-content" style="margin-top: -22px; margin-left: -24px">
          <div class="list-content-item" v-for="item in platformInfo.todayOverviewMap" :key="item.contentName">
            <a-statistic :value="item.contentData" :value-style="{fontSize: '16px', marginBottom: '10px', fontWeight: 'bold'}" >
              <template v-slot:prefix>
                <span style="font-size: 16px; color: #333333; font-weight: normal; font-family: MicrosoftYaHei">{{item.contentName}}</span>
              </template>
            </a-statistic>
            <a-statistic v-if="item.ratio < 0" :value="Math.abs(parseFloat(item.ratio * 100).toFixed(2))" :value-style="{color: '#3CB800', fontSize: '14px', fontWeight: 'bold', paddingBottom:'20px'}">
              <template v-slot:prefix>
                <span style="font-size: 14px; color: #333333; font-weight: normal">环比</span>
                <img src="../../../public/static/icon/drop.png" style="margin-top: -3px; margin-left: 6px"/>
              </template>
              <template v-slot:suffix>
                <span>%</span>
              </template>
            </a-statistic>
            <a-statistic v-else :value="Math.abs(parseFloat(item.ratio * 100).toFixed(2))" :value-style="{color: '#F44242', fontSize: '14px', fontWeight: 'bold', paddingBottom:'20px'}">
              <template v-slot:prefix>
                <span style="font-size: 14px; color: #333333; font-weight: normal;">环比</span>
                <img src="../../../public/static/icon/up.png" style="margin-top: -3px; margin-left: 6px"/>
              </template>
              <template v-slot:suffix>
                <span>%</span>
              </template>
            </a-statistic>
          </div>
        </div>
      </a-card>
    </a-col>
  </div>

</template>

<script>
// import elementResizeDetectorMaker from 'element-resize-detector'
export default {
  name: 'PlatformOverview',
  props: {
    platform: {
      type: Object,
      default: function () {
        return {

        }
      }
    },
    platformOverview: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      platformInfo: this.platform,
      height: ''
    }
  },
  methods: {
    platformDetails () {
      // <router-link></router-link>
      console.log('点击事件')
      this.$router.push({ name: 'HisPlatformOverview' })
    }
  },
  mounted () {
    // const _this = this
    // const erd = elementResizeDetectorMaker()
    // erd.listenTo(document.getElementById('core1'), element => {
    //   _this.height = element.offsetHeight + 'px'
    //   _this.$forceUpdate()
    //   // _this.$nextTick(() => {
    //   //   // 监听到事件后执行的业务逻辑
    //   //   // _this.$set(this.height, element.offsetHeight + 'px')
    //   //   // console.log(element.offsetHeight + 'px')
    //   // })
    // })
  }
}
</script>
<style lang="less">
@import "~ant-design-vue/es/style/themes/default.less";
.ant-statistic-content-value-decimal {
  font-size: 14px;
}

.vis_main {
  font-family: MicrosoftYaHei;
  font-size: 16px;
  line-height: 21px;
  color: #333333;
}

.ant-card-head {
  border-bottom: 0px;
}

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

}

.list-content-item {
  color: rgba(0, 0, 0, .45);
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-left: 50px;
  span {
    line-height: 20px;
  }
  p {
    margin-top: 4px;
    margin-bottom: 0;
    line-height: 22px;
  }
}

</style>
