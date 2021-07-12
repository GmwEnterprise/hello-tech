//
// Created by Gmw on 2021/5/29.
//

#include <stdio.h>
#include "point_array.h"

#define LEN 10

void printIntArray(__int32 *array, __int32 len) {
    for (__int32 index = 0; index < len; index++) {
        printf("array[%d] = [%p] (%d)\n", index, array + index, *(array + index));
    }
}

void printLongArray(__int64 *array, __int64 len) {
    for (__int64 i = len; i >= 0; i--) {
        printf("array[%lld] = [%p] (%lld)\n", i, array + i, *(array + i));
    }
}

void point_array() {
    __int64 start;
    __int64 array[LEN] = {155, 155, 155, 155, 155, 155, 155, 155, 155, 155};
    __int64 end;
    printf("    start = [%p] (%lld)\n", &start, start);
    printLongArray(array, LEN);
    printf("      end = [%p] (%lld)\n", &end, end);
}

void array_change_value() {
    __int32 arr[LEN] = {1, 2, 3};
    printf("%d\n", arr[2]);
    arr[2] = 100;
    printf("%d\n", arr[2]);
}

void array_init() {
    __int32 arr[LEN] = {};
    printIntArray(arr, LEN);
}

void array_sizeof() {
    __int32 arr[] = {10, 20, 30, 40, 50, 60, 70};
    for (__int32 i = 0; i < sizeof arr / sizeof arr[0]; i++) {
        printf("%d _ ", arr[i]);
    }
}
