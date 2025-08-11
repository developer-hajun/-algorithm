import java.io.*;
import java.util.*;
import java.util.stream.*;



class Pair{
    int y,x;
    public Pair(int a,int b){
        y=a;
        x=b;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] move = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        boolean[][] visit = new boolean[n][m];
        int[][] arr = new int[n][m];

        for(int y=0;y<n;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<m;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                if(arr[y][x]==1 && !visit[y][x]){
                    count++;
                    visit[y][x]=true;
                    Queue<Pair> queue = new ArrayDeque<>();
                    queue.add(new Pair(y,x));
                    while(!queue.isEmpty()){
                        Pair now = queue.poll();
                        for(int[] next: move){
                            int ny = now.y+next[0];
                            int nx = now.x+next[1];
                            if(ny<0||nx<0||ny>=n||nx>=m||arr[ny][nx]==0||visit[ny][nx]) continue;
                            visit[ny][nx]=true;
                            queue.add(new Pair(ny,nx));
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
    
}
