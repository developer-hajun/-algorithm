import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] num = new int[n];
        int[] value = new int[n]; 
        int[] answer = new int[n];
        Arrays.fill(value,-1);
        
        for(int i=0;i<n;i++) num[i] = Integer.parseInt(st.nextToken());

        
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            int now = num[i];
            while(!stack.isEmpty() && num[stack.peek()]<=now){
                stack.pop();
            }
            answer[i]=stack.size();
            if(!stack.isEmpty()) value[i]=stack.peek()+1;
            stack.add(i);
        }
        stack = new Stack<>();
        for(int i=n-1;i>=0;i--){
            while(!stack.isEmpty() && num[stack.peek()] <= num[i]){
                stack.pop();
            }
            answer[i] += stack.size();
            if(!stack.isEmpty()){
                if(value[i] == -1 ||
                   Math.abs((value[i]-1) - i) > Math.abs(stack.peek() - i)){
                    value[i] = stack.peek() + 1;
                }
            }
            stack.push(i);
        }

        for(int i=0;i<n;i++){
            System.out.print(answer[i]+" ");
            if(answer[i]!=0) System.out.println(value[i]);
            else{
                System.out.println();
            }
        }
    }
}