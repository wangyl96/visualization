import { getTodayOverview } from '@/api/business/visOverview'

const todayOverview = {
  actions: {
    // 登录
    TodayOverview ({ commit }) {
      return new Promise((resolve, reject) => {
        getTodayOverview().then(response => {
          console.log(response)
          const result = response.data
          resolve(result)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default todayOverview
