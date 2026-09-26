import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int s : scoville) queue.add(s);
        
        while(queue.size()!=1){
            if(queue.peek()>=K) return answer;
            queue.add(queue.poll()+(queue.poll()*2));
            answer++;
        }
        return queue.peek()>=K ? answer : -1;
    }
}