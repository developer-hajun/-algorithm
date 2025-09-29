import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] h = new int[N];
        int[] see = new int[N];
        int[] an = new int[N];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<N;i++){
            h[i] = sc.nextInt();
            an[i]=-1;
        }
        for(int i=0;i<N;i++){
            while(!stack.isEmpty() && h[stack.peek()]<=h[i]){
                stack.pop();
            }
            see[i] = stack.size();
            if(!stack.isEmpty()) an[i]=stack.peek();
            stack.add(i);
        }
        stack = new Stack<>();
        for(int i=N-1;i>=0;i--){
            while(!stack.isEmpty() && h[stack.peek()]<=h[i]){
                stack.pop();
            }
            see[i] += stack.size();
            if(!stack.isEmpty() && (an[i]==-1 || Math.abs(an[i]-i)>Math.abs(i-stack.peek()))) an[i]=stack.peek();
            stack.add(i);
        }
        for(int i=0;i<N;i++){
            if(see[i]==0){
                System.out.println(0);
            }
            else{
                System.out.println(see[i]+" "+(an[i]+1));
            }
        }
    }
}