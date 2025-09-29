import java.util.*;
import java.lang.*;
import java.io.*;

class Edge{
    int node;
    int value;
    public Edge(int a,int b){
        node=a;
        value=b;
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
        for(int i=0;i<=n;i++) edge.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge.get(a).add(new Edge(b,c));
        }
        List<PriorityQueue<Integer>> answer = new ArrayList<>();
        for(int i=0;i<=n;i++) answer.add(new PriorityQueue<>((o1,o2)->{return o2-o1;}));
        PriorityQueue<Edge> edges = new PriorityQueue<>((o1,o2)->{return o1.value-o2.value;});
        answer.get(1).add(0);
        edges.add(new Edge(1,0));
        //방문할때 answer[i]의 size가 2보다 작다면 가기 아니라면 해당 answer[i].peek의 값보다 작으면 빼고 넣기
        while(!edges.isEmpty()){
            Edge now = edges.poll();
            int node = now.node;
            int value = now.value;
            for(Edge nodes:edge.get(node)){
                int next_node = nodes.node;
                int next_value = nodes.value+value;
                if(answer.get(next_node).size()<k){
                    answer.get(next_node).add(next_value);
                    edges.add(new Edge(next_node,next_value));
                }
                else if(answer.get(next_node).peek()>next_value){
                    answer.get(next_node).poll();
                    answer.get(next_node).add(next_value);
                    edges.add(new Edge(next_node,next_value));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            if(answer.get(i).size()<k) sb.append(-1);
            else sb.append(answer.get(i).poll());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}