import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n,m;
	static int[] sum;
	static int[][] dp;
	static boolean[][] checked;
	static int Init = -32768*100;

    public static int dfs(int last,int depth){
        if(depth == 0 ) return 0;		
		if(last < 0 ) return Init;
        if(checked[last][depth]) return dp[last][depth];
        checked[last][depth]=true;
        dp[last][depth] = dfs(last-1,depth);
        //해당 칸으로 안나눳을경우
        for(int i=last;i>0;i--){
            dp[last][depth] = Math.max(dp[last][depth],dfs(i-2,depth-1)+(sum[last]-sum[i-1]));
            //i-1까지 썻을경우
        }
        return dp[last][depth];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		sum = new int[n+1];
		for(int i=1; i<n+1; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(br.readLine());	
		}
        //누적합
        dp = new int[n+1][m+1];
		checked = new boolean[n+1][m+1];
        for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], Init);			
		}
        //초기화
        dfs(n,m);
        //실행
        System.out.println(dp[n][m]);
    }
}