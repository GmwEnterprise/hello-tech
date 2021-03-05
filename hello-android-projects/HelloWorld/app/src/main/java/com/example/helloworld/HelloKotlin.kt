package com.example.helloworld

import kotlin.math.max
import kotlin.math.min

// 声明一个普通函数
fun returnMax(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

// kotlin语法糖：只有一行代码 / 返回类型可省略
fun returnMin(num1: Int, num2: Int) = min(num1, num2)

// if 具有返回值属性
fun returnMaxIf(num1: Int, num2: Int): Int {
    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}

// 一行代码
fun returnMaxIfSimple(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Long -> println("number is Long")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

// 主函数
fun main() {
    checkNumber(0x80000000) // Long
    checkNumber(0x7fffffff) // Int
    checkNumber(1L) // Long
    checkNumber(1.0) // Double
    checkNumber(1.0f) // Float -> not support
}

val range = 0..10
fun loop() {
    for (i in range step 2) print(i)
}

// 定义一个对象
class Person {

}