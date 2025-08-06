import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            deque.add(i);
        }

        int move = 1;
        int out_count = 0;

        while(!deque.isEmpty()){
            for(int i=0;i<k-1;i++){
                if(move==-1) deque.addFirst(deque.pollLast());
                else deque.addLast(deque.pollFirst());
            }
            if(move==-1) sb.append(deque.pollLast()).append("\n");
            else sb.append(deque.pollFirst()).append("\n");

            out_count++;
            if(out_count==m){
                out_count=0;
                move *= -1;
            }
        }
        System.out.print(sb.toString());


    }
}