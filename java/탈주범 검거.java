import java.util.*;
import java.io.*;

class Pair{
    int y;
    int x;
    int time;
    public Pair(int a,int b,int t){
        y=a; x=b;time=t;
    }
}
public class Main {
    static int[][] tunnels={
        {0,0,0,0},
        {1,1,1,1},
        {0,0,1,1},
        {1,1,0,0},
        {0,1,1,0},
        {0,1,0,1},
        {1,0,0,1},
        {1,0,1,0}
    };  
    static int[] reverse = {1,0,3,2};
    static int[] dy = {0,0,-1,1};
    static int[] dx = {-1,1,0,0};
    static int n,m,r,c,l;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());


        for(int test_case = 1;test_case<=t;test_case++){
            st = new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            r=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());
            l=Integer.parseInt(st.nextToken())-1;
            map = new int[n][m];
            visit = new boolean[n][m];
            for(int y=0;y<n;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<m;x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<Pair> queue = new ArrayDeque<>();
            visit[r][c]=true;
            int count=1;
            queue.add(new Pair(r,c,0));

            while(!queue.isEmpty()){
                Pair now = queue.poll();
                if(now.time==l) continue;

                int y = now.y;
                int x = now.x;
                
                int tunnel = map[y][x];
                for(int i=0;i<4;i++){
                    if(tunnels[tunnel][i]==0) continue;
                    int ny = y+dy[i];
                    int nx = x+dx[i];
                    if(ny<0||nx<0||ny>=n||nx>=m||visit[ny][nx]) continue;
                    int next_tunnel = map[ny][nx];
                    int check = reverse[i];
                    if(tunnels[next_tunnel][check]==0) continue;
                    visit[ny][nx]=true;
                    queue.add(new Pair(ny,nx,now.time+1));
                    count++;
                }
            }
            System.out.println("#"+test_case+" "+count);
        }    
    }
}
