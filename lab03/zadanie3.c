#include <stdio.h>

int main() 
{
    int liczba1, liczba2;

    printf("podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("podaj drugą liczbę: ");
    scanf ("%d", &liczba2);

    if (liczba1 > liczba2) {
        printf("większa jest pierwsza liczba: %d", liczba1);
    }
    else {
        printf("większa jest druga liczba: %d", liczba2);
    }

    return 0;
}