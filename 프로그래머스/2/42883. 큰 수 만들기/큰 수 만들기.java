import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<number.length();i++){
            while(!stack.isEmpty()&&stack.peek()<number.charAt(i)-'0'&&k>0){
                stack.pop();
                k--;
            }
            stack.add(number.charAt(i)-'0');
        }
        for(int i=0;i<k;i++) stack.pop();
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        answer = sb.reverse().toString();
        
        return answer;
    }
}