/*Napisz program który dla dowolnej wartości kąta w stopniach zwróci wartość tego kąta w radianach.*/

#include <stdio.h>

int main() 
{
    float stopnie, radiany;

    printf("Podaj wartość kąta w stopniach: ");
    scanf("%f", &stopnie);

    radiany = stopnie / 180;

    printf("%.0f stopni to %.2f Pi radianów.\n", stopnie, radiany);

    return 0;
}