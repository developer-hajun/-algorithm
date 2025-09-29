import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] during = new int[n + 1]; 
        int[] bosu = new int[n + 1];   
        int[] dp = new int[n + 2];   

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            during[i] = Integer.parseInt(st.nextToken());
            bosu[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
        
            if (i + during[i] - 1 <= n) {
                dp[i + during[i] - 1] = Math.max(dp[i + during[i] - 1], dp[i - 1] + bosu[i]);
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}

