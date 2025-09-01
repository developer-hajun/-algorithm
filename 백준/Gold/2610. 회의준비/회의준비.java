import java.util.*;
import java.io.*;

public class Main {
    static int[] parents;

    public static int find(int x){
        if(parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa != pb){
            if(pa < pb) parents[pb] = pa;
            else parents[pa] = pb;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        parents = new int[N+1];
        for(int i=1; i<=N; i++) parents[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
            union(u,v);
        }

        HashMap<Integer, ArrayList<Integer>> components = new HashMap<>();
        for(int i=1; i<=N; i++){
            int root = find(i);
            components.putIfAbsent(root, new ArrayList<>());
            components.get(root).add(i);
        }

        ArrayList<Integer> leaders = new ArrayList<>();

        for(ArrayList<Integer> committee : components.values()){
            int leader = -1;
            int minMaxDist = Integer.MAX_VALUE;

            for(int candidate : committee){
                Queue<Integer> q = new ArrayDeque<>();
                int[] dist = new int[N+1];
                boolean[] visited = new boolean[N+1];
                q.add(candidate);
                visited[candidate] = true;
                int maxDist = 0;

                while(!q.isEmpty()){
                    int node = q.poll();
                    for(int nxt : graph[node]){
                        if(!visited[nxt]){
                            visited[nxt] = true;
                            dist[nxt] = dist[node] + 1;
                            maxDist = Math.max(maxDist, dist[nxt]);
                            q.add(nxt);
                        }
                    }
                }

                if(maxDist < minMaxDist){
                    minMaxDist = maxDist;
                    leader = candidate;
                }
            }
            leaders.add(leader);
        }

        Collections.sort(leaders);
        System.out.println(leaders.size());
        for(int l : leaders) System.out.println(l);
    }
}
