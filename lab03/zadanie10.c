/*Napisz program, który pobierze od użytkownika długości trzech odcinków i sprawdzi, czy można z nich utworzyć trójkąt.*/

#include <stdio.h>

int main() 
{
    int a, b, c;

    printf("Podaj wartości trzech odcinków:\n");
    scanf("%d %d %d", &a, &b, &c);

    if ((a + b > c) && (a + c > b) && (b + c > a)) {
        printf("Trójkąt o bokach %d, %d, %d istnieje.\n", a, b, c);
    }
    else {
        printf("Trójkąt o bokach %d, %d, %d nie istnieje.\n", a, b, c);
    }

    return 0;
}