const Ajv = require('ajv').default
// 版本7开始，需要单独引入这个东西
const addFormats = require('ajv-formats')

const schema = {
  type: 'object',
  properties: {
    name: {
      type: 'string',
      format: 'email',
    },
    age: {
      type: 'number',
    },
    pets: {
      type: 'array',
      items: {
        type: 'string',
      },
    },
    isWorker: {
      type: 'boolean',
    },
  },
  required: ['name', 'age'],
}

const ajv = new Ajv() // options can be passed, e.g. {allErrors: true}
addFormats(ajv)
// ajv.addFormat('myname', (value) => {
//   console.log('custom-format');
//   return value === 'Mrag';
// });

const validate = ajv.compile(schema)
const valid = validate({
  name: 'Mrag',
  age: 24,
})
if (!valid) console.log(validate.errors)
