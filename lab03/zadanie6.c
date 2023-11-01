#include <stdio.h>

int main() 
{
    int rok;

    printf("podaj rok: ");
    scanf ("%d", &rok);

    if ((rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0)) {
        printf("rok %d jest przestępny\n", rok);
    }
    else {
        printf("rok %d nie jest przestępny\n", rok);
    }

    return 0;
}