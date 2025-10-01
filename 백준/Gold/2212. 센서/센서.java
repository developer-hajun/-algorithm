import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());


        if (k >= n) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        int[] sensors = new int[n];
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(sensors);

 
        Integer[] dist = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            dist[i] = sensors[i + 1] - sensors[i];
        }


        Arrays.sort(dist);


        int answer = 0;
        for (int i = 0; i < n - k; i++) {
            answer += dist[i];
        }

        System.out.println(answer);
    }
}