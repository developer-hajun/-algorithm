import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        for(int tc=0; tc<n; tc++){
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int[] now = new int[3];
            int[][] fire = new int[h][w];
            boolean[][] visited = new boolean[h][w]; 

            Queue<int[]> fire_start = new ArrayDeque<>();
            
            for(int i=0;i<h;i++){
                String value = br.readLine();
                for(int j=0;j<w;j++){
                    char nowe = value.charAt(j);

                    if(nowe=='#') fire[i][j]=0;
                    else if(nowe=='@'){
                        fire[i][j] = Integer.MAX_VALUE;
                        now = new int[]{i,j,0};
                        visited[i][j] = true; 
                    }
                    else if(nowe=='*'){
                        fire[i][j]=0;
                        fire_start.add(new int[]{i,j,0});
                    }
                    else{
                        fire[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            while(!fire_start.isEmpty()){
                int[] nowe = fire_start.poll();
                for(int i=0;i<4;i++){
                    int ny = nowe[0]+dy[i];
                    int nx = nowe[1]+dx[i];
                    if(ny<0||ny>=h||nx<0||nx>=w) continue;
                    if(fire[ny][nx] != Integer.MAX_VALUE) continue;

                    fire[ny][nx] = nowe[2]+1;
                    fire_start.add(new int[]{ny,nx,nowe[2]+1});
                }
            }

            Queue<int[]> move = new ArrayDeque<>();
            move.add(now);
            boolean escaped = false;

            while(!move.isEmpty()){
                int[] nowe = move.poll();
                for(int i=0;i<4;i++){
                    int ny = nowe[0]+dy[i];
                    int nx = nowe[1]+dx[i];

                    if(ny<0||ny>=h||nx<0||nx>=w){
                        System.out.println(nowe[2]+1);
                        escaped = true;
                        break;
                    }

                    if(visited[ny][nx]) continue; 
                    if(fire[ny][nx] <= nowe[2]+1) continue;

                    visited[ny][nx] = true; 
                    move.add(new int[]{ny,nx,nowe[2]+1});
                }
                if(escaped) break;
            }

            if(!escaped){
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
