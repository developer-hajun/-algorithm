#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define NUM_POINTS 8
#define POP_SIZE 8
#define CROSSOVER_RATIO 1.0
#define MUTATION_RATIO 0.01
#define GENERATIONS 1000

typedef struct {
    int x, y;
} Point;

Point points[NUM_POINTS] = {
    {0, 3}, {7, 5}, {6, 0}, {4, 3}, {1, 0}, {5, 3}, {4, 1}, {2, 2}
};

char labels[NUM_POINTS] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };

double distance(Point a, Point b) {
    return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}

double total_distance(int route[]) {
    double dist = 0.0;
    for (int i = 0; i < NUM_POINTS - 1; i++) {
        dist += distance(points[route[i]], points[route[i + 1]]);
    }
    dist += distance(points[route[NUM_POINTS - 1]], points[route[0]]);
    return dist;
}

double average_distance(double fitness[], int size) {
    double sum = 0.0;
    for (int i = 0; i < size; i++) {
        sum += fitness[i];
    }
    return sum / size;
}

void initialize_population(int population[POP_SIZE][NUM_POINTS]) {
    for (int i = 0; i < POP_SIZE; i++) {
        int available[NUM_POINTS];
        for (int j = 0; j < NUM_POINTS; j++) {
            available[j] = j;
        }
        population[i][0] = 0; 
        for (int j = 1; j < NUM_POINTS; j++) {
            int r = rand() % (NUM_POINTS - j) + j;
            int temp = available[j];
            available[j] = available[r];
            available[r] = temp;
            population[i][j] = available[j];
        }
    }
}
void crossover(int parent1[], int parent2[], int child1[], int child2[]) {
    int start = rand() % NUM_POINTS;
    int end = rand() % NUM_POINTS;

    if (start > end) {
        int temp = start;
        start = end;
        end = temp;
    }

    // Copy the segment from parent1 to child1
    for (int i = 0; i < NUM_POINTS; i++) {
        if (i >= start && i <= end) {
            child1[i] = parent1[i];
        } else {
            child1[i] = -1;
        }
    }

    // Fill the rest from parent2
    int current = 0;
    for (int i = 0; i < NUM_POINTS; i++) {
        if (child1[i] == -1) {
            while (1) {
                int is_in_child = 0;
                for (int j = 0; j < NUM_POINTS; j++) {
                    if (child1[j] == parent2[current]) {
                        is_in_child = 1;
                        break;
                    }
                }
                if (!is_in_child) {
                    child1[i] = parent2[current];
                    current++;
                    break;
                }
                current++;
            }
        }
    }

    // Repeat the same for child2
    for (int i = 0; i < NUM_POINTS; i++) {
        if (i >= start && i <= end) {
            child2[i] = parent2[i];
        } else {
            child2[i] = -1;
        }
    }

    current = 0;
    for (int i = 0; i < NUM_POINTS; i++) {
        if (child2[i] == -1) {
            while (1) {
                int is_in_child = 0;
                for (int j = 0; j < NUM_POINTS; j++) {
                    if (child2[j] == parent1[current]) {
                        is_in_child = 1;
                        break;
                    }
                }
                if (!is_in_child) {
                    child2[i] = parent1[current];
                    current++;
                    break;
                }
                current++;
            }
        }
    }
}


void mutate(int route[]) {
    if ((double)rand() / RAND_MAX < MUTATION_RATIO) {
        int a, b;
        do {
            a = rand() % (NUM_POINTS - 1) + 1;
            b = rand() % (NUM_POINTS - 1) + 1;
        } while (a == b);
        int temp = route[a];
        route[a] = route[b];
        route[b] = temp;
    }
}

void genetic_algorithm() {
    int population[POP_SIZE][NUM_POINTS];
    int new_population[POP_SIZE][NUM_POINTS];
    double fitness[POP_SIZE];
    double previous_avg = 99999999;

    initialize_population(population);

    for(;;){
        
        for (int i = 0; i < POP_SIZE; i++) {
            fitness[i] = total_distance(population[i]);
        }

        double current_avg = average_distance(fitness, POP_SIZE);
        
        if (previous_avg <= current_avg) {
            //printf("Previous avg=%.2f, Current avg=%.2f\n", previous_avg, current_avg);
            break;
        }

        previous_avg = current_avg;

        for (int i = 0; i < POP_SIZE; i += 2) {
            int parent1 = rand() % (POP_SIZE / 2);
            int parent2 = rand() % (POP_SIZE / 2);
            crossover(population[parent1], population[parent2], new_population[i], new_population[i + 1]);
        }
        mutate(new_population[ rand() % (NUM_POINTS - 1) + 1]);

        for (int i = 0; i < POP_SIZE; i++) {
            for (int j = 0; j < NUM_POINTS; j++) {
                population[i][j] = new_population[i][j];
            }
        }
    }
    int index = -1;
    double value = 9999999;
    
    for (int i =0;i<8;i++){
        double v = total_distance(population[i]);
        if (v<value) {
            index=i;
            value = v;
        }
    }
    printf("이동순서: [");
    for (int i = 0; i < NUM_POINTS; i++) {
        printf("%c ", labels[population[index][i]]);
    }
    printf("%c], 이동 거리: %.2f\n", labels[population[index][0]], value);
}

int main() {
    
    srand(time(NULL));
    genetic_algorithm();
    return 0;
}
