import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int answer = Integer.MAX_VALUE;
        
        int min =0;
        int max =distance;
        
        while(min<=max){
            int now = (min+max)/2;
            int delete = 0;
            int start = 0;
            
            for(int i=0;i<rocks.length;i++){
                int r = rocks[i];
                if(r-start<now){
                    delete+=1;
                }
                else{
                    start = r;
                }
            }
            
            if(distance-start<now) delete+=1;
            
            if(delete<=n){
                answer = now;
                min = now+1;
            }
            else{
                max = now-1;
            }
        }
        
        return answer;
    }
}