import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] now = new int[m];

        st = new StringTokenizer(br.readLine());
        int su=0;

        long answer= 0; 

        for(int i=0;i<n;i++){
            int value = Integer.parseInt(st.nextToken());
            su = (su+value)%m;
            if(su==0) answer++;
            now[su]++;
        }
        
        for(int ne : now){
            long count = (long)ne; 
            answer += (count * (count - 1) / 2);
        }
        System.out.println(answer);
        
    }
}