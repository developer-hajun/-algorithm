import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            arr[i]=new int[]{left,right};
        }
        Arrays.sort(arr,(o1,o2)->o1[0]-o2[0]);
        int now=-1;
        int answer=0;
        for(int i=0;i<n;i++){

            int start=arr[i][0];
            int end=arr[i][1];
            if(now<start) now=start;
            while(now<end){
                now+=l;
                answer++;
            }
        }
        System.out.println(answer);
    }
}

