<template>
  <div class="vis-container">
    <!--  数据总览  -->
    <a-space class="vis-main">
      <div class="vis-tag"></div>
      <span class="vis-font">数据总览 2020-09-20</span>
    </a-space>
    <!--  分割线  -->
    <a-divider/>
    <!--  各平台卡片  -->
    <a-row>
      <platform-overview ref="platformOverview" :platform="platform"></platform-overview>
      <a-col :span="1">
        <div style="width: 1px; height: 72px; margin-top: 34px;  background-color: #ECECEC;"></div>
      </a-col>
      <PlatformOverview></PlatformOverview>
      <a-col :span="1">
        <div style="width: 1px; height: 72px; margin-top: 34px;  background-color: #ECECEC;"></div>
      </a-col>
      <PlatformOverview></PlatformOverview>
    </a-row>

  </div>
</template>



<script>
  import PlatformOverview from '@/components/overview/idnex'

  export default {
    name: 'overview',
    components: { PlatformOverview },
    data() {
      return {
        platform: {
          test: '1111'
        },
        tstyle:{"border-bottom":"0px", "margin-bottom":"-12px"}
      }
    },
    mounted() {
    },
    methods: {

    }
  }
</script>
<style lang="less" scoped>
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
    padding: 13px 20px 0px 20px;
  }

  .ant-divider-horizontal {
    margin: 13px 0px 0px 0px;
  }

  .vis-font {
    font-family: MicrosoftYaHei;
    font-size: 18px;
    line-height: 24px;
    color: #333333;
  }

  //.ant-card-head {
  //  border-bottom: 0px;
  //}
</style>