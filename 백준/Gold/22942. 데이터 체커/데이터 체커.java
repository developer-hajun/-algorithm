import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  

        int[][] value = new int[n * 2][2];  

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            value[i * 2] = new int[] { i, x - r, 0 }; 
            value[i * 2 + 1] = new int[] { i, x + r, 1 };
        }


        Arrays.sort(value, (o1, o2) -> o1[1] - o2[1]);

        Stack<int[]> stack = new Stack<>();  

        for (int i = 0; i < n * 2; i++) {
            if (value[i][2] == 0) {
                stack.push(value[i]);
            } 
            else if (value[i][2] == 1) {
                if (!stack.isEmpty() && stack.peek()[0] == value[i][0]) {
                    stack.pop();
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");

    }
}
