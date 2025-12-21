import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int map[][] = new int[r][c];
        for (int i = 0; i < r; i++) Arrays.fill(map[i], -1);

        Queue<int[]> queue = new ArrayDeque<>();
        int sy = 0, sx = 0;
        for (int i = 0; i < r; i++) {
            String now = br.readLine();
            for (int j = 0; j < c; j++) {
                char value = now.charAt(j);
                if (value == '#') {
                    map[i][j] = -2; 
                } else if (value == 'F') {
                    map[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                } else if (value == 'J') {
                    sy = i;
                    sx = j;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny >= 0 && ny < r && nx >= 0 && nx < c && map[ny][nx] == -1) {
                    map[ny][nx] = now[2] + 1;
                    queue.add(new int[]{ny, nx, now[2] + 1});
                }
            }
        }

        int visit[][] = new int[r][c];
        queue.add(new int[]{sy, sx, 0});
        visit[sy][sx] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int count = now[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= r || nx < 0 || nx >= c) {
                    System.out.print(count + 1);
                    return; 
                }
                if (map[ny][nx] != -2 && visit[ny][nx] == 0) {
                    if (map[ny][nx] == -1 || map[ny][nx] > count + 1) {
                        visit[ny][nx] = 1;
                        queue.add(new int[]{ny, nx, count + 1});
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}