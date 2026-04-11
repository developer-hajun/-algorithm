import java.util.*;
class Solution {
    
    Set<Integer>[][] winner;
    
    public int solution(int n, int[][] results) {
        winner = new HashSet[2][n+1];
        List<Integer> move[] = new ArrayList[n+1];
        boolean[] visit = new boolean[n+1];
        for(int i=0;i<=n;i++){
            move[i] = new ArrayList<>();
            winner[0][i] = new HashSet<>();
            winner[1][i] = new HashSet<>();
        }
        for(int[] r : results) move[r[0]].add(r[1]);
        for(int i=1;i<=n;i++){
            if(visit[i]) continue;
            dfs(move,visit,i,0);
        }
        move = new ArrayList[n+1];
        visit = new boolean[n+1];
        for(int i=0;i<=n;i++) move[i] = new ArrayList<>();
        for(int[] r : results) move[r[1]].add(r[0]);
        for(int i=0;i<=n;i++){
            if(visit[i]) continue;
            dfs(move,visit,i,1);
        }
        int answer=0;
        for(int i=1;i<=n;i++){
            if(winner[0][i].size()+winner[1][i].size()==n-1) answer++;
        }
        
        
        return answer;
    }
    
    public Set<Integer> dfs(List<Integer>[] move,boolean[] visit,int now,int w){
        if(visit[now]) return winner[w][now];
        visit[now]=true;
        for(int next:move[now]){
            winner[w][now].addAll(dfs(move,visit,next,w));
            winner[w][now].add(next);
        }
        return winner[w][now];
        
        
    }
    
}