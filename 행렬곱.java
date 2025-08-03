import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();
        int[][] A = new int[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                A[y][x] = sc.nextInt();
            }
        }

        int p = sc.nextInt(), q = sc.nextInt();
        int[][] B_original = new int[p][q];
        for (int y = 0; y < p; y++) {
            for (int x = 0; x < q; x++) {
                B_original[y][x] = sc.nextInt();
            }
        }


        int[][] B = new int[q][p];
        for (int y = 0; y < p; y++) {
            for (int x = 0; x < q; x++) {
                B[x][y] = B_original[y][x];
            }
        }


        for (int y = 0; y < n; y++) {
            for (int x = 0; x < q; x++) {
                int sum = 0;
                for (int k = 0; k < m; k++) {
                    sum += A[y][k] * B[x][k];
                }
                System.out.print(sum + " ");
            }
            System.out.println();
        }
    }
}
