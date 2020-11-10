import request from '@/utils/request'

// 数据可视化概览api
const visOverviewApi = {
  TodayOverview: 'business/getTodayOverview',
  TodayMapData: 'business/getTodayMapData',
  PieView: 'business/getAppData'
}

// 数据可视化-今日数据总览
export function getTodayOverview (query) {
  return request({
    url: visOverviewApi.TodayOverview,
    method: 'get',
    params: query
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

// 数据可视化-饼图数据
export function getPieView () {
  return request({
    url: visOverviewApi.PieView,
    method: 'get'
  })
}
