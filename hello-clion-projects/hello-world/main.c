#include <stdio.h>

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

void zhuanYi() {
    // 可以使用 '\x41' 来表示一个ascii字符，41 为ascii码的十六进制表示，这里会打印出字符 A
    printf("\x41\n");

    /**
     * '&' 该符号的ascii值为38，八进制为 046，十六进制为 0x26
     * 所以可以这么打印出该符号
     */
    printf("\046 - \x26 - &\n"); // 输出：& - & - &
}

void basicTypeTest() {
    char c1 = 'A';
    int n1 = 'A', n2 = 65;
    printf("%c - %c - %c\n", c1, n1, n2);
    printf("%d - %d - %d\n", c1, n1, n2);

    char fate = 'FATE';
    int fateInt = 'FATE'; // 4个8位ASCII码存储在32位存储单元
    printf("%d - %c\n", fate, fate);
    printf("%d - %c\n", fateInt, fateInt);

    int word = 'XFATE'; // 这种方式无法识别超过4个字符，因为字面量默认为int，只能接纳32位值，超出的部分忽略高位
    long long wordLong = 'XFATE';
    printf("FATEO = %d - %lld\n", word, wordLong);

    int num1 = 0xffffffff;
    long long num2 = 0xffffffff;
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
    long long very_big = 12345678908642;

    // 打印符规则
    printf("un = %u and not %d\n", un, un);
    printf("end = %hd and not %d\n", end, end);
    printf("big = %ld and not %hd\n", big, big);
    printf("very_big = %lld and not %ld\n", very_big, very_big);

    // 打印字符
    char c1 = 'A';
    int c1Int = 65, c2Int = '1A';
    // 十进制65的十六进制为0x41
    // 字符'1'的ascii码为49，十六进制为0x31
    //   所以c2Int = '1A' 如果按照 %d 打印，将会被识别为 int，其十六进制值为0x3141，则打印出十进制值为12609
    printf("%c %c %c %d\n", c1, c1Int, c2Int, c2Int); // 输出：A A A 12609

    // 可以查阅文件 limits.h 以查看当前环境的整型值的表达范围
}

#include <stdint.h>

void newType() {
    // stdint.h 提供的32位int型
    int32_t intVal = 0x7fffffff, intVal2 = 0x8fffffff;
    int64_t longVal = 0x7fffffffffffffff;
    printf("%d, %lld\n", intVal, longVal);
    printf("%d\n", intVal2); // 由于不是关键字，没有ide的智能提示  但仍然溢出，所以打印出负数
}

int main(void) {
    newType();
    return 0;
}