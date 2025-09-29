import java.util.*;
import java.lang.*;
import java.io.*;



class Main {
    static int n,w,h;
    static int answer;
    static int sum;
    static int map[][];
    static int[] make;


    public static int[][] deepcopy(){
        int[][] copy = new int[h][w];
        for(int i=0;i<h;i++){
            copy[i]=map[i].clone();
        }
        return copy;
    }

    public static int[] find(int x,int[][] maps){
        for(int y=0;y<h;y++){
            if(maps[y][x]!=0) return new int[]{y,x};
        }
        return new int[]{-1,-1};
    }

    public static int sim(){
        int count = 0;
        int[][] now_map = deepcopy();
        for(int ball:make){
            int[] yx = find(ball,now_map);
            int y = yx[0],x=yx[1];
            if(y==-1) continue;
            // 떨어트렷을때 도착하는 위치 찾기
            // 땅에 닿은 경우 그냥 넘어가기
            boolean[][] visit = new boolean[h][w];
            Queue<int[]> queue  = new ArrayDeque<>();
            visit[y][x]=true;
            queue.add(new int[]{y,x});
            count++;
            while(!queue.isEmpty()){
                int[] now = queue.poll();
                int ny=now[0],nx = now[1];
                int boom = now_map[ny][nx];
                now_map[ny][nx]=0;
                for(int k=1;k<boom;k++){
                    for(int[] move : new int[][]{{ny+k,nx},{ny-k,nx},{ny,nx+k},{ny,nx-k}}){
                        int next_y = move[0];
                        int next_x = move[1];
                        if(next_y<0||next_x<0||next_y>=h||next_x>=w||visit[next_y][next_x]||now_map[next_y][next_x]==0) continue;
                        visit[next_y][next_x]=true;
                        count++;
                        queue.add(new int[]{next_y,next_x});
                    }
                }//다음으로 터질꺼 넣기
            }
            // 위치를 기준으로 BFS를 통해서 터트릴꺼 터트리기
            for (int e = 0; e < w; e++) {
                int idx = h - 1;
                for (int u = h - 1; u >= 0; u--) {
                    if (now_map[u][e] != 0) {
                        now_map[idx][e] = now_map[u][e];
                        if (idx != u) now_map[u][e] = 0;
                        idx--;
                    }
                }
                // 위쪽 빈 공간은 0으로 채우기
                for (int u = idx; u >= 0; u--) {
                    now_map[u][e] = 0;
                }
            }
            // 내리기
        }

        return count;
    }
    
    public static void product(int count){
        if(count==n){
            answer = Math.max(answer,sim());
            return;
        }
        for(int i=0;i<w;i++){
            make[count]=i;
            product(count+1);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=t;tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            answer =0;
            sum=0;
            map = new int[h][w];
            make=new int[n];
            
            for(int i=0;i<h;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<w;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]!=0) sum++;
                }
            }
            product(0);
            System.out.println("#"+tc+" "+(sum-answer));
            
        }
    }
}