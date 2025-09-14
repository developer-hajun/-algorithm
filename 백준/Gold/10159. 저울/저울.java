import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        final long INF = Long.MAX_VALUE / 2;

        long[][] map = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            map[s][e] = 1; 
        }

        // Floyd–Warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][k] != INF && map[k][j] != INF) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int known = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (map[i][j] != INF || map[j][i] != INF) {
                    known++;
                }
            }
            sb.append(N - 1 - known).append("\n");
        }

        System.out.print(sb);
    }
}
