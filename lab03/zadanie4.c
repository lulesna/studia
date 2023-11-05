/*Napisz program który pobierze od użytkownika trzy liczby i zwróci największą liczbę.*/

#include <stdio.h>

int main() 
{
    int liczba1, liczba2, liczba3;

    printf("Podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("Podaj drugą liczbę: ");
    scanf ("%d", &liczba2);
    printf("Podaj trzecią liczbę: ");
    scanf ("%d", &liczba3);

    if (liczba1 > liczba2 && liczba1 > liczba3) {
        printf("Największa jest pierwsza liczba.\n");
    }
    else if (liczba2 > liczba3) {
        printf("Największa jest druga liczba.\n");
    }
    else {
        printf("Największa jest trzecia liczba.\n");
    }

    return 0;
}