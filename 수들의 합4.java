import java.io.*;
import java.util.*;

public class Main {
    static long[][] arr;
    static Map<Long,Long> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //누적합
        map.put(0L,1L);
        //지금까지의 누적합을 구할떄에 n을 구해
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());

        long sum = 0;
        long count=0;
        for(int i =0;i<n;i++){
            sum+=Integer.parseInt(st.nextToken());

            if(map.containsKey(sum-k)){
                count+=map.get(sum-k);
            }

            if(map.containsKey(sum)){
                map.put(sum,map.get(sum)+1);
            }
            else{
                map.put(sum,1L);
            }
        }

        System.out.println(count);

    }
}

