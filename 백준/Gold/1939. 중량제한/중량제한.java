import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] bridge = new ArrayList[n+1];

        for(int i=1;i<=n;i++) bridge[i] = new ArrayList<>();

        int l = 1;
        int r = -1;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bridge[a].add(new int[]{b,c});
            bridge[b].add(new int[]{a,c});
            r=Math.max(r,c);
        }

        st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        
        int answer= -1;

        
        while(l<=r){
            int mid = (l+r)/2;
            boolean[] visit = new boolean[n+1];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(left);
            visit[left]=true;

            while(!queue.isEmpty()){
                int now = queue.poll();
                if(visit[right]) break;
                for(int[] move : bridge[now]){
                    if(move[1]<mid||visit[move[0]]) continue;
                    queue.add(move[0]);
                    visit[move[0]]=true;
                }
            }
            if(visit[right]){
                answer=Math.max(mid,answer);
                l=mid+1;
            }
            else{
                r=mid-1;
            }
        }
        
        System.out.println(answer);
        
    }
}