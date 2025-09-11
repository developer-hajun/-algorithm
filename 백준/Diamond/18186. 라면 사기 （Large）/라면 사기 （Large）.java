

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] A = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

  
        if (B <= C) {
            long totalCost = 0L;
            for (int i = 0; i < N; i++) {

                totalCost += (long) A[i] * B;
            }
            System.out.println(totalCost);
            return; 
        }


        long ans = 0; 
        int idx = 0;
        while (idx < N) {
            if (A[idx] > 0) {
                int temp = A[idx];

                ans += (long) B * temp;
                temp = Math.min(temp, A[idx + 1]);
                ans += (long) C * temp;
                A[idx + 1] -= temp;
                temp = Math.min(temp, A[idx + 2] - Math.min(A[idx + 1], A[idx + 2]));
                ans += (long) C * temp;
                A[idx + 2] -= temp;
            }
            idx++;
        }
        System.out.println(ans);
    }
}