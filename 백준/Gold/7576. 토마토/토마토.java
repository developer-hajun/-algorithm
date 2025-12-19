import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int map[][] = new int[n][m];
        
        int zero=0;
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1) queue.add(new int[]{i,j,0});
                if(map[i][j]==0) zero++;
            }
        }

        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};
        
        int answer=0;;
        while(!queue.isEmpty()&&zero!=0){
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int time = now[2];
            for(int i=0;i<4;i++){
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(ny<0||ny>=n||nx<0||nx>=m||map[ny][nx]!=0) continue;
                map[ny][nx]=1;
                zero--;
                queue.add(new int[]{ny,nx,time+1});
                answer=Math.max(answer,time+1);
            }
        }
        if(zero!=0){
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
        
    }
}