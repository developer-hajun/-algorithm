import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        Integer[] num = new Integer[n]; // Use Integer[] instead of int[]
        
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }


        Arrays.sort(num, Collections.reverseOrder());
        Long answer=0L;
        for (int i = 0; i < n; i++) {
            answer+=Math.max(0,num[i]-i);
        }
        System.out.println(answer);
    }
}
