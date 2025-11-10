import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] color = new int[n+1];
        for(int i = 1; i <= n; i++) {
            char now = st.nextToken().charAt(0);
            if (now == 'R') color[i]=0;
            else if (now == 'G') color[i]=1;
            else color[i]=2;
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<int[]>[] road = new List[m + 1];
        for (int i = 0; i <= m; i++) road[i] = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            char now = st.nextToken().charAt(0);
            int val = 0;
            if (now == 'R') val = 0;
            else if (now == 'G') val = 1;
            else val = 2;
            road[l].add(new int[]{r, val});
            road[r].add(new int[]{l, val});
        }
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i],-Integer.MAX_VALUE);
        dp[0][1] = 0;
        for(int i = 1;i<=n;i++){
            int value = color[i];
            for(int j=1;j<=m;j++){
                if(dp[i-1][j]<0) continue;
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                for(int[] next : road[j]){
                    dp[i][next[0]] = Math.max(dp[i][next[0]],dp[i-1][j]+(value==next[1] ? 10 : 0));
                }
            }
        }
        System.out.println(Arrays.stream(dp[n]).max().getAsInt());
        
    }
}