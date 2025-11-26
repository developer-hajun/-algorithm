import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            List<int[]>[] move = new ArrayList[n + 1];
            int[] dist = new int[n + 1];

            for (int i = 0; i <= n; i++) move[i] = new ArrayList<>();
            Arrays.fill(dist, 100_000_000);
            dist[s] = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if ((a == g && b == h) || (a == h && b == g)) {
                    move[a].add(new int[]{b, d * 2 - 1});
                    move[b].add(new int[]{a, d * 2 - 1});
                } else {
                    move[a].add(new int[]{b, d * 2});
                    move[b].add(new int[]{a, d * 2});
                }
            }

            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            queue.add(new int[]{s, 0});

            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                int start = now[0];
                int count = now[1];

                if (dist[start] < count) continue;

                for (int[] next : move[start]) {
                    int ne = next[0];
                    int next_count = count + next[1];

                    if (dist[ne] > next_count) {
                        dist[ne] = next_count;
                        queue.add(new int[]{ne, next_count});
                    }
                }
            }

            int[] value = new int[t];
            for (int i = 0; i < t; i++) value[i] = Integer.parseInt(br.readLine());

            Arrays.sort(value);
            for (int val : value) {
                if (dist[val] != 100_000_000 && dist[val] % 2 != 0) {
                    System.out.print(val + " ");
                }
            }
            System.out.println();
        }
    }
}