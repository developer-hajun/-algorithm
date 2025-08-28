import java.util.*;
import java.io.*;

class Main {
    static int[][] matrix;

    public static String solve(int start_x, int end_x, int start_y, int end_y) {
        if (start_x == end_x && start_y == end_y) {
            return String.valueOf(matrix[start_y][start_x]);
        }

        int mid_x = (start_x + end_x) / 2;
        int mid_y = (start_y + end_y) / 2;

        String[] value = new String[4];
        value[0] = solve(start_x, mid_x, start_y, mid_y);    
        value[1] = solve(mid_x + 1, end_x, start_y, mid_y);     
        value[2] = solve(start_x, mid_x, mid_y + 1, end_y);    
        value[3] = solve(mid_x + 1, end_x, mid_y + 1, end_y);   

        if (value[0].equals(value[1]) && value[1].equals(value[2]) && value[2].equals(value[3])
            && value[0].length() == 1) {
            return value[0];
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            for (String v : value) {
                sb.append(v);
            }
            sb.append(')');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String now = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = now.charAt(j) - '0';
            }
        }

        System.out.print(solve(0, n - 1, 0, n - 1));
    }
}
