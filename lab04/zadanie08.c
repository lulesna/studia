/*Napisz program, który dla podanej pary liczb a,b zwróci b liczb pierwszych większych lub równych a.*/

#include <stdio.h>
#include <stdbool.h>

bool czypierwsza(int n) {
    if (n <= 1) {
        return false;
    }

    for (int i = 2; i * i <= n; ++i) {
        if (n % i == 0) {
            return false;
        }
    }

    return true;
}

int main()
{
    int a, b, n;
    
    printf("Podaj pierwszą liczbę: ");
    scanf("%d", &a);
    printf("Podaj drugą liczbę: ");
    scanf("%d", &b);

    n = a;

    for (int i = 1; i <= b; i++) {
        while (!(czypierwsza(n))) {
            n++;
        }
        printf("%d ", n);
        n++;
    }

    printf("\n");

    return 0;
}