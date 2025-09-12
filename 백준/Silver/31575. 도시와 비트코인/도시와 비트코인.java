import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();int N = sc.nextInt();
        int[][] board = new int[N][M];
        boolean[][] dp = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] && board[i][j] == 1) {

                    if (i + 1 < N && board[i+1][j]==1) {
                        dp[i + 1][j] = true;
                    }

                    if (j + 1 < M && board[i][j+1]==1) {
                        dp[i][j + 1] = true;
                    }
                }
            }
        }

        if (dp[N - 1][M - 1]) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
