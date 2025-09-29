import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;
    static List<List<Integer>> tree=new ArrayList<>();

    public static void dfs(int now){
         sb.append(now).append(" ");
         for(int next:tree.get(now)){
             if(visit[next]) continue;
             visit[next]=true;
             dfs(next);
         }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree.get(start).add(end);
            tree.get(end).add(start);
        }
        for(int i=1;i<=n;i++){
            Collections.sort(tree.get(i));
        }
        visit= new boolean[n+1];
        visit[v]=true;
        dfs(v);
        sb.append("\n");
        visit= new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visit[v]=true;
        while(!queue.isEmpty()){
            int now = queue.poll();
            sb.append(now).append(" ");
            for(int next:tree.get(now)){
                if(visit[next]) continue;
                visit[next]=true;
                queue.add(next);
            }
        }
        System.out.println(sb.toString());
    }
}