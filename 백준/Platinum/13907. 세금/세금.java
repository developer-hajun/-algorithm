import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        List<int[]>[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        int[] tax = new int[k+1];
        for (int i = 1; i <= k; i++) {
            tax[i] = Integer.parseInt(br.readLine());
        }

        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[n][n+1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
        dist[0][start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.add(new int[]{start, 0, 0}); 

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int node = now[0], d = now[1], used = now[2];
            if (dist[used][node] < d) continue;

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int nd = d + edge[1];
                int nu = used + 1;
                if (nu < n && nd < dist[nu][next]) {
                    dist[nu][next] = nd;
                    queue.add(new int[]{next, nd, nu});
                }
            }
        }

        int[] prefix = new int[k+1];
        for (int i = 1; i <= k; i++) {
            prefix[i] = prefix[i-1] + tax[i];
        }

        System.out.println(getMin(dist, end, 0));
        for (int i = 1; i <= k; i++) {
            System.out.println(getMin(dist, end, prefix[i]));
        }
    }

    static int getMin(int[][] dist, int end, int tax) {
        int ans = Integer.MAX_VALUE;
        for (int len = 0; len < dist.length; len++) {
            if (dist[len][end] < Integer.MAX_VALUE / 2) {
                ans = Math.min(ans, dist[len][end] + len * tax);
            }
        }
        return ans;
    }
}
