import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i][1] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(input, (o1, o2) -> {

            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        long answer = 0;
        int preMax = input[0][1];
        int curMax = -1;
        for (int i = 0; i < n; i++) {

            if (preMax > input[i][0]) {

                if (preMax < input[i][1]) {
                    preMax = input[i][1];
                }

     
                int cnt = ((preMax - input[i][0]) + 29) / 30;
                input[i][0] += (cnt * 30);

                answer += cnt;
            }

            curMax = Math.max(curMax, input[i][0]);


            if (i + 1 < n && input[i][1] != input[i + 1][1]) {
                preMax = curMax;
            }
        }

        System.out.println(answer);

        br.close();
    }
}