/*Za pomocą funkcji rekurencyjnej napisz program, który dla podanej liczby dziesiętnej wypisze na ekren tę liczbę w postaci binarnej.*/

#include <stdio.h>

int binarna(int n)
{
    if (n == 0) {
       return 0;
    }
    else {
        return (n % 2 + 10 * binarna(n / 2));
    }
}

int main() 
{
    int n;

    printf("Podaj liczbę: ");
    scanf("%d", &n);

    printf("Liczba %d w postaci binarnej: %d\n", n, binarna(n));

    return 0;
}