import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        int[] answer = new int[n+1];
        Arrays.fill(answer,10000);
        answer[0]=0;
        
        for(int i=0;i<=n;i++){
            if(i>=3 && i>=5) answer[i] = Math.min(answer[i-3],answer[i-5])+1;
            else if(i>=3) answer[i] = answer[i-3]+1;
        }
        int ans = answer[n]>10000 ? -1 : answer[n]; 
        System.out.print(ans);
    }
}