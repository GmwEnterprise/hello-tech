#include <stdio.h>

int main(void) {
    int num;
    num = 1;
    printf("I am a simple ");
    printf("computer.\n");
    printf("My favorite number is %d because it is first.\n", num);

    int input = getchar(); // getchar方法返回的是字符对应的ascii编码的int值
    printf("Your input is \"%c\" and the ascii int value is \"%d\".", input, input);
    return 0;
}
