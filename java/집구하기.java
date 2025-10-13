import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] node = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) node[i] = new ArrayList<>();

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            node[start].add(new int[]{end, dist});
            node[end].add(new int[]{start, dist});
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] mac = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) mac[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[] starbucks = new int[s];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) starbucks[i] = Integer.parseInt(st.nextToken());

        int[] mac_dist = Dijkstra(node, mac, x);
        int[] starbucks_dist = Dijkstra(node, starbucks, y);

        for (int i : mac) {
            mac_dist[i] = Integer.MAX_VALUE / 2;
            starbucks_dist[i] = Integer.MAX_VALUE / 2;
        }
        for (int i : starbucks) {
            mac_dist[i] = Integer.MAX_VALUE / 2;
            starbucks_dist[i] = Integer.MAX_VALUE / 2;
        }

        int value = Integer.MAX_VALUE;

        for (int i = 1; i <= v; i++) {
            if (mac_dist[i] <= x && starbucks_dist[i] <= y) {
                value = Math.min(value, mac_dist[i] + starbucks_dist[i]);
            }
        }

        System.out.println(value == Integer.MAX_VALUE ? -1 : value);
    }

    public static int[] Dijkstra(ArrayList<int[]>[] edge, int[] start_nodes, int max_value) {
        int[] dist = new int[edge.length];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (int start : start_nodes) {
            dist[start] = 0;
            queue.add(new int[]{start, 0});
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int node = now[0];
            int distance = now[1];

            if (distance > dist[node]) continue;

            for (int[] next : edge[node]) {
                int next_node = next[0];
                int next_distance = distance + next[1];

                if (next_distance > max_value) continue;

                if (next_distance < dist[next_node]) {
                    dist[next_node] = next_distance;
                    queue.add(new int[]{next_node, next_distance});
                }
            }
        }
        return dist;
    }
}
