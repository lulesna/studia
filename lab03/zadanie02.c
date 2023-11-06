/*Napisz program który pobierze od użytkownika dwie liczby i zwróci wynik ich:
dodawaia
odejmowania
mnożenia
dzielenia
resztę z dzielenia*/

#include <stdio.h>

int main() 
{
    int liczba1, liczba2;

    printf("Podaj pierwszą liczbę: ");
    scanf("%d", &liczba1);
    printf("Podaj drugą liczbę: ");
    scanf("%d", &liczba2);

    int suma = liczba1 + liczba2;
    int roznica = liczba1 - liczba2;
    int iloczyn = liczba1 * liczba2;
    float iloraz = liczba1 / liczba2;
    int reszta_z_dzielenia = liczba1 % liczba2;

    printf("Suma: %d\n", suma);
    printf("Różnica: %d\n", roznica);
    printf("Iloczyn: %d\n", iloczyn);
    printf("Iloraz: %f\n", iloraz);
    printf("Reszta z dzielenia: %d\n", reszta_z_dzielenia);

    return 0;
}