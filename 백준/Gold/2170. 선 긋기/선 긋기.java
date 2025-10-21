
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{x, y};
        }

   
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int start = arr[0][0];
        int end = arr[0][1];
        int answer = 0;

        for (int i = 1; i < n; i++) {
            int s = arr[i][0];
            int e = arr[i][1];

            if (end < s) {  
                answer += end - start;
                start = s;
                end = e;
            } else {    
                end = Math.max(end, e);
            }
        }
        answer += end - start;
        System.out.print(answer);
    }
}
