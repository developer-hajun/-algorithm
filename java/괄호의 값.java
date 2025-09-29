import java.util.*;

class Main {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        for(int i=0;i<input.length();i++){
            char now = input.charAt(i);
            if(now=='('||now=='[') stack.add(now);
            else if(now==')'||now==']'){

                int num=0;
                while(!stack.isEmpty()&&stack.peek()!='['&&stack.peek()!='('){
                    stack.pop();
                    num+=nums.pop();
                }
                if(stack.isEmpty()){
                    System.out.println(0);
                    return;
                }
                if(now==')'&&stack.peek()=='['){
                    System.out.println(0);
                    return;
                }
                else if(now==']'&&stack.peek()=='('){
                    System.out.println(0);
                    return;
                }
                else if(now==')'){
                    stack.pop();
                    stack.add('-');
                    nums.add(num==0 ? 2:num*2);
                }
                else if(now==']'){
                    stack.pop();
                    stack.add('-');
                    nums.add(num==0 ? 3:num*3);
                }
            }
        }
        int ans = 0;
        while(!stack.isEmpty()){
            if(stack.peek()!='-'){
                System.out.println(0);
                return;
            }
            ans+=nums.pop();
            stack.pop();
        }
        System.out.println(ans);
    }
} 