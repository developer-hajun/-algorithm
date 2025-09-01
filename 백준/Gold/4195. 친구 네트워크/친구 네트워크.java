import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int find_parents(int x){
        if(parents[x]!=x) parents[x] = find_parents(parents[x]);
        return parents[x];
    }

    public static void union(int left,int right,int left_parent,int right_parent){
        if(left_parent<right_parent){
            parents[right_parent] = left_parent;
            size[left_parent] += size[right_parent];
        }
        else{
            parents[left_parent] = right_parent;
            size[right_parent] += size[left_parent];
        }
    }

    static int[] parents;
    static int[] size;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int tc=0;tc<n;tc++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            
            
            
    
            Queue<int[]> queue = new ArrayDeque<>();
    
            Map<String,Integer> save = new HashMap<>();
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                String u = st.nextToken();
                String v = st.nextToken();
                if(!save.containsKey(u)) save.put(u,save.size());
                if(!save.containsKey(v)) save.put(v,save.size());
                int start = save.get(u);
                int end = save.get(v);
                queue.add(new int[]{start,end});
            }
            parents = new int[save.size()];
            size= new int[save.size()];
            for(int i=0;i<save.size();i++){
                parents[i]=i;
                size[i]=1;
            }

            int answer = 0;
            while(!queue.isEmpty()){
                int[] now = queue.poll();
                int lp = find_parents(now[0]);
                int rp = find_parents(now[1]);
                if(lp!=rp){
                    union(now[0],now[1],lp,rp);
                }
                
                int parent = Math.min(lp, rp); 
                System.out.println(size[parent]);
            }
        }
    }
}