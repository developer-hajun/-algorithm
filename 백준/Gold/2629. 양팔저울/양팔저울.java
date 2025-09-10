import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cu = Integer.parseInt(st.nextToken());
        boolean[][] dp = new boolean[cu+1][80001];
        dp[0][0]=true;
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=cu;i++){
            int value = Integer.parseInt(st.nextToken());
            for(int e=0;e<=40000;e++){
                if(dp[i-1][e]==false) continue;
                dp[i][e] = true;
                dp[i][Math.abs(e+value)]=true;
                dp[i][Math.abs(e-value)]=true;
            }
        }
        

        
        
        st = new StringTokenizer(br.readLine());

        int e = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=e;i++){
            int value = Integer.parseInt(st.nextToken());
            if(dp[cu][value]) System.out.print("Y ");
            else System.out.print("N ");
        }
        
        
    }
}