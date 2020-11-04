import request from '@/utils/request'

// 数据可视化概览api
const visOverviewApi = {
  TodayOverview: 'business/getTodayOverview'
}

// 数据可视化-今日数据总览
export function getTodayOverview () {
  return request({
    url: visOverviewApi.TodayOverview,
    method: 'get'
  })
}
