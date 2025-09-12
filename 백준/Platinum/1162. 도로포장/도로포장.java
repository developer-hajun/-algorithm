import java.util.*;

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Move {
    int node;
    long value;
    int depth;

    public Move(int n, long v, int d) {
        node = n;
        value = v;
        depth = d;
    }
}

@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        int m = sc.nextInt(); 
        int k = sc.nextInt();

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        long[][] visit = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(visit[i], Long.MAX_VALUE);
        }
        visit[0][1] = 0;

        PriorityQueue<Move> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1.value, o2.value));
        queue.add(new Move(1, 0, 0));

        while (!queue.isEmpty()) {
            Move now = queue.poll();
            int node = now.node;
            long value = now.value;
            int depth = now.depth;

            if (value > visit[depth][node]) continue;

            for (Edge e : graph[node]) {
                int next_node = e.to;
                int weight = e.weight;


                if (visit[depth][next_node] > value + weight) {
                    visit[depth][next_node] = value + weight;
                    queue.add(new Move(next_node, value + weight, depth));
                }

                if (depth < k && visit[depth + 1][next_node] > value) {
                    visit[depth + 1][next_node] = value;
                    queue.add(new Move(next_node, value, depth + 1));
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            ans = Math.min(ans, visit[i][n]);
        }

        System.out.println(ans);
    }
}
