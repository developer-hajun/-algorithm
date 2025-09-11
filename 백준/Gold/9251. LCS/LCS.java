import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String l = br.readLine();
        String r = br.readLine();
        char[] left = new char[l.length()+1];
        char[] right =  new char[r.length()+1];
        int[][] dp = new int[l.length()+1][r.length()+1];

        for(int i=1;i<=l.length();i++) left[i]=l.charAt(i-1);
        for(int i=1;i<=r.length();i++) right[i]=r.charAt(i-1);

        for(int i=1;i<left.length;i++){
            for(int j=1;j<right.length;j++){
                if(left[i]==right[j]){
                    dp[i][j] = Math.max(dp[i-1][j-1]+1,Math.max(dp[i-1][j],dp[i][j-1]));
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[l.length()][r.length()]);

        
    }
}