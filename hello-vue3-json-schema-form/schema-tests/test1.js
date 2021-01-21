const Ajv = require('ajv').default;

const schema = {
  type: 'object',
  properties: {
    name: {
      type: 'string',
      // format: 'myname',
      // minLength: 10,
    },
    bindEmail: {
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
};

const ajv = new Ajv(); // options can be passed, e.g. {allErrors: true}

// ajv.addFormat('myname', (value) => {
//   console.log('custom-format');
//   return value === 'Mrag';
// });

const validate = ajv.compile(schema);
const valid = validate({
  name: 'Mrag',
  bindEmail: '909498022@qq.com',
  age: 24,
  // pets: ['mimi', 'mama'],
  // isWorker: true,
});
if (!valid) console.log(validate.errors);
