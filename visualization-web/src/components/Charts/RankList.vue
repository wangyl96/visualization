<template>
  <div class="rank">
    <h4 class="title" style="font-size: 18px; color: #333333">{{ title }}</h4>
    <ul class="list">
      <li :key="index" v-for="(item, index) in list">
        <span :class="index < 3 ? 'active' : null">{{ index + 1 }}</span>
        <span>{{ item.name }}</span>
        <span>{{ item.total }}</span>
      </li>
    </ul>
  </div>
</template>

<script>

export default {
  name: 'RankList',
  // ['title', 'list']
  props: {
    title: {
      type: String,
      default: ''
    },
    list: {
      type: Array,
      default: null
    }
  }
}
</script>

<style lang="less" scoped>

  .rank {
    padding: 0 32px 32px 72px;

    .list {
      margin: 20px 0 0;
      padding: 0;
      list-style: none;

      li {
        margin-top: 16px;

        span {
          color: #333333;
          font-size: 18px;
          line-height: 22px;

          &:first-child {
            background-color: #f5f5f5;
            border-radius: 28px;
            display: inline-block;
            font-size: 16px;
            margin-right: 14px;
            height: 28px;
            line-height: 28px;
            width: 28px;
            text-align: center;
          }
          &.active {
            background-color: #314659;
            color: #fff;
          }
          &:last-child {
            float: right;
          }
        }
      }
    }
  }

  .mobile .rank {
    padding: 0 32px 32px 32px;
  }

</style>
