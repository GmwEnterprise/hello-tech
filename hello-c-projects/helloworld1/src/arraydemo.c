//
// Created by Gmw on 2021/5/29.
//
#include <stdio.h>
#include "arraydemo.h"

#define MONTHS 12

#define SIZE 8

void day_mon1() {
    // 声明了一个不可变数组
    const int days[MONTHS] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    for (int index = 0; index < MONTHS; index++) {
        printf("Month %2d has %2d days.\n", index + 1, days[index]);
    }
}

void no_data_32() {
    __int32 no_data[SIZE];
    printf("&no_data_32 = %p\n", &no_data);
    for (int idx = 0; idx < SIZE; idx++) {
        printf("no_data_32[%d] = [%p]%d\n", idx, &no_data[idx], no_data[idx]);
    }
}

void no_data_64() {
    __int64 no_data[SIZE];
    printf("&no_data_64 = %p, no_data_64 = %lld\n", &no_data, *(no_data + 1));
    for (int idx = 0; idx < SIZE; idx++) {
        printf("no_data_64[%d] = [%p]%lld\n", idx, &no_data[idx], no_data[idx]);
    }

    __int32 source[SIZE];
    source[0] = 250;
    source[1] = 25000;
    __int32 *point = source;
    printf("%p\n", point);
    printf("%d\n", *point);
    printf("%d\n", *(source + 1));
}

#define ARRAY1_LEN 10
void array_addr_in_memory() {
    __int32 array1[ARRAY1_LEN] = {100, 200, 300};
    for(__int32 i = 0; i < ARRAY1_LEN; i++) {
        printf("array1[%2d] = [%p] %d\n", i, &array1[i], array1[i]);
    }
}
