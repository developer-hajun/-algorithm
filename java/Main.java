import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString() {
            return "{x=" + x + ", y=" + y + "}";
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Node(x,y));
        }
        nodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x-o2.x;
            }
        });
        int answer=0;
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(0,0));
        for(Node node : nodes){
            while(node.y<stack.peek().y) {
                stack.pop();
                answer++;
            }

            if(node.y>stack.peek().y) {
                stack.add(node);
            }

        }
        while(stack.peek().y>0){
            stack.pop();
            answer++;
        }
        System.out.println(answer);

    }
}