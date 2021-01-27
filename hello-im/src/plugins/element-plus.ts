import { App } from 'vue'
import { ElButton } from 'element-plus'

export default function useElement(app: App<Element>) {
  app.use(ElButton)
}
