import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        int answer = -Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) arr[i]=arr[i-1]+Integer.parseInt(st.nextToken());
        for(int i=k;i<=n;i++) answer = Math.max(answer,arr[i]-arr[i-k]);
        System.out.println(answer);
    }
}
