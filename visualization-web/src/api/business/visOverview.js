import request from '@/utils/request'

// 数据可视化概览api
const visOverviewApi = {
  TodayOverview: 'business/getTodayOverview',
  TodayMapData: 'business/getTodayMapData'
}

// 数据可视化-今日数据总览
export function getTodayOverview () {
  return request({
    url: visOverviewApi.TodayOverview,
    method: 'get'
  })
}

// 数据可视化-今日数据总览
export function getTodayMapData (query) {
  return request({
    url: visOverviewApi.TodayMapData,
    method: 'get',
    params: query
  })
}
