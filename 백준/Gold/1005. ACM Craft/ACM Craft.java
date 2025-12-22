import java.util.*;
import java.io.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int INF = 1000000000;


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        for(int t=0;t<tc;t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] waitTime = new int[n];
            int[] wait = new int[n];
            int[] count= new int[n];
            List<Integer>[] nums= new ArrayList[n];
            st = new StringTokenizer(br.readLine());
            
            for(int i=0;i<n;i++){
                waitTime[i]=Integer.parseInt(st.nextToken());
                nums[i]=new ArrayList<>();
            }

            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                nums[x].add(y);
                count[y]++;
            }

            int answer = Integer.parseInt(br.readLine())-1;
            
            
            Queue<Integer> queue = new ArrayDeque<>();

            for(int i=0;i<n;i++){
                if(count[i]!=0) continue;
                queue.add(i);
            }

            while(!queue.isEmpty()){
                int now = queue.poll();
                int time = wait[now]+waitTime[now];
                if(now==answer){
                    System.out.println(time);
                    break;
                }
                for(int next : nums[now]){
                    count[next]--;
                    wait[next] = Math.max(wait[next],time);
                    if(count[next]==0){
                        queue.add(next);
                    }
                    
                }
            }
            
        }
        
    }
}
