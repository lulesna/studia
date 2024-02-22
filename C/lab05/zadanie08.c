/*Napisz funkcję, która przyjmuje jako argumenty liczby d,m,r i sprawdzi, czy tworzą poprawną datę (d-dzień, m-miesiąc, r-rok).*/

#include <stdio.h>

int czy_poprawna_data(int d, int m, int r)
{
    if (m >=1 && m <= 12) {
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            if (d >= 1 && d <= 31) {
                printf("Data %d.%d.%d jest poprawna.\n", d, m, r);
            }   
            else {
                printf("Podano niepoprawny dzień.\n");
            }
        }
        else if (m == 2) {
            if (((r % 4 == 0 && r % 100 != 0) || (r % 400 == 0)) && (d >= 1 && d <= 29)) {
                printf("Data %d.%d.%d jest poprawna.\n", d, m, r);
            }
            else if ((d >= 1 && d <= 28)) {
                printf("Data %d.%d.%d jest poprawna.\n", d, m, r);
            } 
            else {
                printf("Podano niepoprawny dzień.\n");
            }
        }
        else {
            if (d >= 1 && d <= 30) {
                printf("Data %d.%d.%d jest poprawna.\n", d, m, r);
            }   
            else {
                printf("Podano niepoprawny dzień.\n");
            }
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

    czy_poprawna_data(dzien, miesiac, rok);

    return 0;
}