import java.util.*;

public class Solution {
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int countMines(int y, int x) {
        int count = 0;
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == '*') {
                count++;
            }
        }
        return count;
    }

    static void markZeroArea(int y, int x) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{y, x});
        visited[y][x] = true;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int cy = cur[0], cx = cur[1];

            if (countMines(cy, cx) == 0) {
                for (int d = 0; d < 8; d++) {
                    int ny = cy + dy[d], nx = cx + dx[d];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N &&
                        !visited[ny][nx] && map[ny][nx] == '.') {
                        visited[ny][nx] = true;
                        stack.push(new int[]{ny, nx});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            map = new char[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = sc.next().toCharArray();
            }

            int clickCount = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] == '.' && !visited[y][x] && countMines(y, x) == 0) {
                        markZeroArea(y, x);
                        clickCount++;
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] == '.' && !visited[y][x]) {
                        clickCount++;
                        visited[y][x] = true;
                    }
                }
            }

            System.out.println("#" + t + " " + clickCount);
        }
    }
}
