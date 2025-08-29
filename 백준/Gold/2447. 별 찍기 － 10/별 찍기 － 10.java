import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static char[][] map;

    public static void dfs(int start_x, int end_x, int start_y, int end_y) {
        if (start_x == end_x && start_y == end_y) {
            map[start_x][start_y] = '*';
            return;
        }
        int len = (end_x - start_x + 1) / 3; 

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                dfs(start_x + i * len, start_x + (i + 1) * len - 1,
                    start_y + j * len, start_y + (j + 1) * len - 1);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map= new char[n][n];
        for(int i=0;i<n;i++) Arrays.fill(map[i],' ');

        dfs(0,n-1,0,n-1);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) sb.append(new String(map[i])).append("\n");
        System.out.println(sb);
    }
}

