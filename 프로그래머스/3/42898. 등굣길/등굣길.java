class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map = new int[n][m];
        
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=0;
            }
        }
        for(int[] water : puddles) map[water[1]-1][water[0]-1]=123456789;
        map[0][0]=1;
        
        int[] dy={-1,0};
        int[] dx={0,-1};
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                
                if(map[i][j]!=0) continue;
                
                for(int e=0;e<2;e++){
                    int y = i+dy[e];
                    int x = j+dx[e];
                    if(y<0||x<0||map[y][x]==123456789) continue;
                    map[i][j]=(map[y][x]+map[i][j])%1000000007;
                }
            }
        }
        
        
        return map[n-1][m-1];
        
        
        
    }
}