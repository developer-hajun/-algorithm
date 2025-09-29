import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y, x, dist;
        Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();

        visited = new boolean[N][N];
        int islandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    markIsland(i, j, islandNum);
                    islandNum++;
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int num = 2; num < islandNum; num++) {
            result = Math.min(result, buildBridge(num));
        }

        System.out.println(result);
    }

    static void markIsland(int y, int x, int num) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(y, x, 0));
        visited[y][x] = true;
        map[y][x] = num;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = p.y + dy[dir];
                int nx = p.x + dx[dir];
                if (ny >= 0 && nx >= 0 && ny < N && nx < N) {
                    if (!visited[ny][nx] && map[ny][nx] == 1) {
                        visited[ny][nx] = true;
                        map[ny][nx] = num;
                        q.add(new Point(ny, nx, 0));
                    }
                }
            }
        }
    }

    static int buildBridge(int island) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (map[i][j] == island) {
                    q.add(new Point(i, j, 0));
                    visit[i][j] = true;
                }

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = p.y + dy[dir];
                int nx = p.x + dx[dir];
                if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visit[ny][nx]) {
                    if (map[ny][nx] == 0) {
                        visit[ny][nx] = true;
                        q.add(new Point(ny, nx, p.dist + 1));
                    } 
                    else if (map[ny][nx] != island) {
                        return p.dist;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
