import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> answer = new TreeSet<>();
        for(String now : operations){
            String[] co = now.split(" ");
            char command = co[0].charAt(0);
            int num = Integer.parseInt(co[1]);
            if(command=='I') answer.add(num);
            else if(answer.size()==0) continue;
            else if(num==1) answer.pollLast();
            else answer.pollFirst();
        }
        if(answer.size()==0) return new int[]{0,0};
        return new int[]{answer.last(),answer.first()};
    }
}