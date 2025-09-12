import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] board = new int[N][N];
        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] && board[i][j] != 0) {
                    int step = board[i][j];

                    if (step == -1) continue; 

                    if (i + step < N) {
                        dp[i + step][j] = true;
                    }

                    if (j + step < N) {
                        dp[i][j + step] = true;
                    }
                }
            }
        }

        if (dp[N - 1][N - 1]) {
            System.out.println("HaruHaru");
        } else {
            System.out.println("Hing");
        }
    }
}
