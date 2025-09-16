import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] mmin = new int[3];
        int[] mmax = new int[3];
        int[] cur = new int[3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            
            for (int j = 0; j < 3; j++) {
                cur[j] = Integer.parseInt(st.nextToken());
            }

            int min0 = mmin[0], min1 = mmin[1], min2 = mmin[2];
            int max0 = mmax[0], max1 = mmax[1], max2 = mmax[2];

            mmin[0] = cur[0] + Math.min(min0, min1);
            mmin[1] = cur[1] + Math.min(Math.min(min0, min1), min2);
            mmin[2] = cur[2] + Math.min(min1, min2);

            mmax[0] = cur[0] + Math.max(max0, max1);
            mmax[1] = cur[1] + Math.max(Math.max(max0, max1), max2);
            mmax[2] = cur[2] + Math.max(max1, max2);
        }

        System.out.println(
            Math.max(Math.max(mmax[0], mmax[1]), mmax[2]) + " " +
            Math.min(Math.min(mmin[0], mmin[1]), mmin[2])
        );
    }
}
