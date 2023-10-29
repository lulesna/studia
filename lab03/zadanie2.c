#include <stdio.h>

int main() {

    int liczba1, liczba2, suma, roznica, iloczyn, iloraz, reszta_z_dzielenia;

    printf("podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("podaj drugą liczbę: ");
    scanf ("%d", &liczba2);

    suma = liczba1 + liczba2;
    roznica = liczba1 - liczba2;
    iloczyn = liczba1 * liczba2;
    iloraz = liczba1 / liczba2;
    reszta_z_dzielenia = liczba1 % liczba2;

    printf("suma: %d\n", suma);
    printf("różnica: %d\n", roznica);
    printf("iloczyn: %d\n", iloczyn);
    printf("iloraz: %d\n", iloraz);
    printf("reszta z dzielenia: %d\n", reszta_z_dzielenia);

return 0;
}