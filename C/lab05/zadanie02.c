/*Napisz funkcję typu int która przyjmuje jako argumenty dwa znaki i sprawdza czy są identyczne. Dla identycznych znaków funkcja powinna zwracać 1, w przeciwnym przypadku 0.*/

#include <stdio.h>

int funkcja(char znak1, char znak2) {
    if (znak1 == znak2) {
        return 1;
    }
    else {
        return 0;
    }
}

int main() 
{
    char znak1, znak2;

    printf("Podaj pierwszy znak: ");
    scanf("%c", &znak1);
    printf("Podaj drugi znak: ");
    scanf(" %c", &znak2);

    if (funkcja(znak1, znak2) == 1) {
        printf("Podane znaki są identyczne.\n");
    }
    else {
        printf("Podane znaki są różne.\n");
    }

    return 0;
}