import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); 
        int t = Integer.parseInt(st.nextToken()); 
        
        int[][] dp = new int[n + 1][t + 1];
        int total = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); 
            int m = Integer.parseInt(st.nextToken()); 
            total+=m;

            for (int e = 0; e <= t; e++) {
                dp[i][e] = dp[i - 1][e]; // don't take item i
                if (e >= d) {
                    dp[i][e] = Math.max(dp[i][e], dp[i - 1][e - d] + m); // take item i
                }
            }
        }

        System.out.println(total-dp[n][t]);
    }
}
