package com.example.helloworld.hellokt

/*
 * Kotlin中声明一个普通类
 * 默认为Java中的public final class，即不可继承
 */
open class Person {
    var name = ""
    var age = 0

    fun eat() {
        println("$name is eating. He is $age years old.")
    }
}

class Student : Person() {

}

fun main() {
    // Kotlin中没有new关键字
    val person = Person()
    person.name = "Mrag"
    person.age = 25
    person.eat()
}