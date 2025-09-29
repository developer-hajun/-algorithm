#include <stdio.h>

#define CAPACITY 10
#define SIZE 8

void first_fit(int sizes[], int n) {
    int bins[SIZE][SIZE] = { 0 }; // 각 bin에 들어간 항목을 저장
    int bin_sizes[SIZE] = { 0 }; // 각 bin에 들어간 항목 개수
    int bin_count = 0;

    for (int i = 0; i < n; i++) {
        int j;
        for (j = 0; j < bin_count; j++) {
            int sum = 0;
            for (int k = 0; k < bin_sizes[j]; k++) {
                sum += bins[j][k];
            }
            if (sum + sizes[i] <= CAPACITY) {
                bins[j][bin_sizes[j]++] = sizes[i];
                break;
            }
        }
        if (j == bin_count) {
            bins[bin_count][bin_sizes[bin_count]++] = sizes[i];
            bin_count++;
        }
    }

    printf("Output (First Fit): ");
    for (int i = 0; i < bin_count; i++) {
        printf("bin%d = [", i + 1);
        for (int j = 0; j < bin_sizes[i]; j++) {
            printf("%d", bins[i][j]);
            if (j < bin_sizes[i] - 1) {
                printf(", ");
            }
        }
        printf("] ");
    }
    printf("\n");
}

void next_fit(int sizes[], int n) {
    int bins[SIZE][SIZE] = { 0 };
    int bin_sizes[SIZE] = { 0 };
    int bin_count = 0;
    int sum = 0;

    for (int i = 0; i < n; i++) {
        if (sum + sizes[i] <= CAPACITY) {
            bins[bin_count][bin_sizes[bin_count]++] = sizes[i];
            sum += sizes[i];
        }
        else {
            bin_count++;
            bins[bin_count][bin_sizes[bin_count]++] = sizes[i];
            sum = sizes[i];
        }
    }

    printf("Output (Next Fit): ");
    for (int i = 0; i <= bin_count; i++) {
        printf("bin%d = [", i + 1);
        for (int j = 0; j < bin_sizes[i]; j++) {
            printf("%d", bins[i][j]);
            if (j < bin_sizes[i] - 1) {
                printf(", ");
            }
        }
        printf("] ");
    }
    printf("\n");
}

void best_fit(int sizes[], int n) {
    int bins[SIZE][SIZE] = { 0 };
    int bin_sizes[SIZE] = { 0 };
    int bin_count = 0;

    for (int i = 0; i < n; i++) {
        int best_index = -1;
        int min_space = CAPACITY + 1;

        for (int j = 0; j < bin_count; j++) {
            int sum = 0;
            for (int k = 0; k < bin_sizes[j]; k++) {
                sum += bins[j][k];
            }
            int space = CAPACITY - sum;
            if (space >= sizes[i] && space < min_space) {
                min_space = space;
                best_index = j;
            }
        }

        if (best_index == -1) {
            bins[bin_count][bin_sizes[bin_count]++] = sizes[i];
            bin_count++;
        }
        else {
            bins[best_index][bin_sizes[best_index]++] = sizes[i];
        }
    }

    printf("Output (Best Fit): ");
    for (int i = 0; i < bin_count; i++) {
        printf("bin%d = [", i + 1);
        for (int j = 0; j < bin_sizes[i]; j++) {
            printf("%d", bins[i][j]);
            if (j < bin_sizes[i] - 1) {
                printf(", ");
            }
        }
        printf("] ");
    }
    printf("\n");
}

void worst_fit(int sizes[], int n) {
    int bins[SIZE][SIZE] = { 0 };
    int bin_sizes[SIZE] = { 0 };
    int bin_count = 0;

    for (int i = 0; i < n; i++) {
        int worst_index = -1;
        int max_space = -1;

        for (int j = 0; j < bin_count; j++) {
            int sum = 0;
            for (int k = 0; k < bin_sizes[j]; k++) {
                sum += bins[j][k];
            }
            int space = CAPACITY - sum;
            if (space >= sizes[i] && space > max_space) {
                max_space = space;
                worst_index = j;
            }
        }

        if (worst_index == -1) {
            bins[bin_count][bin_sizes[bin_count]++] = sizes[i];
            bin_count++;
        }
        else {
            bins[worst_index][bin_sizes[worst_index]++] = sizes[i];
        }
    }

    printf("Output (Worst Fit): ");
    for (int i = 0; i < bin_count; i++) {
        printf("bin%d = [", i + 1);
        for (int j = 0; j < bin_sizes[i]; j++) {
            printf("%d", bins[i][j]);
            if (j < bin_sizes[i] - 1) {
                printf(", ");
            }
        }
        printf("] ");
    }
    printf("\n");
}

int main() {
    int sizes[SIZE] = { 7, 5, 6, 4, 2, 3, 7, 5 };

    first_fit(sizes, SIZE);
    next_fit(sizes, SIZE);
    best_fit(sizes, SIZE);
    worst_fit(sizes, SIZE);

    return 0;
}
