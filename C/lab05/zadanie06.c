/*Na podstawie zadania 5 napisz funkcję, która zwróci b3.*/

#include <stdio.h>

int funkcja(unsigned int a1, unsigned int b1, unsigned int a2, unsigned int b2, unsigned int b3)
{
    if (b1 > a1 && b2 > a2) {
        if (b1 < a2 || b2 < a1) {
            printf("-1\n");
            return 1;
        }
        else if (b1 < b2) {
            b3 = b1;
            printf("b3 = %d\n", b3);
        }
        else {
            b3 = b2;
            printf("b3 = %d\n", b3);
        }
    }
    else {
        printf("Błędne dane, b1 powinno być większe od a1, i b2 powinno być większe od a2.\n");
    }
}

int main() 
{
    unsigned int a1, b1, a2, b2, b3;

    printf("Podaj cztery liczby naturalne oddzielone spacjami (a1 b1 a2 b2): ");
    scanf("%u %u %u %u", &a1, &b1, &a2, &b2);

    funkcja(a1, b1, a2, b2, b3);

    return 0;
}