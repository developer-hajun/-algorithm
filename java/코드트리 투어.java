import java.util.*;
import java.io.*;

class travel{
    int id;
    boolean canSell;
    int revenue;
    int cost;
    int dest;
    public travel(int id,int revenue,int cost,int dest){
        this.id = id;
        this.revenue =revenue;
        this.cost= cost;
        this.dest=dest;
        if(revenue>=cost) canSell=true;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int start = 0;
    static List<int[]>[] edges;
    static TreeSet<travel> travels;
    static travel[] travelById = new travel[30001];
    static int[] distance;

    public static void dijkstra(){
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start]=0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
        queue.add(new int[]{start,0});
        while(!queue.isEmpty()){
            int now[] = queue.poll();
            int node = now[0];
            int dist = now[1];
            if(distance[node]<dist) continue;
            for(int[] next : edges[node]){
                int next_node = next[0];
                int next_distance = dist + next[1];
                if(distance[next_node]<=next_distance) continue;
                distance[next_node]=next_distance;
                queue.add(new int[]{next_node,next_distance});
            }
        }

    }

    public static void make_land(){
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edges = new ArrayList[n];
        travels = new TreeSet<>((o1, o2) -> {
            int profit1 = o1.revenue - o1.cost;
            int profit2 = o2.revenue - o2.cost;
            if (profit1 != profit2) return Integer.compare(profit2, profit1);
            return Integer.compare(o1.id, o2.id);
        });
        for(int i=0;i<n;i++) 
            edges[i]=new ArrayList<>();
        for(int i=0;i<m;i++){
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[v].add(new int[]{u,w});
            edges[u].add(new int[]{v,w});
        }
        distance = new int[n];
        dijkstra();
    }

    public static void make_travel(){
        int id = Integer.parseInt(st.nextToken());
        int revenue = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        int cost = distance[dest];

        travel t = new travel(id,revenue,cost,dest);
        travelById[id] = t;
        travels.add(t);

    }

    public static void cancel(){
       int id = Integer.parseInt(st.nextToken());
       if(travelById[id]==null) return;
       travel t = travelById[id];
       travels.remove(t);
       travelById[id]= null;
    }

    public static void sell(){
    	//for(travel e : travels)  System.out.println(e.id+","+e.revenue+","+e.cost+","+e.canSell);
  
    	if(travels.isEmpty()||travels.first().canSell==false) {
    		System.out.println(-1);
    		return;
    	}
    	System.out.println(travels.pollFirst().id);
    }


    public static void change_start(){
        start = Integer.parseInt(st.nextToken());
        dijkstra();
        TreeSet<travel> new_travel = new TreeSet<>((o1, o2) -> {
            int profit1 = o1.revenue - o1.cost;
            int profit2 = o2.revenue - o2.cost;
            if (profit1 != profit2) return Integer.compare(profit2, profit1);
            return Integer.compare(o1.id, o2.id);
        });

        for(travel t : travels){
            t.cost = distance[t.dest];
            if(t.revenue>=t.cost) t.canSell=true;
            else t.canSell=false;
            new_travel.add(t);
        }
        travels=new_travel;
    }


    public static void main(String[] args) throws Exception {
        int q = Integer.parseInt(br.readLine());
        for(int i=0;i<q;i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch(command){
                case 100:
                    make_land();
                    break;
                case 200:
                    make_travel();
                    break;
                case 300:
                    cancel();
                    break;
                case 400:
                    sell();
                    break;
                case 500:
                    change_start();
                    break;    
            }
        }
    }
}
