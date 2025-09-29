import java.util.*;
import java.lang.*;
import java.io.*;
class Pair{
    int y,x;
    public Pair(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class Main {
    static int[][] arr;
    static Pair[] human;
    static Pair start;
    static int n,m,can_move;

    static int[] next_human(int fuel){
        int[][] visit = new int[n][n];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        visit[start.y][start.x] = 0;
        int[] answer = {-1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}; // 번호, 거리, y, x
        int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()){
            Pair now = queue.poll();
            if(arr[now.y][now.x]>1){
                int dist = visit[now.y][now.x];
                if(dist < answer[1] || 
                  (dist == answer[1] && now.y < answer[2]) ||
                  (dist == answer[1] && now.y == answer[2] && now.x < answer[3])){
                    answer = new int[]{arr[now.y][now.x], dist, now.y, now.x};
                }
                continue;
            }
            for(int i=0;i<4;i++){
                int ny = now.y+move[i][0];
                int nx = now.x+move[i][1];
                if(ny<0 || ny>=n || nx<0 || nx>=n || arr[ny][nx]==1 || visit[ny][nx]!=0) continue;
                visit[ny][nx] = visit[now.y][now.x]+1;
                if(visit[ny][nx] > fuel) continue;
                queue.add(new Pair(ny,nx));
            }
        }
        if(answer[0] == -1) return new int[]{-1,0,0,0};
        return answer;
    }
    static int move(Pair st,Pair end,int fuel){
        int[][] visit = new int[n][n];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(st);
        visit[st.y][st.x] = 0;
        int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()){
            Pair now = queue.poll();
            if(now.y==end.y && now.x==end.x){
                return visit[now.y][now.x];
            }
            for(int i=0;i<4;i++){
                int ny = now.y+move[i][0];
                int nx = now.x+move[i][1];
                if(ny<0 || ny>=n || nx<0 || nx>=n || arr[ny][nx]==1 || visit[ny][nx]!=0) continue;
                visit[ny][nx]= visit[now.y][now.x]+1;
                if(visit[ny][nx]>fuel) continue;
                queue.add(new Pair(ny,nx));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tr = new StringTokenizer(br.readLine());
        n=Integer.parseInt(tr.nextToken());
        m=Integer.parseInt(tr.nextToken());
        can_move=Integer.parseInt(tr.nextToken());
        arr = new int[n][n];
        for(int y=0;y<n;y++){
            tr = new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                arr[y][x] = Integer.parseInt(tr.nextToken());
            }
        }
        tr = new StringTokenizer(br.readLine());
        start = new Pair(Integer.parseInt(tr.nextToken())-1,Integer.parseInt(tr.nextToken())-1);
        human = new Pair[m];
        for(int i=0;i<m;i++){
            tr = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(tr.nextToken())-1][Integer.parseInt(tr.nextToken())-1]=i+2;
            human[i] = new Pair(Integer.parseInt(tr.nextToken())-1,Integer.parseInt(tr.nextToken())-1);
        }

        int answer = 0;
        for(int l=0;l<m;l++){
            int[] find_human = next_human(can_move);
            if(find_human[0]==-1){
                can_move=-1;
                break;
            } //갈수 있는 거리에 태울 수 있는 사람이 없는경우
            answer+=find_human[1];
            can_move-=find_human[1];
            arr[find_human[2]][find_human[3]]=0;
            Pair st = new Pair(find_human[2],find_human[3]);
            Pair next = human[find_human[0]-2];
            int go = move(st,next,can_move);
            if(go==-1){
                can_move=-1;
                break;
            }
            answer+=go;
            can_move+=go;
            start = next;
        }
        System.out.print(can_move);
    }
}
