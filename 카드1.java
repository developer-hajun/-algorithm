import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        Scanner sc = new Scanner(System.in);
        StringBuilder st = new StringBuilder();
        int n = sc.nextInt();
        for(int i=1;i<=n;i++) queue.add(i);
        while(!queue.isEmpty()){
            st.append(queue.poll()).append(" ");
            if(!queue.isEmpty()){
                queue.add(queue.poll());
            }
        }
        System.out.println(st.toString());
    }
}