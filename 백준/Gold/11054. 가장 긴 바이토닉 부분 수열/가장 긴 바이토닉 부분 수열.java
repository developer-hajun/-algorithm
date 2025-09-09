import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        
        for(int i=0;i<n;i++) num[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(dp2,1);

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                
                if(num[j]<num[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        for(int i=n-2;i>=0;i--){
            for(int j=n-1;j>i;j--){
                if(num[j]<num[i]){
                    dp2[i] = Math.max(dp2[i],dp2[j]+1);
                }
            }
        }
        int[] answer =  new int[n];
        for(int i=0;i<n;i++) answer[i] =dp[i]+dp2[i]-1;
        System.out.println(Arrays.stream(answer).max().getAsInt());
    }
}