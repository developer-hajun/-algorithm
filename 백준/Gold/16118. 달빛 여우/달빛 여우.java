import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class node{
        int now;
        double value;
        int state;

        public node(int n,double v,int s){
            now=n; value=v; state=s;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] channel = new ArrayList[n+1];
        for(int i=0;i<=n;i++) channel[i]= new ArrayList<>();

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            channel[l].add(new int[]{r,d});
            channel[r].add(new int[]{l,d});
        }

        double[] fox = new double[n + 1];
        double[][] wolf = new double[n + 1][2];

        Arrays.fill(fox, Double.MAX_VALUE);
        for(int i=1;i<=n;i++){
            Arrays.fill(wolf[i], Double.MAX_VALUE);
        }


        PriorityQueue<node> foxPQ = new PriorityQueue<>(
                (a,b)-> Double.compare(a.value,b.value)
        );

        fox[1] = 0;
        foxPQ.add(new node(1,0,0));

        while(!foxPQ.isEmpty()){
            node now = foxPQ.poll();
            if(fox[now.now] < now.value) continue;

            for(int[] next : channel[now.now]){
                int nextNode = next[0];
                int dist = next[1];

                double nextValue = now.value + dist;

                if(fox[nextNode] > nextValue){
                    fox[nextNode] = nextValue;
                    foxPQ.add(new node(nextNode,nextValue,0));
                }
            }
        }
        

        PriorityQueue<node> wolfPQ = new PriorityQueue<>(
                (a,b)-> Double.compare(a.value,b.value)
        );

        wolf[1][0] = 0; 
        wolfPQ.add(new node(1,0,0));

        while(!wolfPQ.isEmpty()){
            node now = wolfPQ.poll();
            if(wolf[now.now][now.state] < now.value) continue;

            for(int[] next : channel[now.now]){
                int nextNode = next[0];
                int dist = next[1];

                if(now.state == 0){ 
                    double nextValue = now.value + dist / 2.0;

                    if(wolf[nextNode][1] > nextValue){
                        wolf[nextNode][1] = nextValue;
                        wolfPQ.add(new node(nextNode,nextValue,1));
                    }
                }else{
                    double nextValue = now.value + dist * 2.0;

                    if(wolf[nextNode][0] > nextValue){
                        wolf[nextNode][0] = nextValue;
                        wolfPQ.add(new node(nextNode,nextValue,0));
                    }
                }
            }
        }
        

        int count = 0;
        for(int i = 2; i <= n; i++){
            if(fox[i] < Math.min(wolf[i][0], wolf[i][1])){
                count++;
            }
        }

        System.out.println(count);
    }
}