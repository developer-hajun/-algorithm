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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] candy = new int[N+1];
        for(int i=1;i<=N;i++) candy[i]=Integer.parseInt(st.nextToken());
        
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
        Map<Integer,Integer> candys = new HashMap<>();
        Map<Integer,Integer> ide = new HashMap<>();
        for(int i=1;i<=N;i++) {
        	int root = find(i);
        	if(!candys.containsKey(root)) {
        		candys.put(root, 0);
        		ide.put(root, 0);
        	}
        	candys.put(root, candys.get(root)+candy[i]);
        	ide.put(root, ide.get(root)+1);
        }
        
        List<int[]> f = new ArrayList<>();
        for(int root : candys.keySet()) {
        	f.add(new int[] {ide.get(root),candys.get(root)});
        }
        
        int[][] dp = new int[candys.size()+1][K];
        for(int i=1;i<=f.size();i++) {
        	int[] now = f.get(i-1);
        	for(int j=0;j<K;j++) {
        		if(j<now[0]) dp[i][j] = dp[i-1][j];
        		else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-now[0]]+now[1] );
        	}
        }
        System.out.println(dp[candys.size()][K-1]);

        
    }
}
