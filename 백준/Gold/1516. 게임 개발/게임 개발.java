import java.util.*;
import java.io.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] wait = new int[n]; 
        int[] wait_time = new int[n];
        List<Integer>[] in = new ArrayList[n]; 
        int[] wait_count = new int[n];

        for(int i=0; i<n; i++){
            in[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            wait[i] = Integer.parseInt(st.nextToken());
            while(true){
                int val = Integer.parseInt(st.nextToken());
                if(val == -1) break;
                in[val-1].add(i); 
                wait_count[i]++;
            }
        }

        int[] answer = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            if(wait_count[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            
            answer[now] = wait_time[now] + wait[now];

            for(int next : in[now]){
                wait_time[next] = Math.max(wait_time[next], answer[now]);
                wait_count[next]--;
                
                if(wait_count[next] == 0){
                    queue.add(next);
                }
            }
        }

        for(int i : answer){
            System.out.println(i);
        }
    }
}