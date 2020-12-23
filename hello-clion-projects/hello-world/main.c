#include <stdio.h>

// 函数声明

// 测试
void func1(void);
// 复杂一点的函数
void platinum(void);
// 基本类型测试
void basicTypeTest();

int main(void) {
    basicTypeTest();
    return 0;
}

void func1(void) {
    int num;
    num = 1;
    printf("I am a simple ");
    printf("computer.\n");
    printf("My favorite number is %d because it is first.\n", num);

    int input = getchar(); // getchar方法返回的是字符对应的ascii编码的int值
    printf("Your input is \"%c\" and the ascii int value is \"%d\".", input, input);
}

void platinum(void) {
    float weight, value;
    printf("Are you worth your weight in platinum?\n");
    printf("Let's check it out.\n");
    printf("Please enter your weight in pounds: ");
    // & 符号用于找到weight的内存地址
    scanf("%f", &weight);
    getchar(); // 用于接收scanf函数输入完后余留的回车符，因为回车符也会被存入键盘缓冲区中
    value = 1700.0 * weight * 14.5833f;
    printf("Your weight in platinum is worth $%.2f.\n", value);
    printf("You are easily worth that! If platinum prices drop,\n");
    printf("eat more to maintain your value.\n");
    // 停住程序
    getchar();
}

void basicTypeTest() {
    unsigned char ch = 256;
    printf("ch = %d", ch);
    double d = 3.5E-8;
}