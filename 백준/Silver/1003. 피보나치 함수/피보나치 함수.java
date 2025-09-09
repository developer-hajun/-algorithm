import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t =Integer.parseInt(st.nextToken());
        for(int tc=0;tc<t;tc++){
            st = new StringTokenizer(br.readLine());
            int n =Integer.parseInt(st.nextToken());
            int[][] fibo = new int[41][2];
            fibo[0] = new int[]{1,0};
            fibo[1] = new int[]{0,1};
            for(int i=2;i<=n;i++){
                fibo[i][0] = fibo[i-1][0]+fibo[i-2][0];
                fibo[i][1] = fibo[i-1][1]+fibo[i-2][1];
            }
            System.out.println(fibo[n][0] + " " +fibo[n][1]);
        }
    }
}