import { auth } from '@/api/auth'

const oauth = {
  state: {
    token: ''
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
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
          console.log(response)
          const result = response.data
          commit('SET_TOKEN', result.tokenHead + result.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default oauth
