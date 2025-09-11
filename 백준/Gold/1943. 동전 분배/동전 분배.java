

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int tc = 1; tc <= 3; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] won = new int[n];
            int[] count = new int[n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                won[i] = Integer.parseInt(st.nextToken());
                count[i] = Integer.parseInt(st.nextToken());
                sum += won[i] * count[i];
            }

            if (sum % 2 != 0) {
                System.out.println(0);
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            for (int i = 0; i < n; i++) {
                int coin = won[i];
                int cnt = count[i];

             
                for (int j = target; j >= 0; j--) {
                    if (!dp[j]) continue;

                    for (int k = 1; k <= cnt; k++) {
                        int next = j + coin * k;
                        if (next > target) break;
                        dp[next] = true;
                    }
                }
            }

            System.out.println(dp[target] ? 1 : 0);
        }
    }
}
