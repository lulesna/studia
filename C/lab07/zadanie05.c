/*Napisz funkcję która przyjmuje wskaźniki na trzy liczby a, b, c posortuje wartości tych liczb rosnąco.*/

#include <stdio.h>

int sortowanie(int *a, int *b, int *c)
{
    if (*a > *b) {
        int temp = *a;
        *a = *b;
        *b = temp;
    }

    if (*b > *c) {
        int temp = *b;
        *b = *c;
        *c = temp;
    
        if (*a > *b) {
            int temp = *a;
            *a = *b;
            *b = temp;
        }
    }
}

int main() 
{
    int a, b, c;

    printf("Podaj trzy liczby oddzielone spacjami: ");
    scanf("%d %d %d", &a, &b, &c);

    sortowanie(&a, &b, &c);

    printf("Po sortowaniu: %d %d %d\n", a, b, c);

    return 0;
}