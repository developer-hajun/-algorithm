import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int totalPeople = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += arr[i][j];
            }
        }

       
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;

                        solve(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
    }

    static void solve(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int[] peopleSum = new int[5];


        for (int r = 0; r < x + d1; r++) {
            for (int c = 0; c <= y; c++) {
                if (border[r][c]) break;
                peopleSum[0] += arr[r][c];
            }
        }


        for (int r = 0; r <= x + d2; r++) {
            for (int c = N - 1; c > y; c--) {
                if (border[r][c]) break;
                peopleSum[1] += arr[r][c];
            }
        }


        for (int r = x + d1; r < N; r++) {
            for (int c = 0; c < y - d1 + d2; c++) {
                if (border[r][c]) break;
                peopleSum[2] += arr[r][c];
            }
        }


        for (int r = x + d2 + 1; r < N; r++) {
            for (int c = N - 1; c >= y - d1 + d2; c--) {
                if (border[r][c]) break;
                peopleSum[3] += arr[r][c];
            }
        }

 
        peopleSum[4] = totalPeople;
        for (int i = 0; i < 4; i++) peopleSum[4] -= peopleSum[i];


        int max = Arrays.stream(peopleSum).max().getAsInt();
        int minVal = Arrays.stream(peopleSum).min().getAsInt();

        min = Math.min(min, max - minVal);
    }
}
