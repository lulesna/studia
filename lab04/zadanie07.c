/*Napisz program, który dla podanej liczby n sprawdzi, czy n jest liczbą pierwszą.*/

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
    int n;
    
    printf("Podaj liczbę: ");
    scanf("%d", &n);

    if (czypierwsza(n)) {
        printf("%d jest liczbą pierwszą.\n", n);
    } else {
        printf("%d nie jest liczbą pierwszą.\n", n);
    }

    return 0;
}