import java.util.*;
import java.io.*;

class human {
    int y, x;
    public human(int a, int b) {
        y = a;
        x = b;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("Test4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 가로 최대 좌표
        int m = Integer.parseInt(st.nextToken()); // 세로 최대 좌표
        int t = Integer.parseInt(st.nextToken()); // 사람 수
        int k = Integer.parseInt(st.nextToken()); // 사각형 한 변 길이

        Set<Integer> y_arr = new HashSet<>();
        Set<Integer> x_arr = new HashSet<>();

        List<human> humans = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            y_arr.add(y);
            x_arr.add(x);
            humans.add(new human(y, x));
        }

        int answer_count = -1;
        int answer_y = -1;
        int answer_x = -1;

        for (int y : y_arr) {
            for (int x : x_arr) {
                int startY = y;
                int startX = x;

                // y축 아래쪽 경계 보정 (y-k가 0보다 작으면 k로 보정)
                if (startY - k < 0) {
                    startY = k;
                }

                // x축 오른쪽 경계 보정 (x+k가 n보다 크면 n-k로 보정)
                if (startX + k > n) {
                    startX = n - k;
                }

                int count = 0;
                for (human hu : humans) {
                    int ny = hu.y;
                    int nx = hu.x;
                    if (ny > startY || ny < startY - k || nx < startX || nx > startX + k)
                        continue;
                    count++;
                }

                if (answer_count < count) {
                    answer_count = count;
                    answer_y = startY;
                    answer_x = startX;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer_x).append(" ").append(answer_y).append("\n").append(answer_count);
        System.out.print(sb.toString());
    }
}
