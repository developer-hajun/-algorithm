import java.util.*;
class Solution {
    static boolean[] visit;
    static HashMap<String,HashMap<Integer,String>> ticket = new HashMap<>();
    static Deque<String> answer = new LinkedList<>();
    static boolean find = false;
    static int count=0;
    
    public String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];
        for(int i=0;i<tickets.length;i++){
            String start = tickets[i][0];
            String end = tickets[i][1];
            if(!ticket.containsKey(start)) ticket.put(start,new HashMap<>());
            ticket.get(start).put(i,end);
        }
        answer.add("ICN");
        count=tickets.length;
        dfs("ICN",0);
        String[] ans = new String[answer.size()];
        int cc=0;
        while(!answer.isEmpty()) {ans[cc] = answer.pollFirst();cc++;}
        return ans;
    }
    
    public void dfs(String now,int num){
        if(num==count){ find=true; return;}
        HashMap<Integer,String> now_ticket = ticket.get(now);
        if(now_ticket == null) return; 
        List<Integer> keys = new ArrayList<>(now_ticket.keySet());
        keys.sort((a, b) -> now_ticket.get(a).compareTo(now_ticket.get(b)));
        
        for(int next : keys){
            if(visit[next]) continue;
            visit[next] = true;
            String move = now_ticket.get(next);
            answer.add(move);
            dfs(move,num+1);
            if(find) return;
            answer.pollLast();
            visit[next]=false;
        }
    }
}