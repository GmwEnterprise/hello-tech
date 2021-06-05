//
// Created by Gmw on 2021/5/11.
//
#include <stdio.h>
#include "src/primer.h"
#include "src/arraydemo.h"
#include "src/point_array.h"

#define wrap(x) printf(">>>\n");x;printf("\n")
#define wrapOut(format, x) printf(">>>\n");printf(format, x);printf("\n")
#define max(x, y) (x > y ? x : y)

int main() {
    // 设置printf打印时不输出到缓冲区，方便调试
    setvbuf(stdout, NULL, _IONBF, 0);
#ifdef wrap
#ifdef wrapOut
#ifdef max
    wrap(sayHello("Mrag"));
    wrapOut("max is %d", max(1, 2));
    wrapOut("%d", 0x7FFFFFFE + 1);
    wrap(loccheck());
    wrap(pointerDemo());
    wrap(pointerDemo2());
    wrap(pointerDemo3());
    wrap(swapIntTest());
    wrap(day_mon1());
    wrap(no_data_32());
    wrap(no_data_64());
    wrap(array_addr_in_memory());
    wrap(point_array());
#endif
#endif
#endif
}

#undef wrap
#undef wrapOut
#undef max