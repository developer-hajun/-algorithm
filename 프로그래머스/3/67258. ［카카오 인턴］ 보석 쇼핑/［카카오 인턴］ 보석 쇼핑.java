import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gem_count = new HashSet<>();
        for(String gem:gems){
            gem_count.add(gem);
        }
        Map<String,Integer> gem = new HashMap<>();
        int start=0;
        int[] answer = {Integer.MIN_VALUE/2,Integer.MAX_VALUE/2};
        for(int end=0;end<gems.length;end++){
            String value = gems[end];
            if(!gem.containsKey(value)) gem.put(value,1);
            else gem.put(value,gem.get(value)+1);
            
            while(start<=end && gem.size()==gem_count.size()){
                if((end-start)<(answer[1]-answer[0])){
                    answer= new int[]{start+1,end+1};
                }
                
                String val = gems[start];
                start++;
                gem.put(val,gem.get(val)-1);
                if(gem.get(val)==0){
                    gem.remove(val);
                }
            }
            
        }
        return answer;
    }
}
