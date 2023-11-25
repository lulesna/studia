/*Napisz funkcję, która przyjmuje jako argument miesiąc i rok i zwróci liczbę dni danego miesiąca.*/

#include <stdio.h>

int liczba_dni(int m, int r)
{
    if (m >=1 && m <= 12) {
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            printf("Liczba dni tego miesiąca to 31.\n");
        }
        else if (m == 2) {
            if ((r % 4 == 0 && r % 100 != 0) || (r % 400 == 0)) {
                printf("Liczba dni tego miesiąca to 29.\n");
            } 
            else {
                printf("Liczba dni tego miesiąca to 28.\n");
            }
        }
        else {
            printf("Liczba dni tego miesiąca to 30.\n");
        }
    }
    else {
        printf("Podano niepoprawny miesiąc.\n");
    }
}

int main() 
{
    int miesiac, rok;

    printf("Podaj miesiąc: ");
    scanf ("%d", &miesiac);

    printf("Podaj rok: ");
    scanf ("%d", &rok);

    liczba_dni(miesiac, rok);

    return 0;
}