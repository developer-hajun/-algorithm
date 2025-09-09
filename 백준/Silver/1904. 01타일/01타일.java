import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        if(n<=2){
             System.out.println(n);
            return;
        }
        int[] answer = new int[n+1];
        answer[1]=1;
        answer[2]=2;
        for(int i=3;i<=n;i++){
            answer[i] = (answer[i-1]+answer[i-2])%15746;
        }
        System.out.println(answer[n]);
    }
}