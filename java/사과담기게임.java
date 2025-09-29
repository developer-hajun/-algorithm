import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()),m = Integer.parseInt(st.nextToken());
        int start = 1,end=m,count=0;;
        int j = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for(int i=0;i<j;i++){
            int now = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            if(start<=now && now<=end) continue;
            else if(now<start){
                int move = start-now;
                start-=move;
                end-=move;
                count+=move;
            }
            else{
                int move = now-end;
                start+=move;
                end+=move;
                count+=move;
            }
        }
        System.out.println(count);
    }
}