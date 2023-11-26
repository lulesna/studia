/*Napisz funkcję rekurencyjną obliczającą n!.*/

#include <stdio.h>

int silnia(int n)
{
    if (n <= 1) {
        return 1;
    }
    else {
        return n * silnia(n - 1);
    }
}

int main() 
{
    int n;

    printf("Podaj liczbę: ");
    scanf("%d", &n);

    printf("%d! = %d\n", n, silnia(n));

    return 0;
}