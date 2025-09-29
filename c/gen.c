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
    int cycle[NUM_POINTS] = { 0 };
    int visited[NUM_POINTS] = { 0 };
    int start = 1;
    int current = parent1[start];

    while (!visited[current]) {
        cycle[current] = 1;
        visited[current] = 1;
        current = parent1[current];
    }

    for (int i = 0; i < NUM_POINTS; i++) {
        if (cycle[i]) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }
        else {
            child1[i] = parent2[i];
            child2[i] = parent1[i];
        }
    }


    int used1[NUM_POINTS] = { 0 }, used2[NUM_POINTS] = { 0 };
    used1[0] = used2[0] = 1; 
    for (int i = 1; i < NUM_POINTS; i++) {
        while (used1[child1[i]]) {
            child1[i] = (child1[i] + 1) % NUM_POINTS;
        }
        used1[child1[i]] = 1;
        while (used2[child2[i]]) {
            child2[i] = (child2[i] + 1) % NUM_POINTS;
        }
        used2[child2[i]] = 1;
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

    initialize_population(population);

    for (int generation = 0; generation < GENERATIONS; generation++) {

        for (int i = 0; i < POP_SIZE; i++) {
            fitness[i] = total_distance(population[i]);
        }


        for (int i = 0; i < POP_SIZE - 1; i++) {
            for (int j = i + 1; j < POP_SIZE; j++) {
                if (fitness[i] > fitness[j]) {
                    double temp_fitness = fitness[i];
                    fitness[i] = fitness[j];
                    fitness[j] = temp_fitness;

                    int temp_route[NUM_POINTS];
                    for (int k = 0; k < NUM_POINTS; k++) {
                        temp_route[k] = population[i][k];
                        population[i][k] = population[j][k];
                        population[j][k] = temp_route[k];
                    }
                }
            }
        }


        for (int i = 0; i < POP_SIZE; i += 2) {
            int parent1 = rand() % (POP_SIZE / 2);
            int parent2 = rand() % (POP_SIZE / 2);
            crossover(population[parent1], population[parent2], new_population[i], new_population[i + 1]);
            mutate(new_population[i]);
            mutate(new_population[i + 1]);
        }


        for (int i = 0; i < POP_SIZE; i++) {
            for (int j = 0; j < NUM_POINTS; j++) {
                population[i][j] = new_population[i][j];
            }
        }
    }

    printf("이동순서: [");
    for (int i = 0; i < NUM_POINTS; i++) {
        printf("%c ", labels[population[0][i]]);
    }
    printf("%c],", labels[population[0][0]]);
    printf("이동 거리: %.2f\n", fitness[0]);
}

int main() {
    srand(time(NULL));
    genetic_algorithm();
    return 0;
}
