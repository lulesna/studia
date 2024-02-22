//Napisz program, który pobierze od użytkownika nazwę pliku i zamieni wszystkie małe litery z pliku na wielkie. Pozostałe znaki powinny pozostać bez zmian.

#include <stdio.h>
#include <ctype.h>

int main()
{
    char nazwa_pliku[100];
    char znak;

    printf("Podaj nazwę pliku: ");
    scanf("%s", nazwa_pliku);

    FILE *plik = fopen(nazwa_pliku, "r");

    while ((znak = fgetc(plik)) != EOF) {
        if (islower(znak)) {
            fseek(plik, -1, SEEK_CUR); // cofnięcie wskaźnika pliku o 1 bajt, SEEK_CUR - wskaźnik na aktualną pozycję
            fputc(toupper(znak), plik); // zamiana małej litery na wielką
        }
    }

    fclose(plik);

    return 0;
}