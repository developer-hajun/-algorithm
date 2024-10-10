import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int to;
    int weight;
    int start;

    public Edge(int to, int weight, int start) {
        this.to = to;
        this.weight = weight;
        this.start = start;
    }
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}


public class prim {
    static List<Edge>[] list;
    static boolean[] visited = new boolean[6];
    public static void main(String[] args) throws FileNotFoundException {
        long beforeTime = System.currentTimeMillis();
        int[][] edges = {
                {0,1,3},
                {0,3,2},
                {0,4,4},
                {1,2,1},
                {1,3,4},
                {1,5,2},
                {2,5,1},
                {3,4,5},
                {3,5,7},
                {4,5,9}
        };
        list = new ArrayList[6];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>(); // 각 인덱스를 ArrayList로 초기화
        }
        for(int i=0;i<10;i++){
            int from = edges[i][0];
            int to = edges[i][1];
            int value = edges[i][2];
            list[from].add(new Edge(to,value,from));
            list[to].add(new Edge(from,value,to));
        }
        p(2);
        long afterTime = System.currentTimeMillis();
        System.out.println("running time: "+(afterTime-beforeTime)+"ms");
    }
    static void p(int start) {
        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0,0));
        List<Edge> ed=new ArrayList<>();
        while(!pq.isEmpty()) {
            Edge p = pq.poll();
            int node = p.to;
            int weight = p.weight;
            if(visited[node]) continue;
            // 선택한 간선의 정점으로부터 가장 낮은 가중치 갖는 정점 선택
            visited[node]= true;
            ed.add(p);
            for(Edge next : list[node]) {
                if(!visited[next.to]) {
                    pq.add(next);

                }
            }
        }
        ed.remove(0);
        for(Edge next : ed) {
            System.out.println(next.start+" "+next.to+" "+next.weight);
        }
    }
}