class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers,0,0,target);
        return answer;
    }
    
    public void dfs(int[] numbers,int value,int count,int target){
        if(count==numbers.length){
            if(value==target) answer++;
        }
        else{
            dfs(numbers,value+numbers[count],count+1,target);
            dfs(numbers,value-numbers[count],count+1,target);
        }
    }
    

}