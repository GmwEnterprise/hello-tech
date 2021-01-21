import { defineComponent, reactive, ref } from 'vue';
import HelloWorld from '@/components/HelloWorld';

const img = () => import('./assets/logo.png'); // eslint-disable-line

function renderHelloWorld(number: number, msg: string) {
  return <HelloWorld msg={msg} age={number} />;
}

export default defineComponent({
  setup() {
    const state = reactive({
      name: 'Mrag',
      title: 'Welcome to Your Vue.js + TypeScript App',
    });
    const numberRef = ref(1);
    // setInterval(() => {
    //   state.name += '=';
    //   numberRef.value += 1;
    // }, 1000);
    return () => {
      const num = numberRef.value;
      return (
        <div id="app">
          <img src={img} alt="Vue logo" />
          <p>{state.name + num}</p>
          <input type="text" v-model={state.name} />
          {renderHelloWorld(24, state.title)}
        </div>
      );
    };
  },
});
