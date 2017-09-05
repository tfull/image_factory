#include <stdio.h>
#include <stdlib.h>

#define SIZE 256

typedef struct{
    int red;
    int green;
    int blue;
} Color;

int **getEmptyMat(int, int);
void makeMat(char*, Color, Color);
void draw(int**);
void freeMat(int**, int);
void makeSpace(char*, Color);

int main(void){
    Color white = { 255, 255, 255 };
    Color black = { 0, 0, 0 };
    makeMat("white.ppm", white, black);
    makeMat("black.ppm", black, white);
    makeSpace("white_space.ppm", white);
    makeSpace("black_space.ppm", black);

    return 0;
}

int **getEmptyMat(int width, int height){
    int i, j;
    int **table;

    table = (int**)malloc(sizeof(int*) * SIZE);
    for(i = 0; i < height; i++){
        table[i] = (int*)malloc(sizeof(int) * SIZE);
        for(j = 0; j < width; j++){
            table[i][j] = 0;
        }
    }
    return table;
}

void freeMat(int **table, int height){
    int i, j;
    for(i = 0; i < height; i++){
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
    int width, height;
    FILE *fp;

    width = SIZE;
    height = SIZE;

    table = getEmptyMat(width, height);
    draw(table);

    fp = fopen(path, "w");
    fprintf(fp, "P3\n");
    fprintf(fp, "%d %d\n", width, height);
    fprintf(fp, "255\n");
    for(i = 0; i < height; i++){
        for(j = 0; j < width; j++){
            if(table[i][j] == 0){
                fprintf(fp, "%d %d %d\n", plain.red, plain.green, plain.blue);
            }else{
                fprintf(fp, "%d %d %d\n", color.red, color.green, color.blue);
            }
        }
    }
    fclose(fp);
    freeMat(table, height);
}

void makeSpace(char *path, Color color){
    int i, j;
    int width, height;
    FILE *fp;

    width = SIZE / 2;
    height = SIZE;

    fp = fopen(path, "w");
    fprintf(fp, "P3\n");
    fprintf(fp, "%d %d\n", width, height);
    fprintf(fp, "255\n");
    for(i = 0; i < height; i++){
        for(j = 0; j < width; j++){
            fprintf(fp, "%d %d %d\n", color.red, color.green, color.blue);
        }
    }
    fclose(fp);
}
