import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int cheeseCount = 0;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N + 2][M + 2]; // 1씩 확장된 배열

        // 입력 + 치즈 개수 세기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) cheeseCount++;
            }
        }

        int time = 0;
        int lastCheese = 0;

        while (cheeseCount > 0) {
            lastCheese = cheeseCount;
            bfsAir();
            melt();
            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);
    }

    static void bfsAir() {
        visited = new boolean[N + 2][M + 2];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny >= 0 && ny < N + 2 && nx >= 0 && nx < M + 2
                        && !visited[ny][nx] && map[ny][nx] != 1) {
                    map[ny][nx] = 2;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    static void melt() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (map[ny][nx] == 2) {
                            map[i][j]=0;
                            cheeseCount--;
                            break;
                        }
                    }
                }
            }
        }
    }
}
