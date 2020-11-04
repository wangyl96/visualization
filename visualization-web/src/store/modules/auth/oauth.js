import { auth } from '@/api/auth'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const oauth = {
  state: {
    access_token: ''
  },

  mutations: {
    SET_ACCESS_TOKEN: (state, accessToken) => {
      state.access_token = accessToken
      storage.set(ACCESS_TOKEN, accessToken, 7 * 24 * 60 * 60 * 1000)
    },
    SET_NAME: (state, { name, welcome }) => {
      state.name = name
      state.welcome = welcome
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    }
  },

  actions: {
    // 登录
    Auth ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        auth(userInfo).then(response => {
          const result = response.data
          commit('SET_ACCESS_TOKEN', result.tokenHead + result.token)
          resolve(result.tokenHead + result.token)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default oauth
