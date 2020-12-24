#include <stdio.h>

// 函数声明

// 测试
void func1(void);
// 复杂一点的函数
void platinum(void);
// 基本类型测试
void basicTypeTest();
// 打印变量的各种方法
void printVariables();

int main(void) {
    printVariables();
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
    unsigned char ch = 255;
    printf("ch = %d", ch);
    double d = 3.5E-8;
}

void printVariables() {
    int x = 100;
    // 十进制 八进制 十六进制
    printf("dec = %d, oct = %o, hex = %x\n", x, x, x);
    // 添加一个 # 号可以打印出八进制与十六进制的前缀
    printf("dec = %d, oct = %#o, hex = %#x\n", x, x, x);

    int i = 0x7FFFFFFF;
    unsigned int j = 0xFFFFFFFF;
    // %u 打印 unsigned int 类型，如果传递给 %u 的参数是有符号数，则会转换为无符号数
    // 数值溢出后会重新从起点开始
    printf("%d %d %d\n", i, i + 1, i + 2);
    printf("%u %u %u\n", j, j + 1, j + 2);

    unsigned int un = 3000000000;
    short end = 200;
    long big = 65537;
    long long verybig = 12345678908642;

    // 打印符规则
    printf("un = %u and not %d\n", un, un);
    printf("end = %hd and not %d\n", end, end);
    printf("big = %ld and not %hd\n", big, big);
    printf("verybig = %lld and not %ld\n", verybig, verybig);
}