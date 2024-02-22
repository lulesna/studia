/*Napisz program, który pobierze od użytkownika dwie pary liczb reprezentujące przeciwległe wierzchołki prostokąta i na ich podstawie wyznaczy pole i obwód tego prostokąta.*/

#include <stdio.h>
#include <stdlib.h>

int main() 
{
    int x1, y1, x2, y2, a, b, p, l;

    printf("Podaj współrzędne pierwszego wierzchołka prostokąta:\n");
    scanf("%d %d", &x1, &y1);

    printf("Podaj współrzędne drugiego wierzchołka prostokąta:\n");
    scanf("%d %d", &x2, &y2);

    a = abs(x1 - x2);
    b = abs(y1 - y2);
    p = a * b;
    l = 2*a + 2*b;

    printf("Pole prostokąta wynosi %d, obwód wynosi %d.\n", p, l);

    return 0;
}