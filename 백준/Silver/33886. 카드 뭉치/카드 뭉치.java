import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N]; 
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            for (int j = i; j >= 0; j--) {
                int length = i - j + 1;
                if (length <= A[j]) {
                    int prev = (j - 1 >= 0) ? dp[j - 1] : 0;
                    dp[i] = Math.min(dp[i], prev + 1);
                }
            }
        }

        System.out.println(dp[N - 1]);
    }
}