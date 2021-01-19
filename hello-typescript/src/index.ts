interface Config {
  width?: number;
}

function CalculateAreas(config: Config): { area: number } {
  let square = 100;
  if (config.width) {
    square = config.width * config.width;
  }
  return { area: square };
}

let mySquare = CalculateAreas({ width: 5 });

interface Phone {
  [name: string]: string;
}

interface User {
  name: string;
  age?: number;
  readonly isMale: boolean;
  say: () => string;
  phone: Phone;
}

interface VIPUser extends User {
  broadcast: () => void;
}

const user1: VIPUser = {
  name: "xiaoming",
  age: 16,
  isMale: true,
  say: () => "Hello!",
  phone: {
    NetEase: "xiaoming@163.com",
    qq: "784536325@qq.com",
    sina: "abc784536325@sina.com",
  },
  broadcast: () => console.log("Hello, Broadcast"),
};
