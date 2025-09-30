import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] flowers = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flowers[i][0] = start;
            flowers[i][1] = end;
        }

        Arrays.sort(flowers, (o1, o2) -> {
            if (o1[0] == o2[0]) return o2[1] - o1[1]; // 종료일이 긴 순
            return o1[0] - o2[0];
        });

        int count = 0;
        int cur = 301;
        int end = 0;
        int idx = 0;

        while (cur <= 1130) {
            boolean found = false;
            int maxEnd = 0;

            while (idx < n && flowers[idx][0] <= cur) {
                if (flowers[idx][1] > maxEnd) {
                    maxEnd = flowers[idx][1];
                    found = true;
                }
                idx++;
            }

            if (!found) {
                System.out.println(0);
                return;
            }

            cur = maxEnd;
            count++;
        }

        System.out.println(count);
    }
}
