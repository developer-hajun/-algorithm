import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static class node{
        float x;float y;
        public node() throws Exception{
            st = new StringTokenizer(br.readLine());
            x = Float.parseFloat(st.nextToken());
            y = Float.parseFloat(st.nextToken());
        }
    }
     static class move{
        int end; float time;
        public move(int b, float c){ 
            end =b;
            time =c;
        }
    }
    static class go{
        int start; float time;
        public go(int b, float c){ 
            start =b;
            time =c;
        }
    }
    public static void main(String[] args) throws Exception {
        List<node> nodes = new ArrayList<>();
        for(int i=0;i<2;i++) nodes.add(new node());
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) nodes.add(new node());
        int Inf = Integer.MAX_VALUE;
        float[] dist = new float[nodes.size()];
        Arrays.fill(dist,Inf);
        dist[0]=0;
        List<move>[] moves = new ArrayList[nodes.size()];
        for(int i=0;i<nodes.size();i++) moves[i] = new ArrayList<>();
        
        for(int i=0;i<nodes.size();i++){
            for(int j=0;j<nodes.size();j++){
                if(i==j) continue;
                node one = nodes.get(i);
                node two = nodes.get(j);
                float distance = (float) Math.sqrt(Math.pow(one.x-two.x,2)+Math.pow(one.y-two.y,2));
                moves[i].add(new move(j,distance/5));
                if(i>=2) moves[i].add(new move(j,(Math.abs(distance-50)/5)+2));
            }
        }

        PriorityQueue<go> queue = new PriorityQueue<>((o1,o2)->{ return Float.compare(o1.time, o2.time);});
        queue.add(new go(0,0));//현재 위치, 시간

        while(!queue.isEmpty()){
            go now = queue.poll();
            int num = now.start;
            float count=now.time;

            if(count>dist[num]) continue;

            for(move m: moves[num]){
                int next = m.end;
                float next_dist = count+m.time;
                if(dist[next]<=next_dist) continue;
                dist[next] = next_dist;
                queue.add(new go(next,next_dist));
            }
        }
        System.out.println(dist[1]);

        
        
    }
}