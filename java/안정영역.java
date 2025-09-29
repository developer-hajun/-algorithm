import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[][] move = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int answer = 1;
        for (int h = 1; h <= maxHeight; h++) {
            visit = new boolean[N][N];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > h && !visit[i][j]) {
                        bfs(i, j, h);
                        count++;
                    }
                }
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y, int h) {
        Queue<int[]> queue = new LinkedList<>();
        visit[x][y] = true;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];
            for (int[] m : move) {
                int nx = cx + m[0];
                int ny = cy + m[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N ||visit[nx][ny] || map[nx][ny] <= h) continue;

                visit[nx][ny] = true;
                queue.add(new int[] { nx, ny });
            }
        }
    }
}
