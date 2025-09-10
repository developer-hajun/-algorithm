import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[2001];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int human = Integer.parseInt(st.nextToken());

            for (int j = human; j <= 2000; j++) {
                dp[j] = Math.min(dp[j], dp[j - human] + money);
            }
        }

        int answer = INF;
        for (int i = c; i <= 2000; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
