/*Napisz program który pobierze od użytkownika dwie liczby i zwróci większą z nich.*/

#include <stdio.h>

int main() 
{
    int liczba1, liczba2;

    printf("Podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("Podaj drugą liczbę: ");
    scanf ("%d", &liczba2);

    if (liczba1 > liczba2) {
        printf("Większa jest pierwsza liczba.\n");
    }
    else {
        printf("Większa jest druga liczba.\n");
    }

    return 0;
}