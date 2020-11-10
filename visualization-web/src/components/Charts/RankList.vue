<template>
  <div class="rank">
    <h4 class="title" style="font-size: 16px; color: #333333; font-family: MicrosoftYaHei;">{{ title }}</h4>
    <ul class="list">
      <li :key="index" v-for="(item, index) in list">
        <span :class="index < 3 ? 'active' : 'other'">{{ index + 1 }}</span>
        <span>{{ item.name }}</span>
        <span>{{ formatNum(item.value) }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import { formatNum } from '@/utils/util'
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
  },
  methods: {
    formatNum (value) {
      return formatNum(value)
    }
  },
  mounted () {
    console.log(formatNum(0))
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
          font-size: 14px;
          line-height: 22px;
          font-family: MicrosoftYaHei;
          &:first-child {
            background-color: #f5f5f5;
            border-radius: 28px;
            display: inline-block;
            font-size: 14px;
            margin-right: 14px;
            height: 24px;
            line-height: 24px;
            width: 24px;
            text-align: center;
          }
          &.active {
            background-color: #314659;
            color: #fff;
          }
          &.other {
            color: #999999;
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
