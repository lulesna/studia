//Napisz program, który pobierze od użytkownika nazwę pliku i wypisze jego treść na ekranie.

#include <stdio.h>

int main()
{
    char nazwa_pliku[100];
    char znak;

    printf("Podaj nazwę pliku: ");
    scanf("%s", nazwa_pliku);

    FILE *plik = fopen(nazwa_pliku, "r"); // r - odczyt, w - zapis / utworzenie nowego pliku, a - dopisanie, r+ - odczyt / zapis istniejącego pliku, w+ - odczyt / zaopis / utworzenie nowego pliku, a+ - odczyt / dopis do istniejącego pliku
    if (plik == NULL) {
        printf("Plik jest pusty.\n");
        return 1;
    }

    // każdy znak odczytany z pliku jest przypisywany do zmiennej ch, a następnie jest wyświetlany na ekranie za pomocą funkcji putchar
    while ((znak = fgetc(plik)) != EOF) { // EOF - koniec pliku
        putchar(znak);
    }

    return 0;
}