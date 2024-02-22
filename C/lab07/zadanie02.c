/*Napisz program który zadeklaruje tablicę 10 elementową i wczyta jej elementy od użytkownika a następnie wypisze je na ekran. Do elementów tablicy odwołuj się za pomocą wskaźników.*/

#include <stdio.h>

int main() 
{
    int tablica[10];

    printf("Podaj elementy tablicy 10 elementowej:\n");
    for (int i = 0; i < 10; i++) {
        scanf("%d", &tablica[i]);
    }

    for (int i = 0; i < 10; i++) {
        printf("%p\n", &tablica[i]);
    }

    return 0;
}