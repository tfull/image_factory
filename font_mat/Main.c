#include <stdio.h>
#include <stdlib.h>

#define SIZE 256

typedef struct{
    int red;
    int green;
    int blue;
} Color;

int **getEmptyMat(void);
void makeMat(char*, Color, Color);
void draw(int**);
void freeMat(int**);

int main(void){
    Color white = { 255, 255, 255 };
    Color black = { 0, 0, 0 };
    makeMat("white.ppm", white, black);
    makeMat("black.ppm", black, white);

    return 0;
}

int **getEmptyMat(void){
    int i, j;
    int **table;

    table = (int**)malloc(sizeof(int*) * SIZE);
    for(i = 0; i < SIZE; i++){
        table[i] = (int*)malloc(sizeof(int) * SIZE);
        for(j = 0; j < SIZE; j++){
            table[i][j] = 0;
        }
    }
    return table;
}

void freeMat(int **table){
    int i, j;
    for(i = 0; i < SIZE; i++){
        free(table[i]);
    }
    free(table);
}

void draw(int **table){
    int i, j;

    for(i = 0; i < SIZE / 4; i++){
        table[i][0] = 1;
        table[i][SIZE - 1] = 1;
    }
    for(i = 3 * SIZE / 4; i < SIZE; i++){
        table[i][0] = 1;
        table[i][SIZE - 1] = 1;
    }
    for(j = 0; j < SIZE / 4; j++){
        table[0][j] = 1;
        table[SIZE - 1][j] = 1;
    }
    for(j = 3 * SIZE / 4; j < SIZE; j++){
        table[0][j] = 1;
        table[SIZE - 1][j] = 1;
    }

    for(i = 7 * SIZE / 16; i < 9 * SIZE / 16; i++){
        table[i][SIZE / 2 - 1] = 1;
        table[i][SIZE / 2] = 1;
    }
    for(j = 7 * SIZE / 16; j < 9 * SIZE / 16; j++){
        table[SIZE / 2 - 1][j] = 1;
        table[SIZE / 2][j] = 1;
    }
}

void makeMat(char *path, Color plain, Color color){
    int i, j;
    int **table;
    FILE *fp;

    table = getEmptyMat();
    draw(table);

    fp = fopen(path, "w");
    fprintf(fp, "P3\n");
    fprintf(fp, "%d %d\n", SIZE, SIZE);
    fprintf(fp, "255\n");
    for(i = 0; i < SIZE; i++){
        for(j = 0; j < SIZE; j++){
            if(table[i][j] == 0){
                fprintf(fp, "%d %d %d\n", plain.red, plain.green, plain.blue);
            }else{
                fprintf(fp, "%d %d %d\n", color.red, color.green, color.blue);
            }
        }
    }
    fclose(fp);
}
