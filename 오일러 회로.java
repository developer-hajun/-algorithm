import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            int degree = 0;
            for (int j = 0; j < N; j++) {
                degree += graph[i][j];
            }
            if (degree % 2 != 0) {
                System.out.println(-1);
                return;
            }
        }

        dfs(0);

        //Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for (int node : path) {
            sb.append(node + 1).append(' ');
        }
        System.out.println(sb.toString());
    }

    static void dfs(int u) {
        for (int v = 0; v < N; v++) {
            while (graph[u][v] > 0) {
                graph[u][v]--;
                graph[v][u]--;
                dfs(v);
            }
        }
        path.add(u);
    }
}
