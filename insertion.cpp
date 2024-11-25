#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    clock_t start = clock();
    FILE* fp = fopen("input.txt", "r");

    int* value = NULL;
    int cap = 100;
    value = (int*)malloc(cap * sizeof(int));

    int n = 0;

    while (fscanf(fp, "%d", &value[n]) != EOF) {
        n++;
        if (n >= cap) {
            cap *= 2;
            value = (int*)realloc(value, cap * sizeof(int));
        }
    }
    fclose(fp);
    
    for(int i=1;i<n;i++){
        int current = value[i];
        int j = i-1;
        while (j>=0 and value[j] > current){
            int temp = value[j];
            value[j]=value[j+1];
            value[j+1]=temp;
            j-=1;
        }
        value[j+1] = current;
    }
    
    
    fp = fopen("insertion_output.txt", "w");
    for(int i=0;i<n;i++){
        fprintf(fp, "%d\n", value[i]);
    }
    clock_t end = clock();
    printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);
}
