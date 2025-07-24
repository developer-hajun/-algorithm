import java.util.*;
class Solution {
    static boolean[] visit;
    static int[][] wires;
    static int n;

    public int bfs(){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());
        for(int i=0;i<wires.length;i++){
            if(visit[i]) continue;
            int l=wires[i][0]-1,r=wires[i][1]-1;
            graph.get(l).add(r);
            graph.get(r).add(l);
        }
        boolean[] node = new boolean[n];
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(node[i]) continue;
            Queue<Integer> queue = new ArrayDeque();
            queue.add(i);
            int value=1;
            node[i]=true;
            while(!queue.isEmpty()){
                int now = queue.poll();
                for(int next : graph.get(now)){
                    if(node[next]) continue;
                    node[next]=true;
                    queue.add(next);
                    value++;
                }
            }
            ans.add(value);
        }
        return Math.abs(ans.get(0)-ans.get(1));
    }

    public int solution(int n, int[][] wires) {
        int answer = 21000000;
        visit = new boolean[wires.length];
        this.n = n;
        this.wires = wires;

        for(int i=0;i<wires.length;i++){
            visit[i]=true; //전력망 끊기
            answer = Math.min(answer,bfs());
            visit[i]=false;
        }

        return answer;
    }
}