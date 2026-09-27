import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes,(o1,o2)-> o1[1]-o2[1]);
        int now = -Integer.MAX_VALUE;
        for(int[] route : routes){
            if(route[0]>now){
                now=route[1];
                answer++;
            }
        }
        
        
        return answer;
    }
}