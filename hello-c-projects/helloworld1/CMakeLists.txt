cmake_minimum_required(VERSION 3.16)
project(helloworld1 C)

set(CMAKE_C_STANDARD 11)

include_directories(src)

add_executable(helloworld1
        src/primer.c
        src/primer.h
        src/arraydemo.c
        src/arraydemo.h
        main.c src/point_array.c src/point_array.h)
