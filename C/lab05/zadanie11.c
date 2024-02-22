/*Napisz funkcję, która przyjmuje jako argumenty liczby d,m,r i zwróci liczbę dni pozostałych do końca roku.*/

#include <stdio.h>

int ile_dni(int d, int m, int r)
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
    int dzien, miesiac, rok;

    printf("Podaj dzień: ");
    scanf("%d", &dzien);
    
    printf("Podaj miesiąc: ");
    scanf("%d", &miesiac);
    
    printf("Podaj rok: ");
    scanf("%d", &rok);

    ile_dni(dzien, miesiac, rok);

    return 0;
}