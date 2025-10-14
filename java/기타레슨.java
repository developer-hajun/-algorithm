import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] song = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            song[i]=Integer.parseInt(st.nextToken());
        }


        int start = 0;
        int end = Arrays.stream(song).sum();

        int answer = Integer.MAX_VALUE;
        while(start<=end){
            int mid = (start+end)/2;

            boolean check = true;
            
         

            int now = 1;
            int song_value=0;
            for(int i : song){
                if(i > mid){ 
                    check = false;
                    break; 
                }
                if(song_value+i>mid){
                    song_value=i;
                    now++;
                    if(now>m){
                        check=false;
                        break;
                    }
                }
                else{
                    song_value+=i;
                }
            }
            if(check){
                answer=Math.min(mid,answer);
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        System.out.println(answer);
    }
}
