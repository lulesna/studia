/*Napisz program, który dla podanej liczby naturalnej n zwróci sumę kwadratów liczb od 1 do n.*/

#include <stdio.h>

int main()
{
    unsigned int n, suma_kwadratow = 0;
    
    printf("Podaj liczbę naturalną: ");
    scanf("%u", &n);

    for (int i = 1; i <= n; i++) {
        suma_kwadratow += i * i;
    }

    printf("Suma kwadratów od 1 do %u wynosi: %u\n", n, suma_kwadratow);

    return 0;
}