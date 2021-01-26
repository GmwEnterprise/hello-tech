import { join } from 'lodash'
import printMe from './print'

function component() {
  const element = document.createElement('div')
  const btn = document.createElement('button')

  element.innerHTML = join(['Hello', 'Webpack'], ' ')
  btn.innerHTML = 'Click me'
  btn.onclick = printMe

  element.appendChild(btn)
  return element
}

document.body.appendChild(component())
