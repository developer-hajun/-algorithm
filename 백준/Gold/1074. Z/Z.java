import java.util.*;
import java.io.*;

class Main {
    static int n, r, c;

    public static void solve(int start_x, int end_x, int start_y, int end_y, int answer) {
        if (r < start_y || r > end_y || c < start_x || c > end_x) {
            return;
        }

        if (start_x == end_x && start_y == end_y) {
            if (start_y == r && start_x == c) {
                System.out.println(answer);
                System.exit(0);
            }
            return;
        }

        int mid_x = (start_x + end_x) / 2;
        int mid_y = (start_y + end_y) / 2;

        int totalSize = (end_x - start_x + 1) * (end_y - start_y + 1); // 총 칸 수
        int quarter = totalSize / 4;

        solve(start_x, mid_x, start_y, mid_y, answer);

        solve(mid_x + 1, end_x, start_y, mid_y, answer + quarter);

        solve(start_x, mid_x, mid_y + 1, end_y, answer + quarter * 2);

        solve(mid_x + 1, end_x, mid_y + 1, end_y, answer + quarter * 3);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, n); // 한 변의 길이
        solve(0, size - 1, 0, size - 1, 0);
    }
}
