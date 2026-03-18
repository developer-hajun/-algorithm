import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int max_value = 0;
        
        boolean[] visit = new boolean[n+1];
        HashMap<Integer,Integer> count = new HashMap<>();
        count.put(0,1);
        List<Integer>[] edges = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++) edges[i]= new ArrayList<>();
        for(int i=0;i<edge.length;i++){
            edges[edge[i][0]].add(edge[i][1]);
            edges[edge[i][1]].add(edge[i][0]);
        }
        visit[1]=true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1,0});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int wh = now[0];
            int co = now[1]+1;
            for(int next : edges[wh]){
                if(visit[next]) continue;
                visit[next]=true;
                
                max_value = Math.max(max_value,co);
                if(!count.containsKey(co)) count.put(co,1);
                else count.put(co,count.get(co)+1);
                
                queue.add(new int[]{next,co});
            }
        }
        
        return count.get(max_value);
    }
}