/*Napisz funkcję, która przyjmuje napis s i liczbę i, funkcja powinna zwracać i-ty znak napisu s.*/

#include <stdio.h>
#define SIZE 50

void funkcja(char s[], int i) {
    printf("%c\n", s[i]);
}

int main() 
{
    char s[SIZE];
    int i;

    printf("Podaj napis: ");
    scanf("%s", s);
    printf("Podaj liczbę: ");
    scanf("%d", &i);

    funkcja(s, i);

    return 0;
}