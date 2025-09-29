import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] map = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }
        int[][] move = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                move[i][j]=j;
            }
        }
        for (int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            map[u][v] = w;
            map[v][u] = w;
            move[u][v] = v;
            move[v][u] = u;

        }

        
        // Floyd–Warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
                        if(map[i][k] + map[k][j]<map[i][j]){
                            move[i][j] = move[i][k];
                            map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                        }
                    }
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < N; i++) {
            for(int j=0;j<N;j++){
                if(i==j) sb.append("- ");
                else{
                    sb.append(move[i][j]+1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
