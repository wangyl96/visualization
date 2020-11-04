import request from '@/utils/request'

// 数据可视化概览api
const visOverviewApi = {
  TodayOverview: 'business/getTodayOverview'
}

// 数据可视化-今日数据总览
export function getTodayOverview () {
  return request({
    url: visOverviewApi.TodayOverview,
    method: 'get',
    headers: {
      'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJtYWNybyIsInNjb3BlIjpbImFsbCJdLCJpZCI6MSwiZXhwIjoxNjA0NDYwODgxLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiJlNTQ2YWY1OS04Yzc2LTQ4MDgtODgwZi1lMWNjNTcxNjNmYjQiLCJjbGllbnRfaWQiOiJjbGllbnQtYXBwIn0.ngyq_zHszmgkcTxHdsFSaS4lVc3-5gmQcAvH1c24g3ePeTBHyfCf7rs80tukTf3_hx3LoWI-mWNud0FrWXjp7Hq8V-sufSttHfHUJfDe9o5D67vsLF_VhGRstBQg2FVtep-le5gbVfxJnDYSsiq0dl3T5FMB4WRT4E-lswU9NCg'
    }
  })
}
