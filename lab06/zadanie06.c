/*Napisz funkcję rekurencyjną obliczającą sumę cyfr podanej liczby.*/

#include <stdio.h>

int suma_cyfr(int n)
{
    if (n == 0) {
        return 0;
    }
    else {
        return n % 10 + suma_cyfr(n / 10);
    }
}

int main() 
{
    int n;

    printf("Podaj liczbę: ");
    scanf("%d", &n);

    printf("Suma cyfr liczby %d wynosi: %d\n", n, suma_cyfr(n));

    return 0;
}