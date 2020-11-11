import request from '@/utils/request'
// 数据可视化平台api
const hisVisOverviewApi = {
  hiPlatformData: 'business/getYearPlatData'
}
// 各个平台的总安装 总访客
export function getPlatformInstOrVis (query) {
  return request({
    url: hisVisOverviewApi.hiPlatformData,
    method: 'get',
    params: query
  })
}
