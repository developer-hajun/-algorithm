#include <iostream>
#include <vector>
#include<string.h>
#include <queue>
using namespace std;

int main(){
    int map[101][101];
    int dist[101][101];
    int m,n; cin >> m >>n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            scanf("%1d",&map[i][j]);
            dist[i][j]=2000000000;
        }
    }
    
    int gx[]={0,0,-1,+1}; int gy[]={-1,+1,0,0};
    
    queue<pair<int,int>> save;
    dist[1][1]=0;
    save.push(make_pair(1,1));
    
    while(!save.empty()){
        int x = save.front().second; int y = save.front().first;
        save.pop();
        for(int i=0;i<4;i++){
            int nx = x+gx[i]; int ny=y+gy[i];
            if(nx<1||ny<1||nx>m||ny>n) continue;
            if(map[ny][nx]==1&&dist[ny][nx]>dist[y][x]+1){
                dist[ny][nx]=dist[y][x]+1;
                save.push(make_pair(ny,nx));
            }
            else if(map[ny][nx]==0&&dist[ny][nx]>dist[y][x]){
                dist[ny][nx]=dist[y][x];
                save.push(make_pair(ny,nx));
            }
        }
    }
    cout<<dist[n][m];
}