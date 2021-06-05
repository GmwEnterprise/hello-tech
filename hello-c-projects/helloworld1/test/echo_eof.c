#include <stdio.h>

int main(void) {
    int ch;

    // 直接在clion中执行是没办法使用 ctrl+d 读取到 EOF标志的
    // 在 wsl.exe 中通过linux命令来测试该程序
    while ((ch = getchar()) != EOF) {
        putchar(ch);
    }
    return 0;
}