//Napisz program, który odczyta zawartość pliku liczby.txt (w każdej linii pliku powinna znajdować się jedna liczba). Liczby parzyste powinny zostać przepisane do pliku parzyste.txt, nieparzyste do pliku nieparzyste.txt

#include <stdio.h>

int main()
{
    FILE *plik, *parzyste, *nieparzyste;
    int liczba;

    plik = fopen("liczby.txt", "r");
    parzyste = fopen("parzyste.txt", "w");
    nieparzyste = fopen("nieparzyste.txt", "w");

    if (plik == NULL) {
        printf("Plik jest pusty.\n");
        return 1;
    }

    while (fscanf(plik, "%d", &liczba) == 1) {
        if (liczba % 2 == 0) {
            fprintf(parzyste, "%d\n", liczba);
        } else {
            fprintf(nieparzyste, "%d\n", liczba);
        }
    }

    fclose(plik);
    fclose(parzyste);
    fclose(nieparzyste);

    return 0;
}