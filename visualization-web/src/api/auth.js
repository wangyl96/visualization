import request from '@/utils/request'

const authApi = {
  Auth: '/oauth/token'
}

const qs = require('qs')

export function auth (parameter) {
  parameter = {
    'client_id': 'client-app',
    'client_secret': '123456',
    'grant_type': 'password',
    'username': 'macro',
    'password': '123456'
  }
  parameter = qs.stringify(parameter)
  return request({
    url: authApi.Auth,
    method: 'post',
    data: parameter,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
