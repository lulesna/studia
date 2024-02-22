/*Napisz program, który wyświetli na ekranie prostokąt. Program powinien obsługiwać 4 parametry:

    znak - decydujący o tym jakim znakiem będziemy rysować prostokąt
    czy_pusty - decydujący o tym czy prostokąt ma być wypełniony w środku
    wys - wysokość prostokąta
    szer - szerokość prostokąta

Wszystkie parametry powinny być odczytane z pliku conf.txt

Przykład:

conf.txt
znak = x
czy_pusty = 1
wys = 3
szer = 3

Wynik:

xxx
x x
xxx
*/

#include <stdio.h>

int main()
{
    char znak;
    int czy_pusty, wys, szer;

    FILE *plik = fopen("conf.txt", "r");
    if (plik == NULL) {
        printf("Plik jest pusty.\n");
        return 1;
    }

    fscanf(plik, "znak = %c\n", &znak);
    fscanf(plik, "czy_pusty = %d\n", &czy_pusty);
    fscanf(plik, "wys = %d\n", &wys);
    fscanf(plik, "szer = %d\n", &szer);

    for (int i = 0; i < wys; i++) {
        if (i == 0 || i == wys - 1 || czy_pusty == 0) {
            for (int j = 0; j < szer; j++) {
                printf("%c", znak);
            }
            printf("\n");
        }
        else {
            printf("%c", znak);
            for (int j = 0; j < szer - 2; j++) {
                printf(" ");
            }
            printf("%c\n", znak);
        }
    }

    fclose(plik);

    return 0;
}