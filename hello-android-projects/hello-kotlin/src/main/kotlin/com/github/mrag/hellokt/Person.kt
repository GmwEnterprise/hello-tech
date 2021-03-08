package com.github.mrag.hellokt

open class Person(val name: String, val age: Int)

interface Study {
    fun readBooks()
    fun doHomework() {
        println("do homework default implementation.")
    }
}

class Student(name: String, age: Int) : Person(name, age), Study {
    override fun readBooks() {
        TODO("Not yet implemented")
    }

    override fun doHomework() {
        println("do homework override implementation.")
    }
}
