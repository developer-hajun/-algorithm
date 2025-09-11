

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  
        int m = Integer.parseInt(st.nextToken()); 
        int l = Integer.parseInt(st.nextToken());  
        int k = Integer.parseInt(st.nextToken());  

        int[][] stars = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxCovered = 0;

 
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int x1 = stars[i][0];  
                int y1 = stars[j][1];  

                int count = 0;

                for (int t = 0; t < k; t++) {
                    int sx = stars[t][0];
                    int sy = stars[t][1];

                    if (sx >= x1 && sx <= x1 + l && sy >= y1 && sy <= y1 + l) {
                        count++;
                    }
                }

                maxCovered = Math.max(maxCovered, count);
            }
        }

        System.out.println(k - maxCovered);  
    }
}
