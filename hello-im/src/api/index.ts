import axios from 'axios'
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
    return Promise.reject(response.data)
  },
  (error) => {
    if (error instanceof Error) {
      return Promise.reject({
        code: '0001',
        message: error.message,
        body: error,
      })
    }
    return error
  },
)

export default http

export async function tokenValidation(): Promise<HttpResponse> {
  const response = await http.post('/im-user/token-validation')
  return response.data
}

export async function parameterValidation(
  paramaterBody: object,
): Promise<HttpResponse> {
  const response = await http.post(
    '/im-user/parameter-validation',
    paramaterBody,
  )
  return response.data
}
