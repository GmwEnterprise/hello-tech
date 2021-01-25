import { defineComponent } from 'vue'

const props = {
  msg: String,
  age: {
    type: Number,
    required: true,
  },
} as const

export default defineComponent({
  props,
  setup(props) {
    return () => (
      <div class="hello">
        <h1>{props.age + ':' + props.msg}</h1>
      </div>
    )
  },
})
