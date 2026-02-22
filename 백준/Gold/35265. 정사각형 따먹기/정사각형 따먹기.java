import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());


            int[][] grid = new int[H + 2][W + 2];
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    grid[i][j] = 1;
                }
            }

            List<String> removed = new ArrayList<>();


            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = 1; i <= H; i++) {
                    for (int j = 1; j <= W; j++) {
                        if (grid[i][j] == 1) {
                            int exposedEdges = 0;
                            if (grid[i - 1][j] == 0) exposedEdges++;
                            if (grid[i + 1][j] == 0) exposedEdges++;
                            if (grid[i][j - 1] == 0) exposedEdges++;
                            if (grid[i][j + 1] == 0) exposedEdges++;

                            if (exposedEdges == 2) {
                                grid[i][j] = 0;
                                removed.add(j + " " + i);
                                changed = true;
                            }
                        }
                    }
                }
            }


            sb.append(removed.size()).append("\n");
            for (String s : removed) {
                sb.append(s).append("\n");
            }
        }
        System.out.print(sb);
    }
}