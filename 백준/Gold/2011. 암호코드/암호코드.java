import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String now = br.readLine();
        int[] num = new int[now.length() + 1];

        for (int i = 1; i <= now.length(); i++) num[i] = now.charAt(i - 1) - '0';

        int[] dp = new int[now.length() + 1];
        dp[0] = 1;

        if (num[1] == 0) {  
            System.out.print(0);
            return;
        }

        dp[1] = 1;

        for (int i = 2; i <= now.length(); i++) { 
            int value = num[i];
            if (value == 0) {
                if (num[i - 1] > 2 || num[i - 1] == 0) {
                    System.out.print(0);
                    return;
                }
                dp[i] = dp[i - 2];
            } else if (value <= 6) {
                if (num[i - 1] != 0 && num[i - 1] < 3) dp[i] = dp[i - 1] + dp[i - 2];
                else dp[i] = dp[i - 1];
            } else {
                if (num[i - 1] == 1) dp[i] = dp[i - 1] + dp[i - 2];
                else dp[i] = dp[i - 1];
            }
            dp[i]%=1000000;
        }
        System.out.println(dp[now.length()]);

    }
}
