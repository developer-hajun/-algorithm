import java.util.*;

public class Main {
    static char[][] board;
    static int n, m;
    static int[] dy = {0, 0, -1, 1}; // left, right, up, down
    static int[] dx = {-1, 1, 0, 0};

    static class State {
        int ry, rx, by, bx, depth;

        State(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        board = new char[n][m];
        int ry = 0, rx = 0, by = 0, bx = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    ry = i;
                    rx = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    by = i;
                    bx = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(ry, rx, by, bx));
    }

    static int bfs(int ry, int rx, int by, int bx) {
        Queue<State> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        queue.offer(new State(ry, rx, by, bx, 0));
        visited[ry][rx][by][bx] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.depth >= 10) return -1;

            for (int dir = 0; dir < 4; dir++) {
                int[] redMove = move(cur.ry, cur.rx, dir);
                int[] blueMove = move(cur.by, cur.bx, dir);

                int nry = redMove[0], nrx = redMove[1];
                int nby = blueMove[0], nbx = blueMove[1];
                boolean redHole = redMove[2] == 1;
                boolean blueHole = blueMove[2] == 1;

                if (blueHole) continue;
                if (redHole) return cur.depth + 1;

                // 겹쳤을 때 처리
                if (nry == nby && nrx == nbx) {
                    if (dir == 0) { // left
                        if (cur.rx < cur.bx) nbx++;
                        else nrx++;
                    } else if (dir == 1) { // right
                        if (cur.rx > cur.bx) nbx--;
                        else nrx--;
                    } else if (dir == 2) { // up
                        if (cur.ry < cur.by) nby++;
                        else nry++;
                    } else if (dir == 3) { // down
                        if (cur.ry > cur.by) nby--;
                        else nry--;
                    }
                }

                if (!visited[nry][nrx][nby][nbx]) {
                    visited[nry][nrx][nby][nbx] = true;
                    queue.offer(new State(nry, nrx, nby, nbx, cur.depth + 1));
                }
            }
        }
        return -1;
    }

    static int[] move(int y, int x, int dir) {
        int count = 0;
        boolean hole = false;

        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (board[ny][nx] == '#') break;
            if (board[ny][nx] == 'O') {
                hole = true;
                y = ny;
                x = nx;
                break;
            }
            y = ny;
            x = nx;
            count++;
        }

        return new int[]{y, x, hole ? 1 : 0};
    }
}