import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] lines = new int[n][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i]= new int[]{s,e};
        }

        int start = -Integer.MAX_VALUE;
        int end = -Integer.MAX_VALUE;
        long answer = 0;

        for(int[] line : lines){
            int sta = line[0],en=line[1];
            if(end<sta){
                answer+= end-start;
                start=sta;
                end = en;
            }
            else{
                end =Math.max(en,end);
            }
        }
        answer+= end-start;

        System.out.println(answer);
        
    }
}