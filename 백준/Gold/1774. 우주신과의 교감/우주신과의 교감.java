import java.util.*;
import java.io.*;

public class Main {

    static class Edge {
        int a, b;
        double dist;
        Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    static int[] parent;
    static int N, M;
    static int[][] pos;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;   
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        pos = new int[N + 1][2];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        // 이미 연결된 통로
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.dist, o2.dist));

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = Math.sqrt(
                    Math.pow(pos[i][0] - pos[j][0], 2) +
                    Math.pow(pos[i][1] - pos[j][1], 2)
                );
                pq.add(new Edge(i, j, dist));
            }
        }

        double answer = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.a) != find(e.b)) {
                union(e.a, e.b);
                answer += e.dist;
            }
        }

        System.out.printf("%.2f\n", answer);
    }
}
