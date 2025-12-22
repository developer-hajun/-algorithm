import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n+1][n+1];
        List<Integer>[][] path = new ArrayList[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            for (int j = 1; j <= n; j++) {
                path[i][j] = new ArrayList<>();
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (c < dist[a][b]) { 
                dist[a][b] = c;
                path[a][b].clear();
                path[a][b].add(a);
                path[a][b].add(b);
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) continue;
                    int value = dist[i][k] + dist[k][j];
                    if (value < dist[i][j]) {
                        dist[i][j] = value;
                        path[i][j].clear();
                        path[i][j].addAll(path[i][k]);
                        if (!path[i][j].isEmpty()) {
                            path[i][j].remove(path[i][j].size() - 1);
                        }
                        path[i][j].addAll(path[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF || i == j) {
                    sb.append("0\n");
                } else {
                    sb.append(path[i][j].size()).append(" ");
                    for (int node : path[i][j]) sb.append(node).append(" ");
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb.toString());
    }
}
