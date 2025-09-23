import java.util.*;
import java.lang.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int node;
    int value;
    public Edge(int a,int b){
        node = a;
        value = b;
    }
    @Override
    public int compareTo(Edge o){
        return Integer.compare(this.value, o.value);
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Edge>> edge = new ArrayList<>();
        for(int i=0; i<=n; i++) edge.add(new ArrayList<>());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge.get(a).add(new Edge(b, c));
        }

        List<PriorityQueue<Integer>> answer = new ArrayList<>();
        for(int i=0; i<=n; i++) 
            answer.add(new PriorityQueue<>((o1,o2) -> {return o2-o1;}));

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        // 출발 노드 초기화
        answer.get(1).add(0);
        edges.add(new Edge(1, 0));

        while(!edges.isEmpty()){
            Edge now = edges.poll();
            int node = now.node;
            int value = now.value;

            for(Edge next : edge.get(node)){
                int next_node = next.node;
                int next_value = next.value + value;

                PriorityQueue<Integer> pq = answer.get(next_node);
                if(pq.size() < k){
                    pq.add(next_value);
                    edges.add(new Edge(next_node, next_value));
                }
                else if(pq.peek() > next_value){
                    pq.poll();
                    pq.add(next_value);
                    edges.add(new Edge(next_node, next_value));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            PriorityQueue<Integer> pq = answer.get(i);
            if(pq.size() < k) sb.append(-1);
            else sb.append(pq.peek());
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
