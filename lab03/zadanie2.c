#include <stdio.h>

int main() 
{
    int liczba1, liczba2;

    printf("podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("podaj drugą liczbę: ");
    scanf ("%d", &liczba2);

    int suma = liczba1 + liczba2;
    int roznica = liczba1 - liczba2;
    int iloczyn = liczba1 * liczba2;
    float iloraz = liczba1 / liczba2;
    int reszta_z_dzielenia = liczba1 % liczba2;

    printf("suma: %d\n", suma);
    printf("różnica: %d\n", roznica);
    printf("iloczyn: %d\n", iloczyn);
    printf("iloraz: %f\n", iloraz);
    printf("reszta z dzielenia: %d\n", reszta_z_dzielenia);

    return 0;
}