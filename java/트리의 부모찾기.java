import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] parent;
    static List<List<Integer>> tree = new ArrayList<>();
    public static void dfs(int start){
        for(int i : tree.get(start)){
            if(parent[i]!=0) continue;     
            parent[i]=start;   
            dfs(i);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree.get(start).add(end);
            tree.get(end).add(start);
        }
        dfs(1);
        for(int i=2;i<=n;i++){
            System.out.println(parent[i]);
        }
    }
}