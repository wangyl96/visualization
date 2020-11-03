<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="header-info">
    <a-col :sm="8" :xs="24">
      <a-card :title="platformInfo.platformName" :bordered="false" style="margin-left: 16px; margin-top: 16px">
        <div style="margin-top: -24px; margin-left: -18px">
          <a-row>
            <a-col :sm="8" :xs="24" v-for="item in platformInfo.platformContent" :key="item.contentName">
              <ul>
                <li>
                  <a-statistic :value="item.contentData" :value-style="{fontSize: '16px', lineHeight: '20px' }">
                    <template v-slot:prefix>
                      <span>{{item.contentName}}</span>
                    </template>
                  </a-statistic>
                </li>
                <li style="margin-top: 8px;">
                  <a-statistic :value="item.ratio" :value-style="{fontSize: '16px', lineHeight: '20px' }">
                    <template v-slot:prefix>
                      <span>环比</span><img src="../../../public/static/icon/drop.png" style="margin-top: -3px; margin-left: 6px"/>
                    </template>
                    <template v-slot:suffix>
                      <span>%</span>
                    </template>
                  </a-statistic>
                </li>
              </ul>
            </a-col>
          </a-row>
        </div>
        <a-divider v-if="platformInfo.isEnd" type="vertical" style="height: 76px; margin-top: -120px; margin-left: -10px"/>

      </a-card>

    </a-col>
  </div>

</template>

<script>
  export default {
    name: 'PlatformOverview',
    props: {
      platform: {
        type: Object,
        default: function () {
          return {
            'platformName': '',
            'isEnd': '',
            'platformContent': [{
              'contentName': '',
              'contentData': '',
              'ratio': '',
              'upOrDown': ''
            }, {
              'contentName': '',
              'contentData': '',
              'ratio': '',
              'upOrDown': ''
            }, {
              'contentName': '',
              'contentData': '',
              'ratio': '',
              'upOrDown': ''
            }]
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
        tstyle: { 'border-bottom': '0px', 'margin-left': '16px' },
        data: [
          {
            key: '1',
            name: '注册量10574',
            money: '安装量21913',
            address: '日活112640',
            divider: '111'
          },
          {
            key: '2',
            name: '环比10%',
            money: '环比10',
            address: '环比10%',
            divider: '111'
          }
        ],
        columns: [
          {
            title: 'Name',
            dataIndex: 'name',
            slots: { customRender: 'name' },
            ellipsis: true
          },
          {
            title: 'Cash Assets',
            className: 'column-money',
            dataIndex: 'money',
            ellipsis: true
          },
          {
            title: 'Address',
            dataIndex: 'address',
            ellipsis: true
          }
        ]
      }
    }
  }
</script>
<style lang="less">
  @import "~ant-design-vue/es/style/themes/default.less";

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
</style>
