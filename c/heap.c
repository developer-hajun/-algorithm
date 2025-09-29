#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void downHeap(int arr[], int heapSize, int i) {
    int largest = i; 
    int left = 2 * i + 1; 
    int right = 2 * i + 2; 

    if (left < heapSize && arr[left] > arr[largest])
        largest = left;


    if (right < heapSize && arr[right] > arr[largest])
        largest = right;


    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
        
        downHeap(arr, heapSize, largest);
    }
}

void heapSort(int arr[], int n) {
    for (int i = n / 2 - 1; i >= 0; i--)
        downHeap(arr, n, i);

    int heapSize = n; // 힙의 크기
    for (int i = 0; i < n - 1; i++) { // 수정된 부분
        // 루트와 힙의 마지막 노드 교환
        int temp = arr[0];
        arr[0] = arr[heapSize - 1]; // 마지막 노드와 교환
        arr[heapSize - 1] = temp;

        heapSize--; // 힙의 크기 1 감소
        downHeap(arr, heapSize, 0); // 위배된 힙 조건을 만족시킴
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
    
    
    int value2[] = {40, 80, 60, 50, 30, 70, 10, 20};
    heapSort(value2, 8);
    
    
    
    fp = fopen("heap_output.txt", "w");
    for(int i=0;i<n;i++){
        fprintf(fp, "%d\n", value[i]);
    }
    clock_t end = clock();
    printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);
}
