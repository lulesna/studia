/*Napisz program, który wczyta od użytkownika parę liczb a,b i zwróci pierwszą liczbę c, taką że a do potęgi c jest większe od b.*/

#include <stdio.h>

int main()
{
    unsigned int a, b, c = 0, wynik = 1;
    
    printf("Podaj dwie liczby naturalne:\n");
    scanf("%u%u", &a, &b);

    while (wynik < b) {
        wynik *= a;
        c++;
    }

    printf("%u do potęgi %u jest większe od %u\n", a, c, b);

    return 0;
}