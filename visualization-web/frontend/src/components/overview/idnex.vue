<template>
  <div>
    <a-col :span="7">
      <a-card size="small" :headStyle='tstyle' title="APP" style="margin-top: 22px; margin-left: 40px;" :bordered="false">
        <a-space :size="60">
          <a-space direction="vertical" :size="5">
            <span>注册量10574</span>
            <span>环比40.1%</span>
          </a-space>

          <a-space direction="vertical" :size="5">
            <span>注册量   10574</span>
            <span>环比   40.1%</span>
          </a-space>

          <a-space direction="vertical" :size="5">
            <span>注册量   10574</span>
            <span>环比   40.1%</span>
          </a-space>
        </a-space>
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
            test: ''
          }
        }
      },
      platformOverview: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        platformInfo: this.platform,
        tstyle: {"border-bottom":"0px", "margin-bottom":"-12px"}
      }
    }
  }
</script>
<style lang="less" scoped>

</style>