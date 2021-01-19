function getValue<T extends object, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}

const val1 = {
  name: "xiaomuzhu",
  id: 1,
};

getValue(val1, "name");

interface FirstInterface {
  doSomething(): number;
}

interface SecondInterface {
  doSomethingElse(): string;
}

class Demo<T extends FirstInterface & SecondInterface> {
  private genericProperty: T;

  constructor(param: T) {
    this.genericProperty = param;
  }

  useT() {
    this.genericProperty.doSomething(); // ok
    this.genericProperty.doSomethingElse(); // ok
  }
}
