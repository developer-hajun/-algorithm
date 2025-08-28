import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) graph[i]=new ArrayList<>();

        for(int i=0;i<M;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[N+1];
        ArrayList<Integer> leaders = new ArrayList<>();

        for(int i=1;i<=N;i++){
            if(!visited[i]){
                Queue<Integer> q = new ArrayDeque<>();
                q.add(i);
                visited[i]=true;

                ArrayList<Integer> committee = new ArrayList<>();
                committee.add(i);

                while(!q.isEmpty()){
                    int node = q.poll();
                    for(int nxt : graph[node]){
                        if(!visited[nxt]){
                            visited[nxt]=true;
                            q.add(nxt);
                            committee.add(nxt);
                        }
                    }
                }

                int leader = -1;
                int minMaxDist = Integer.MAX_VALUE;
                for(int candidate : committee){
                    Queue<Integer> bfs = new ArrayDeque<>();
                    int[] dist = new int[N+1];
                    boolean[] vis = new boolean[N+1];
                    bfs.add(candidate);
                    vis[candidate]=true;
                    dist[candidate]=0;
                    int maxDist = 0;

                    while(!bfs.isEmpty()){
                        int node = bfs.poll();
                        for(int nxt : graph[node]){
                            if(!vis[nxt] && committee.contains(nxt)){
                                vis[nxt]=true;
                                dist[nxt]=dist[node]+1;
                                maxDist = Math.max(maxDist, dist[nxt]);
                                bfs.add(nxt);
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
        }

        Collections.sort(leaders);
        System.out.println(leaders.size());
        for(int l : leaders) System.out.println(l);
    }
}
