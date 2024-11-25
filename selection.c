#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>



int change(int list[], int start, int end) {

    int pivot = (start + end) / 2;
    int value = list[pivot];

    while (start <= end) {
        while (list[start] < value) start += 1;
        while (list[end] > value) end -= 1;
        if (start <= end) {
            int v = list[start];
            list[start] = list[end];
            list[end] = v;
            start += 1;
            end -= 1;
        }
    }


    return start;

}

void q_sort(int list[], int start, int end) {
    if (start < end) {
        int pivot = change(list, start, end);
        q_sort(list, start, pivot - 1);
        q_sort(list, pivot, end);
    }

}
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
    q_sort(value,0,n);
    fp = fopen("selection_output.txt", "w");
    for(int i=1;i<=n;i++){
        fprintf(fp, "%d\n", value[i]);
    }
    clock_t end = clock();
    printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);
}
