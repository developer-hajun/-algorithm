

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        int[] use = {6,2,5,5,4,5,6,3,7,6}; 

        String[] dp = new String[101]; 
        Arrays.fill(dp, null);
        dp[0] = ""; 

        
        for (int i = 0; i <= 100; i++) {
            if (dp[i] == null) continue;
            for (int d = 0; d <= 9; d++) {
                int match = use[d];
                if (i + match > 100) continue;

                String candidate = dp[i] + d;

                if (dp[i].equals("") && d == 0) continue;

                if (dp[i + match] == null || compare(candidate, dp[i + match]) < 0) {
                    dp[i + match] = candidate;
                }
            }
        }


        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());

            String minNumber = dp[n];

            int[] answer = new int[n / 2];
            Arrays.fill(answer, 1);
            int remain = n % 2;

            TreeMap<Integer, Integer> count = new TreeMap<>((o1, o2) -> o2 - o1);
            for (int i = 9; i >= 0; i--) {
                if (i == 1) continue;
                int pl = remain / (use[i] - 2);
                if (pl == 0) continue;
                count.put(i, pl);
                remain %= use[i];
                if (remain == 0) break;
            }

            int num = 0;
            while (count.size() != 0 && num < answer.length) {
                int now = count.firstKey();
                answer[num] = now;
                int value = count.get(now) - 1;
                if (value == 0) count.remove(now);
                else count.put(now, value);
                num++;
            }

            StringBuilder maxSb = new StringBuilder();
            for (int digit : answer) {
                maxSb.append(digit);
            }

            System.out.println(minNumber + " " + maxSb);
        }
    }

 
    static int compare(String a, String b) {
        if (a.length() != b.length()) return a.length() - b.length();
        return a.compareTo(b);
    }
}
