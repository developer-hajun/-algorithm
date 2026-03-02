import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static int find_parents(int x, int[] parents){
        if(parents[x]!=x) parents[x]=find_parents(parents[x],parents);
        return parents[x];
    }

    public static void union(int l, int r, int[] parents){
        int l_p = find_parents(l,parents);
        int r_p = find_parents(r,parents);
        if(l_p==r_p) return;

        if(l_p<r_p) parents[r_p]=l_p;
        else parents[l_p]=r_p;
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] state = new int[n][3];
        int[] counts = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++) counts[i] = Integer.parseInt(st.nextToken());

        for(int i=0;i<3;i++){
            int[] parents = new int[n];
            for(int e=0;e<n;e++) parents[e]=e;

            int count = counts[i];
            for(int e=0;e<count;e++){
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                union(l,r,parents);
            }

            for(int e=0;e<n;e++){
                state[e][i] = find_parents(e, parents);
            }
        }

        Map<String, List<Integer>> map = new HashMap<>();

        for(int e = 0; e < n; e++){
            String key = state[e][0] + "," + state[e][1] + "," + state[e][2];
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(e+1);
        }

        List<List<Integer>> groups = new ArrayList<>();
        for(List<Integer> g : map.values()){
            if(g.size() >= 2) groups.add(g);
        }

        groups.sort(Comparator.comparingInt(g -> Collections.min(g)));

        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append("\n");
        for(List<Integer> g : groups){
            Collections.sort(g);
            for(int k : g) sb.append(k).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}