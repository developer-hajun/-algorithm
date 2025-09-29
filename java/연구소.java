import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int y,x;
    public Pair(int a,int b){
        y=a;
        x=b;
    }
}
class Main {
    static int[][] map;
    static int n,m;
    static int zero;
    static List<Pair> bi = new ArrayList<>();
    static int answer = -Integer.MAX_VALUE; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();m=sc.nextInt();
        map=new int[n][m];
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                map[y][x] = sc.nextInt();
                if(map[y][x]==2) bi.add(new Pair(y,x));
                else if(map[y][x]==0) zero++;
            }
        }
        zero-=3;
        dfs(0);
        System.out.println(answer);
    }
    public static int bfs(){
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visit= new boolean[n][m];
        for(Pair now : bi){
            visit[now.y][now.x] = true;
            queue.add(now);
        }
        
        int count=0;
        while(!queue.isEmpty()){
            Pair now = queue.poll();
            for(int[] next:new int[][]{ {now.y-1,now.x},{now.y+1,now.x},{now.y,now.x-1},{now.y,now.x+1} }){
                int ny =next[0];
                int nx =next[1];
                if(ny<0||ny>=n||nx<0||nx>=m||map[ny][nx]!=0||visit[ny][nx]) continue;
                visit[ny][nx]=true;
                count++;
                queue.add(new Pair(ny,nx));
            }
        }
        return zero-count;
    }

    public static void dfs(int count){
        if(count==3){
            answer = Math.max(answer,bfs());
            return;
        }
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                if(map[y][x]!=0) continue;
                map[y][x]=1;
                dfs(count+1);
                map[y][x]=0;
            }
        }
    }
}