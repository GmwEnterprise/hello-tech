async function getComponent() {
  const element = document.createElement('div')
  const { default: _ } = await import('lodash')
  element.innerHTML = _.join(['Hello', 'Webpack'], ' ')
  return element
}

getComponent().then((element) => document.body.appendChild(element))
