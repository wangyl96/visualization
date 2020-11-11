<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="header-info">
  <a-col :sm="8" :xs="24">
      <a-card  :title="platformInfo.custNmae" :bordered="false" :style="{fontSize: '16px', color: '#333'}" style="padding-top: 4px; marginBottom:20px" >
        <div class="list-content" style="margin-top: -22px; margin-left: -24px">
          <div class="list-content-item-demo1">
            <a-statistic :value="'总'+platformInfo.custNmae" :value-style="{fontSize: '16px', marginBottom: '10px', fontWeight: 'bold'}" >
            </a-statistic>
            <span style="font-size: 16px; color: #333333; font-weight: normal; font-family: MicrosoftYaHei">{{platformInfo.total}}</span>
          </div>
          <div class="list-content-item-demo1">
            <a-statistic :value="this.year+'年'+platformInfo.custNmae" :value-style="{fontSize: '16px', marginBottom: '10px', fontWeight: 'bold'}" >
            </a-statistic>
            <span>{{platformInfo.yearValue}}</span>
          </div>
        </div>
      </a-card>
   </a-col>
  </div>
</template>

<script>
// import elementResizeDetectorMaker from 'element-resize-detector'
import { sameYear } from '@/utils/dateUtil'

export default {
  name: 'PlatformCardView',
  props: {
    platformCardViewInfo: {
      type: Object,
      default: function () {
        return {

        }
      }
    },
    platformCardView: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      platformInfo: this.platformCardViewInfo,
      year: sameYear
    }
  },
  watch: {
    platformInfo: {
      deep: true,
      handler (newVal, oldVal) {
        this.platformInfo = JSON.parse(JSON.stringify(newVal))
        console.log(this.platformInfo)
      }
    }
  },
  methods: {
  },
  mounted () {
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

//.list-content-item {
//  color: rgba(0, 0, 0, .45);
//  display: inline-block;
//  vertical-align: middle;
//  font-size: 14px;
//  float: left;
//
//  span {
//    line-height: 20px;
//  }
//
//  p {
//    margin-top: 4px;
//    margin-bottom: 0;
//    line-height: 22px;
//  }
//}
.list-content-item-demo1 {
  color: rgba(0, 0, 0, .45);
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-top: -5px;
  margin-left: 46px;
  margin-right: 70px;

  span {
    line-height: 20px;
  }

  p {
    margin-top: 4px;
    margin-bottom: 0;
    line-height: 22px;
  }
}
.list-content-item-demo2 {
  color: rgba(0, 0, 0, .45);
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-top: -5px;
  margin-left: 160px;

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
