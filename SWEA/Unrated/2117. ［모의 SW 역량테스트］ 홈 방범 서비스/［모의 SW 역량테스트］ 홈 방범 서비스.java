import java.util.*;
import java.lang.*;
import java.io.*;

class Home {
    int y;
    int x;
    public Home(int a, int b) {
        y = a;
        x = b;
    }
}

class Solution {
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int answer = 0;
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n][n];

            List<Home> homes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int now = Integer.parseInt(st.nextToken());
                    map[i][j] = now;
                    if (now == 1) {
                        homes.add(new Home(i, j));
                    }
                }
            }

            for (int cy = 0; cy < n; cy++) {
                for (int cx = 0; cx < n; cx++) {
                    int[] distCount = new int[400];
                    for (Home h : homes) {
                        int distance = Math.abs(h.y - cy) + Math.abs(h.x - cx);
                        distCount[distance]++;
                    }
                    int covered = 0;
                    for (int k = 1; k < 400; k++) {
                        covered += distCount[k - 1]; 
                        int money = k * k + (k - 1) * (k - 1);
                        if (covered * m >= money) {
                            answer = Math.max(answer, covered);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}
