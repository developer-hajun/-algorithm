import java.util.*;

class Main {
    static int n, m;
    static char[][] map;
    static int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int startY, int startX) {
        boolean[][] visited = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        visited[startY][startX] = true;
        queue.offer(new Node(startY, startX, 0));

        int maxDist = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            maxDist = Math.max(maxDist, curr.dist);

            for (int[] d : dir) {
                int ny = curr.y + d[0];
                int nx = curr.x + d[1];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m
                        && !visited[ny][nx] && map[ny][nx] == 'L') {
                    visited[ny][nx] = true;
                    queue.offer(new Node(ny, nx, curr.dist + 1));
                }
            }
        }

        return maxDist;
    }

    static class Node {
        int y, x, dist;

        Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}
