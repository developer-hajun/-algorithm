import java.util.*;
import java.lang.*;
import java.io.*;

class Node {
    int value;
    Node next;
    public Node(int a, Node b) {
        value = a;
        next = b;
    }
}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int test=1; test <= tc; test++) {
            Node head = new Node(-1, null);
            Node tail = head;

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(st.nextToken());
                Node next_tail = new Node(value, null);
                tail.next = next_tail;
                tail = next_tail;
            }
            
            
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                
                switch (command) {

                    case 'I': {
                        int a = Integer.parseInt(st.nextToken());
                        int b = Integer.parseInt(st.nextToken());
                        Node newNode = new Node(b,null);
                        Node start = head;
                        for (int e = 0; e < a; e++) {
                            start = start.next;
                        }
                        newNode.next = start.next;
                        start.next = newNode;
                        break;
                    }
                    case 'D': {
                        int x = Integer.parseInt(st.nextToken());
                        Node start = head;
                        for(int i=0;i<x;i++){
                            start = start.next;
                        }
                        start.next = start.next.next;
                        break;
                    }
                    case 'C': {
                        int x = Integer.parseInt(st.nextToken());
                        int y = Integer.parseInt(st.nextToken());
                        Node start = head;
                        for(int i=0;i<=x;i++){
                            start = start.next;
                        }
                        start.value=y;
                        break;
                    }
                }
            }

            sb.append("#").append(test).append(" ");
            Node curr = head;
            int value = -1;
            for (int i =0;i<=l;i++) {
                if(curr.next==null){
                    value=-1;
                    break;
                }
                curr=curr.next;
                value = curr.value;
            }
            sb.append(value).append("\n");
        }
        

        System.out.print(sb);
        br.close();
    }
}
