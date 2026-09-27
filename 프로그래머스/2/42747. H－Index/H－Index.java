import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        for(int i=0;i<citations.length ;i++){
            if(citations[i]>=citations.length-i) return citations.length-i;
        }
        return 0;
    }
}
//h번 인용된 논문이 h번이상, 나머지 h번 이하