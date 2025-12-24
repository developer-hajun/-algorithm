import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken())-1;
        int d = Integer.parseInt(st.nextToken())-1;

        List<int[]>[] edge = new ArrayList[n];
        for(int i=0;i<n;i++) edge[i] = new ArrayList<>();

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            edge[a].add(new int[]{b,w});
            edge[b].add(new int[]{a,w});
        }

        int[] up = new int[k+1];
        up[1] =  Integer.parseInt(br.readLine());
        for(int i=2;i<=k;i++){
            up[i]= up[i-1]+ Integer.parseInt(br.readLine());
        }


        int[][] dist = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE/2);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->{return o1[1]-o2[1];});
        queue.add(new int[]{s,0,0});//현재위치,현재 값,현재 이동횟수
        dist[0][s]=0;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int w = now[0];
            int val = now[1];
            int count = now[2];
            if (dist[count][w] < val) continue;
            if(count==n-1) continue;
            for(int[] next : edge[w]){
                int nw = next[0];
                int nval = next[1]+val;
                int ncount= count+1;
                if(dist[ncount][nw]<=nval) continue;
                dist[ncount][nw] = nval;
                queue.add(new int[]{nw,nval,ncount});
            }
        }

        for(int i=0;i<=k;i++){
            int answer = Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                int val = dist[j][d]+up[i]*j;
                answer = Math.min(answer,val);
            }
            System.out.println(answer);
        }
    }
}