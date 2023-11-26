/*Napisz funkcję rekurencyjną która dla posortowanej tablicy liczb naturalnych i zadanej liczby sprawdzi, czy liczba jest elementem tej tablicy:

a) przeszukaj tablicę od lewej do prawej strony

b) skorzystaj z przeszukiwania binarnego*/

#include <stdio.h>
#include <stdbool.h>

bool a(int tablica[], int n, int x, int indeks)
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
    int n, tablica[];

    printf("Podaj liczbę: ");
    scanf("%d", &n);

    printf("Suma cyfr liczby %d wynosi: %d\n", n, suma_cyfr(n));

    return 0;
}