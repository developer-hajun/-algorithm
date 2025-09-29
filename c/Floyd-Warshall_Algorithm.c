#include <stdio.h>

#define INF	1000000

int main()
{
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

    for (int k=0;k<10;k++){
        for(int i=0;i<10;i++){
            if(i==k) continue;
            for(int j=0;j<10;j++){
                if(j==k || i==j) continue;
                if(graph[i][j]>graph[i][k]+graph[k][j]) graph[i][j] = graph[i][k]+graph[k][j];
            }
        }
    }
    char* planets[] = {"seoul","cheonan","nonsan","daejeon","gwangju","wonju","Gangneung","Daegu","Pohang","Busan"};
    printf("                 seoul     cheonan     nonsan    daejeon    gwangju      wonju    gangneung    daegu    pohang      busan  \n");
    for(int i=0;i<10;i++){
        printf("%10s ", planets[i]);
        for (int j = 0; j < 10; j++) {
            printf("%10d ", graph[i][j]);
        }
        printf("\n");
    }

    return 0;

}
