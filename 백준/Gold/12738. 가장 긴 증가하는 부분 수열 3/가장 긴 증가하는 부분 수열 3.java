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
        int[] dp = new int[n+1];
        dp[0]=-Integer.MAX_VALUE;

        int len = 0;
        for(int i=0;i<n;i++){
            if(num[i]>dp[len]) {
            	len++;
            	dp[len]=num[i];
            }
            else {
            	int idx = binarySearch(1, len, num[i],dp);
                dp[idx] = num[i];
            }
        }
        System.out.println(len);
    }
    
   public static int binarySearch(int start, int end,int target,int[] dp) {
	   while(start<end) {
		   int mid=(start+end)/2;
		   if(dp[mid]>=target) {
			   end = mid;
		   }
		   else {
			   start=mid+1;
		   }
	   }
	   return end;
   }
}