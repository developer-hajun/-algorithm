#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define INF	1000000

int main() {
    clock_t start = clock();

    printf("                 seoul     cheonan     nonsan    daejeon    gwangju      wonju    gangneung    daegu    pohang      busan  \n");
    int graph[10][10] = {
        {0,12,INF,INF,INF,15,INF,INF,INF,INF},
        {12,0,4,10,INF,INF,INF,INF,INF,INF},
        {INF,4,0,3,13,INF,INF,INF,INF,INF},
        {INF,10,3,0,INF,INF,INF,10,INF,INF},
        {INF,INF,13,INF,0,INF,INF,INF,INF,15},
        {15,INF,INF,INF,INF,0,21,7,INF,INF},
        {INF,INF,INF,INF,INF,21,0,INF,25,INF},
        {INF,INF,INF,10,INF,7,INF,0,19,9},
        {INF,INF,INF,INF,INF,INF,25,19,0,5},
        {INF,INF,INF,INF,15,INF,INF,9,5,0}
    };

    //����0,õ��1,���2,����3,����4,����5,����6,�뱸7,����8,�λ�9
    for (int i = 0; i < 10; i++) {
        char* planets[] = {"seoul","cheonan","nonsan","daejeon","gwangju","wonju","Gangneung","Daegu","Pohang","Busan"};

        int distance[10] = { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF };
        int visit[10] = { 0, };
        distance[i] = 0;
        int now = i;
        int value = 0;
        while (value != 10) {
            visit[now] = 1;
            value += 1;
            //�湮ó��
            for (int j = 0; j < 10; j++) {
                if (visit[j]) continue;
                if (graph[now][j] == INF) continue;
                int now_value = distance[now] + graph[now][j];
                if (distance[j] > now_value) {
                    distance[j] = now_value;
                }
            }
            //�׷��� ������Ʈ
            int v = INF;
            for (int j = 0; j < 10; j++) {
                if (visit[j]) continue;
                if (v < distance[j]) continue;
                v = distance[j];
                now = j;
            }

            //������ �����Ұ� ã��

        }
        printf("%10s ", planets[i]);
        for (int j = 0; j < 10; j++) {
            printf("%10d ", distance[j]);
        }
        printf("\n");
    }
    clock_t end = clock();
    printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);

}
