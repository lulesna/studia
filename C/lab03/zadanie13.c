/*Napisz program, który dla wybranych kątów wyrażonych w radianach wypisze wartości tych kątów w stopniach.*/

#include <stdio.h>

int main() 
{
    int odp;

    printf("Dla 0 radianów wybierz 1.\nDla 1/6 Pi rad wybierz 2.\nDla 1/4 Pi rad wybierz 3.\nDla 1/3 Pi rad wybierz 4.\nDla 1/2 Pi rad wybierz 5.\n");
    scanf("%d", &odp);

    if (odp == 1) {
        printf("0 radianów to 0 stopni.\n");
    }
    else if (odp == 2) {
        printf("1/6 Pi radianów to 30 stopni.\n");
    }
    else if (odp == 3) {
        printf("1/4 Pi radianów to 45 stopni.\n");
    }
    else if (odp == 4) {
        printf("1/3 Pi radianów to 60 stopni.\n");
    }
    else if (odp == 5) {
        printf("1/2 Pi radianów to 90 stopni.\n");
    }
    else {
        printf("Wybierz opcję od 1 do 5.\n");
    }

    return 0;
}