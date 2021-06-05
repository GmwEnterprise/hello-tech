//
// Created by Gmw on 2021/5/11.
//

#include <stdio.h>
#include "primer.h"

void sayHello(char *name) {
    printf("Hello, %s\n", name);
}

void mikado(int bah) /* 定义函数 */
{
    int pooh = 10; /* mikado()的局部变量 */
    printf("In loccheck(), pooh = %2d and &pooh = %p\n", pooh, &pooh);
    printf("In loccheck(),  bah = %2d and  &bah = %p\n", bah, &bah);
}

void loccheck() {
    int pooh = 2, bah = 5;
    printf("In loccheck(), pooh = %2d and &pooh = %p\n", pooh, &pooh);
    printf("In loccheck(),  bah = %2d and  &bah = %p\n", bah, &bah);
    mikado(bah);
    /**
     * output：
     * In loccheck(), pooh =  2 and &pooh = 000000000061FDEC
     * In loccheck(),  bah =  5 and  &bah = 000000000061FDE8
     * In loccheck(), pooh = 10 and &pooh = 000000000061FDAC
     * In loccheck(),  bah =  5 and  &bah = 000000000061FDC0
     */
}

void pointerDemo() {
    // 声明一个int变量并赋值
    int num = 5;
    // 声明一个指向num地址的int指针
    int *p = &num;
    // 打印num的值
    printf(" num = %d\n", num);
    // &num打印num的地址
    printf("&num = %p\n", &num);
    // p指向num的地址
    printf("   p = %p\n", p);
    // *p为p指向的地址的值，相当于num
    printf("  *p = %d\n", *p);
    // 符号的嵌套使用，可以嵌套很多次
    printf(" &*p = %p\n", &*p);
    // 指向(指向num的地址的指针)的指针
    printf("  &p = %p\n", &p);
    // 指向(指向num的地址的指针)的指针所代表的值，== &num == p
    printf(" *&p = %p\n", *&p);
    /*
     * output:
     *  num = 5
     * &num = 000000000061FDEC
     *    p = 000000000061FDEC
     *   *p = 5
     *  &*p = 000000000061FDEC
     *   &p = 000000000061FDE0
     *  *&p = 000000000061FDEC
     */
}

void pointerDemo2() {
    int num = 10;
    // 声明两个指针，其中p1等于num的地址(p1指向num)
    int *p1 = &num, *p2;
    // p2等于p1的值，也等于num的地址(p2与p1同时指向num)
    p2 = p1;
    // output: p1 = 000000000061FDDC, p2 = 000000000061FDDC, *p1 = 10, *p2 = 10
    printf("p1 = %p, p2 = %p, *p1 = %d, *p2 = %d\n", p1, p2, *p1, *p2);
}

void pointerDemo3() {
    int num = 5, *p = &num;
    if (*p == 5) printf("true\n");
}

void swapInt(int *p, int *q) {
    int temp = *p;
    printf("[swapInt] p = %p, q = %p, &temp = %p\n", p, q, &temp);
    *p = *q;
    printf("[swapInt] p = %p, q = %p, &temp = %p\n", p, q, &temp);
    *q = temp;
    printf("[swapInt] p = %p, q = %p, &temp = %p\n", p, q, &temp);
}

void swapIntTest() {
    int a = 5, b = 10;
    printf("[swapIntTest] &a = %p, &b = %p\n", &a, &b);
    swapInt(&a, &b);
    printf("[swapIntTest] &a = %p, &b = %p\n", &a, &b);
    printf("a = %d, b = %d\n", a, b);
}
