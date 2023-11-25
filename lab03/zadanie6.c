/*Napisz program który pobierze od użytkownika rok i sprawdzi czy jest on przestępny.*/

#include <stdio.h>

int main() 
{
    int rok;

    printf("Podaj rok: ");
    scanf("%d", &rok);

    if ((rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0)) {
        printf("Rok %d jest przestępny.\n", rok);
    }
    else {
        printf("Rok %d nie jest przestępny.\n", rok);
    }

    return 0;
}