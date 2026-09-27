import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0;i<n;i++){
            if(visit[i]) continue;
            
            visit[i]=true;
            queue.add(i);
            
            while(!queue.isEmpty()){
                int now = queue.poll();
                
                for(int next=0;next<n;next++){
                    if(visit[next]||computers[now][next]==0) continue;
                    visit[next]=true;
                    queue.add(next);
                }
                
            }
            answer++;
        }
        
        return answer;
    }
}