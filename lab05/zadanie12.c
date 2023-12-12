/*Napisz funkcję, która przyjmuje jako argumenty d1,m1,r1,d2,m2,r2 reprezentujące dwie daty i zwróci liczbę dni różniącą te daty.*/

#include <stdio.h>

int sprawdz_poprawnosc(int d1, int m1, int r1, int d2, int m2, int r2)
{
    if (m1 >=1 && m1 <= 12) {
        if (m1 == 1 || m1 == 3 || m1 == 5 || m1 == 7 || m1 == 8 || m1 == 10 || m1 == 12) {
            if (d1 < 1 && d1 > 31) {
                printf("Podano niepoprawny dzień w pierwszej dacie.\n");
            }
        }
        else if (m1 == 2) {
            if (((r1 % 4 == 0 && r1 % 100 != 0) || (r1 % 400 == 0)) && (d1 < 1 && d1 > 29)) {
                printf("Podano niepoprawny dzień w pierwszej dacie.\n");
            }
            else if ((d1 < 1 && d1 > 28)) {
                printf("Podano niepoprawny dzień w pierwszej dacie.\n");
            } 
        }
        else {
            if (d1 < 1 && d1 > 30) {
                printf("Podano niepoprawny dzień w pierwszej dacie.\n");
            }
        }
    }
    else {
        printf("Podano niepoprawny miesiąc w pierwszej dacie.\n");
    }

    if (m2 >=1 && m2 <= 12) {
        if (m2 == 1 || m2 == 3 || m2 == 5 || m2 == 7 || m2 == 8 || m2 == 10 || m2 == 12) {
            if (d2 < 1 && d2 > 31) {
                printf("Podano niepoprawny dzień w drugiej dacie.\n");
            }
        }
        else if (m2 == 2) {
            if (((r2 % 4 == 0 && r2 % 100 != 0) || (r2 % 400 == 0)) && (d2 < 1 && d2 > 29)) {
                printf("Podano niepoprawny dzień w drugiej dacie.\n");
            }
            else if ((d2 < 1 && d2 > 28)) {
                printf("Podano niepoprawny dzień w drugiej dacie.\n");
            } 
        }
        else {
            if (d2 < 1 && d2 > 30) {
                printf("Podano niepoprawny dzień w drugiej dacie.\n");
            }
        }
    }
    else {
        printf("Podano niepoprawny miesiąc w drugiej dacie.\n");
    }
}

int roznica_dni(int d1, int m1, int r1, int d2, int m2, int r2)
{
    if (m1 >=1 && m1 <= 12) {
        if (m2 >=1 && m2 <= 12) {
            
        }
        else {
            printf("Podano niepoprawny miesiąc w drugiej dacie.");
        }
    }
    else {
        printf("Podano niepoprawny miesiąc w pierwszej dacie.");
    }
}

int main() 
{
    int d1, m1, r1, d2, m2, r2;

    printf("Podaj dzień z pierwszej daty: ");
    scanf("%d", &d1);
    
    printf("Podaj miesiąc z pierwszej: ");
    scanf("%d", &m1);
    
    printf("Podaj rok z pierwszej daty: ");
    scanf("%d", &r1);

    printf("Podaj dzień z drugiej daty: ");
    scanf("%d", &d2);
    
    printf("Podaj miesiąc z drugiej: ");
    scanf("%d", &m2);
    
    printf("Podaj rok z drugiej daty: ");
    scanf("%d", &r2);

    return 0;
}