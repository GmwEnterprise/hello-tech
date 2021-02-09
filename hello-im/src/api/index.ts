import axios from 'axios'
import qs from 'qs'
import { store, Mutations } from '../store'

export interface HttpResponse {
  readonly code: string
  readonly message: string
  readonly body?: object | null | Error
}

const http = axios.create({
  baseURL: '/api',
  // timeout: 3000,
  // withCredentials: false, // 前端开发模式使用webpack反向代理；生产模式使用nginx反向代理，无需关心跨域配置
})

http.interceptors.request.use((config) => {
  if (store.state.userMsg.token) {
    config.headers.authorization = store.state.userMsg.token
  }
  return config
})

http.interceptors.response.use(
  (response) => {
    const result = response.data as HttpResponse
    if (result.code === '0000') {
      if (response.headers.authorization) {
        store.commit(Mutations.tokenUpdate, response.headers.authorization)
      }
      return response
    }
    return Promise.reject(result)
  },
  (error) => {
    if (error instanceof Error) {
      return Promise.reject({
        code: '0006',
        message: error.message,
        body: error,
      })
    }
    return error
  },
)

/**
 * axios实例
 */
export default http

/**
 * token校验
 */
export async function tokenValidation(): Promise<HttpResponse> {
  return (await http.post('/im-user/token-validation')).data
}

/**
 * 参数校验
 * @param params 待校验参数
 */
export async function parameterValidation(
  params: object,
): Promise<HttpResponse> {
  return (await http.post('/im-user/parameter-validation', params)).data
}

/**
 * 登录
 * @param params 登录入参
 */
export async function signIn(params: object): Promise<HttpResponse> {
  return (await http.post('/im-user/sign-in', qs.stringify(params))).data
}

/**
 * 注册
 * @param params 注册入参
 */
export async function signOn(params: object): Promise<HttpResponse> {
  return (await http.post('/im-user/sign-on', params)).data
}

/**
 * 通过令牌获取当前用户信息
 */
export async function aboutMe(): Promise<HttpResponse> {
  return (await http.get('/im-user/about-me')).data
}
