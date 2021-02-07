import { App } from 'vue'
import { ElButton, ElForm, ElFormItem, ElInput } from 'element-plus'

export default function useElement(app: App<Element>) {
  app
    .use(ElButton)
    .use(ElForm)
    .use(ElFormItem)
    .use(ElInput)
}
