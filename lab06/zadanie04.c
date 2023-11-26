/*Napisz funkcję rekurencyjną obliczający n-ty wyraz ciągu Fibonacciego.*/

#include <stdio.h>

int fibonacci(int n)
{
    if (n <= 1) {
       return n;
    }
    else {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

int main() 
{
    int n;

    printf("Podaj n: ");
    scanf("%d", &n);

    printf("Wyraz %d. ciągu Fibonacciego: %d\n", n, fibonacci(n));

    return 0;
}