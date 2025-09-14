import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] num = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) num[i] = Long.parseLong(st.nextToken());

        long[] dp = new long[n+1];
        int[] pos = new int[n];       
        dp[0] = Long.MIN_VALUE;

        int len = 0;
        for(int i=0;i<n;i++){
            if(num[i] > dp[len]) {
                dp[++len] = num[i];
                pos[i] = len;
            } else {
                int idx = binarySearch(1, len, num[i], dp);
                dp[idx] = num[i];
                pos[i] = idx;
            }
        }

        long[] lis = new long[len];
        int cur = len;
        for(int i=n-1;i>=0;i--){
            if(pos[i] == cur) {
                lis[cur-1] = num[i];
                cur--;
            }
        }

  
        System.out.println(len);
        for(long x : lis) System.out.print(x + " ");
    }


    public static int binarySearch(int left, int right,long target,long[] dp) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(dp[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
