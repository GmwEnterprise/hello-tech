#include <stdio.h>
#include <stdint.h>
#include <string.h>

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

    char fate = 'FATE'; // 只接收最右边字符
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

void newType() {
    // stdint.h 提供的32位int型
    int32_t intVal = 0x7fffffff, intVal2 = 0x8fffffff;
    int64_t longVal = 0x7fffffffffffffff;
    printf("%d, %lld\n", intVal, longVal);
    printf("%d\n", intVal2); // 由于不是关键字，没有ide的智能提示  但仍然溢出，所以打印出负数
}

void address() {
    int a = 1, b = 2;
    printf("address is %p and %p\n", &a, &b);
}

void floatingNumbers() {
    float f = 3.40282347E+39f;
    printf("f = %f\n", f); // 浮点上溢，打印出的 "1.#INF00" 意为无穷大（Infinity）

    float f2 = 8E5f;

}

// 第四章前导程序
#define DENSITY 62.4f

void talkback() {
    float weight, volume;
    int size, letters;
    char name[40]; // 占用内存中连续的40个字节 存储字符串
    printf("Hi! What's your first name?\n");
    scanf("%s", name);
    printf("%s, what's your weight in pounds ?\n", name);
    scanf("%f", &weight);
    size = sizeof name;
    letters = strlen(name);
    volume = weight / DENSITY;
    printf("Well, %s, your volume is %2.2f cubic feet.\n", name, volume);
    printf("Also, your first name has %d letters,\n", letters);
    printf("and we have %d bytes to store it.\n", size);
}

#undef DENSITY // 解除定义
#define PRAISE "You are an extraordinary being."

void praise1() {
    char name[40];
    printf("What's your name? :");
    scanf("%s", name); // 只能读取一个单词，无法读取第一个空白符及后面的内容
    printf("\nHello, %s. %s\n", name, PRAISE);

    // %zd 对应 size_t类型
    printf("Your name of %zd letters occupies %zd memory cells.\n", strlen(name), sizeof(name));
    printf("The phrase of praise has %zd letters ", strlen(PRAISE));
    printf("and occupies %zd memory cells.\n", sizeof(PRAISE));
}

#undef PRAISE

#include <limits.h>
#include <float.h>

void defines() {
    printf("Some number limits for this system:\n");
    printf("Biggest int: %d\n", INT_MAX);
    printf("Smallest long long: %lld\n", LLONG_MIN);
    printf("One byte = %d bits on this system.\n", CHAR_BIT);
    printf("Largest double: %e\n", DBL_MAX);
    printf("Smallest normal float: %e\n", FLT_MIN);
    printf("float precision = %d digits\n", FLT_DIG);
    printf("float epsilon = %e\n", FLT_EPSILON);
}

#define PI 3.141593

void printout() {
    int number = 7;
    float pies = 12.75f;
    int cost = 7800;
    printf("The %d contestants ate %f berry pies.\n", number, pies);
    printf("The value of PI is %f.\n", PI);
    printf("Farewell! thou art too dear for my possessing.\n");
    printf("%c%d\n", '$', 2 * cost);
}

#define PAGES 959

void width() {
    printf("*%d*\n", PAGES);
    printf("*%2d*\n", PAGES);
    printf("*%10d*\n", PAGES);
    printf("*%-10d*\n", PAGES);
    /**
     output:
        *959*
        *959*
        *       959*
        *959       *
     */
}

#undef PAGES
#define PAGES 336
#define WORDS 65618

void intconv() {
    short num = PAGES, mnum = -PAGES;
    printf("num as short and unsigned short: %hd %hu\n", num, num);
    printf("-num as short and unsigned short: %hd %hu\n", mnum, mnum);
    printf("num as int and char: %d %c\n", num, num);
    printf("WORDS as int, short, and char: %d %hd %c\n", WORDS, WORDS, WORDS);
}

void sizeofBasicType() {
    // 输出：`char(1) short(2) int(4) long(4) float(4) double(8) _Bool(1)`
    printf("size: char(%zd) short(%zd) int(%zd) long(%zd) float(%zd) double(%zd) _Bool(%zd)\n",
           sizeof(char), sizeof(short), sizeof(int), sizeof(long),
           sizeof(float), sizeof(double), sizeof(_Bool));
}

void floatcnv() {
    float n1 = 3.0f;
    double n2 = 3.0;
    long n3 = 2000000000;
    long n4 = 1234567890;
    printf("%.1e %.1e %.1e %.1e\n", n1, n2, n3, n4);
    printf("%ld %ld\n", n3, n4);

    // win10 x64环境中 long 长度为4字节，%ld 转义符表示转译 long 型变量，所以无法正确识别8字节浮点数
    printf("%ld %ld %ld %ld\n", n1, n2, n3, n4);
    printf("%lld %lld\n", n1, n2);
}

int main(void) {
    floatcnv();
    return 0;
}
