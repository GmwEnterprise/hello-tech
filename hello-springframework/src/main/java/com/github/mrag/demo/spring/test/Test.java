package com.github.mrag.demo.spring.test;

public class Test {
    public static void fun1(int num) {
        int _1 = num / 1000, _2 = num / 100 % 10,
                _3 = num / 10 % 10, _4 = num % 10;

        _1 = (_1 + 5) % 10;
        _2 = (_2 + 5) % 10;
        _3 = (_3 + 5) % 10;
        _4 = (_4 + 5) % 10;

        int result = _4 * 1000 + _3 * 100 + _2 * 10 + _1;

        System.out.println(result);
    }

    public static void fun2() {

    }

    public static void main(String[] args) {
        fun1(1984);
    }
}
