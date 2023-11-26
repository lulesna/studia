/*Napisz funkcję rekurencyjną która dla podanej liczby n wypisze wszystkie liczby z całkowite z przedziału [1, 2, … ,n].*/

#include <stdio.h>

int liczby(int n)
{
    if (n > 0) {
        liczby(n-1);
        printf("%d ", n);
    }
}

int main() 
{
    int n;

    printf("Podaj liczbę: ");
    scanf("%d", &n);

    liczby(n);
    printf("\n");

    return 0;
}