import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        double end =-1;
        int answer=0;
        for(int i=0;i<n;i++){
            double value = arr[i];
            if(end<value-0.5||end<value+0.5){
                end =value-0.5+l;
                answer++;
            }
        }
        System.out.println(answer);
    }
}

