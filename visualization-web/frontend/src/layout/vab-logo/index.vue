<template>
  <div class="vab-logo">
    <vab-icon v-if="logo" :icon="logo"></vab-icon>
    <img src="../../../public/static/icon/picc_icon.png" class="logo" alt="logo">
    <span>{{ title }}</span>
  </div>
</template>

<script>
  import VabIcon from '@/layout/vab-icon'
  import { mapGetters } from 'vuex'
  export default {
    name: 'VabLogo',
    components: { VabIcon },
    computed: {
      ...mapGetters({
        logo: 'settings/logo',
        title: 'settings/title',
      }),
    },
  }
</script>
<style lang="less" scoped>
  .vab-logo {
    display: flex;
    align-items: center;
    justify-content: left;
    height: 32px;
    // 上 右 下 左
    margin: 10px 0px 5px 20px;
    overflow: hidden;
    overflow: hidden;
    font-size: 20px;
    font-weight: 600;
    color: #fff;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .logo {
    height: 20px;
    margin: 4px 20px 0px 0px;
  }

</style>
