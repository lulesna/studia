/*Napisz funkcję, która przyjmuje jako argument liczbę r (rok) i sprawdzi czy jest to rok przestępny.*/

#include <stdio.h>

int czy_rok_przestepny(int r)
{
    if ((r % 4 == 0 && r % 100 != 0) || (r % 400 == 0)) {
        printf("Rok %d jest przestępny.\n", r);
    }
    else {
        printf("Rok %d nie jest przestępny.\n", r);
    }
}

int main() 
{
    int rok;

    printf("Podaj rok: ");
    scanf ("%d", &rok);

    czy_rok_przestepny(rok);

    return 0;
}