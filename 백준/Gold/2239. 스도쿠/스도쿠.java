import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rowVisit = new boolean[9][10];
    static boolean[][] colVisit = new boolean[9][10];  // ← 수정: cowVisit → colVisit
    static boolean[][][] mVisit = new boolean[3][3][10];
    static List<int[]> zero = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0'; 
                if (board[i][j] != 0) {
                    rowVisit[i][board[i][j]] = true;
                    colVisit[j][board[i][j]] = true;
                    mVisit[i / 3][j / 3][board[i][j]] = true;
                } else {
                    zero.add(new int[]{i, j});
                }
            }
        }

        solveSudoku(0);
    }

    public static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void solveSudoku(int count) {
        if (count == zero.size()) {
            printBoard();
            System.exit(0); 
            return;
        }

        int[] now = zero.get(count);
        int y = now[0], x = now[1];
        boolean[] find = mVisit[y / 3][x / 3];  

        for (int i = 1; i <= 9; i++) {
            if (rowVisit[y][i] || colVisit[x][i] || find[i]) continue;

            board[y][x] = i;
            rowVisit[y][i] = true;
            colVisit[x][i] = true;
            find[i] = true;

            solveSudoku(count + 1);

            board[y][x] = 0;
            rowVisit[y][i] = false;
            colVisit[x][i] = false;
            find[i] = false;
        }
    }
}
