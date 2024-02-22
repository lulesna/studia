/*Napisz program, który dla podanej tablicy trójwymiarowej wypisze te elementy które są większe od swoich sąsiadów. Za sąsiadów elementu T[x][y][z] uznajemy te elementy, których współrzędne różnią się o co najwyżej 1 poza samym T[x][y][z].

Wykorzystaj zadania 5,6*/

#include <stdio.h>

int funkcja(unsigned int a1, unsigned int b1, unsigned int a2, unsigned int b2, unsigned int a3, unsigned int b3)
{
    if (b1 > a1 && b2 > a2) {
        if (b1 < a2 || b2 < a1) {
            printf("-1\n");
            return 1;
        }
        else if (a1 > a2) {
            a3 = a1;
            printf("%d\n", a3);
        }
        else {
            a3 = a2;
            printf("a3 = %d\n", a3);
        }
    }
    else {
        printf("Błędne dane, b1 powinno być większe od a1, i b2 powinno być większe od a2.\n");
    }
}

int main() 
{
    unsigned int a1, b1, a2, b2, a3, b3;

    printf("Podaj cztery liczby naturalne oddzielone spacjami (a1 b1 a2 b2): ");
    scanf("%u %u %u %u", &a1, &b1, &a2, &b2);

    funkcja(a1, b1, a2, b2, a3, b3);

    return 0;
}