import java.util.*;
import java.io.*;

class Main {
    static int answer= Integer.MAX_VALUE;
    static int n=0;
    static Stack<Integer> left = new Stack<>();
    static boolean[] visit;
    static int[][] value;
    
    public static void main(String[] args) throws IOException {
        input();
    }

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;
        n = Integer.parseInt(br.readLine());
        value = new int[n][n];
        visit = new boolean[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                value[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        System.out.println(answer);
    }

    public static void dfs(int count,int next){
        if(count==n/2){
            Stack<Integer> right = new Stack<>();
            for(int i=0;i<n;i++){
                if(visit[i]) continue;
                right.add(i);
            }
            answer = Math.min(answer,Math.abs(calc(right)-calc(left)));
            return;
        }
        if(next==n){
            return;
        }
        left.add(next);
        visit[next]=true;
        dfs(count+1,next+1);
        visit[next]=false;
        left.pop();
        dfs(count,next+1);
    }

    public static int calc(Stack<Integer> now){
        int val=0;
        for(Integer a:now){
            for(Integer b:now){
                val+=value[a][b];
            }
        }
        return val;
    }
}
