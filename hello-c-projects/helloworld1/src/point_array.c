//
// Created by Gmw on 2021/5/29.
//

#include <stdio.h>
#include "point_array.h"

#define LEN 10

void printArray(__int64 *array, __int64 len) {
    for (__int64 i = len; i >= 0; i--) {
        printf("array[%lld] = [%p] (%lld)\n", i, array + i, *(array + i));
    }
}

void point_array() {
    __int64 start;
    __int64 array[LEN] = {155, 155, 155, 155, 155, 155, 155, 155, 155, 155};
    __int64 end;
    printf("    start = [%p] (%lld)\n", &start, start);
    printArray(array, LEN);
    printf("      end = [%p] (%lld)\n", &end, end);
}