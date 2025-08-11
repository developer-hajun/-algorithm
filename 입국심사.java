import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] num = new long[n];
        for(int i=0;i<n;i++) num[i]= Long.parseLong(br.readLine());
        long left=0,right = Arrays.stream(num).min().getAsLong()*m;

        long answer = Long.MAX_VALUE;
        while(left<=right){
            long mid= (left+right)/2;
            long count = 0;
            for(long time:num){
                count+= mid/time;
            }
            if(count>=m){
                answer=Math.min(answer,mid);
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        System.out.println(answer);
        
    }
}

