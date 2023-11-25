/*Napisz funkcję, która przyjmuje jako argumenty cztery liczby naturalne a1,b1,a2,b2 reprezentujące odcinki [a1,b1] i [a2,b2]. Odcinek [a3,b3] reprezentuje przekrój odcinków [a1,b1],[a2,b2]. Jeśli przekrój nie jest zbiorem pustym zwróć a3, w przeciwnym wypadku zwróć -1.*/

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