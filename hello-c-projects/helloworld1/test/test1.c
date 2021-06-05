//
// Created by Gmw on 2021/5/6.
//

#include <stdio.h>
#include <string.h>

int main() {
    char str[5] = "hello";
    size_t len = strlen(str);
    for (int i = 0; i < len; i++) {
        printf("_%c_", str[i]);
    }
    return 0;
}