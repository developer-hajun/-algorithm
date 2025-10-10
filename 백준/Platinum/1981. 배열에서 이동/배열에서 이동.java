import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean check(int D) {
        for (int min_val = 0; min_val <= 200 - D; min_val++) {
            int max_val = min_val + D;
            
            if (map[0][0] < min_val || map[0][0] > max_val) {
                continue;
            }

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;


            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int y = cur[0];
                int x = cur[1];


                if (y == N - 1 && x == N - 1) {
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                        continue;
                    }

                    int next_val = map[ny][nx];
                    
                    if (next_val >= min_val && next_val <= max_val) {
                        visited[ny][nx] = true;
                        queue.offer(new int[]{ny, nx});
                    }
                }
            }
        }
        

        return false;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        int V_min = Integer.MAX_VALUE;
        int V_max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                V_min = Math.min(V_min, map[i][j]);
                V_max = Math.max(V_max, map[i][j]);
            }
        }
        

        int low = 0;
        int high = V_max - V_min;
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2; 
            
            if (check(mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        System.out.println(result);
    }
}