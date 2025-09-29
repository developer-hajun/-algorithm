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

class Main {
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Node head = new Node(-1, null); 
            Node tail = head;
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int value = sc.nextInt();
                Node next_tail = new Node(value, null);
                tail.next = next_tail;
                tail = next_tail;
            }
    
            int t = sc.nextInt();
            for (int j = 0; j < t; j++) {
                char command = sc.next().charAt(0);
                switch (command) {
                    case 'A': {
                        int y = sc.nextInt();
                        for (int e = 0; e < y; e++) {
                            int value = sc.nextInt();
                            Node next_tail = new Node(value, null);
                            tail.next = next_tail;
                            tail = next_tail;
                        }
                        break;
                    }
                    case 'D': {
                        int x = sc.nextInt();
                        int y = sc.nextInt();
    
                        Node startNode = head;
                        for (int e = 0; e < x; e++) {
                            if (startNode.next == null) break;
                            startNode = startNode.next;
                        }
    
                        Node endNode = startNode;
                        for (int e = 0; e < y; e++) {
                             if (endNode.next == null) break;
                            endNode = endNode.next;
                        }
    
                        startNode.next = endNode.next;
                        
                        if (startNode.next == null) {
                            tail = startNode;
                        }
                        break;
                    }
                    case 'I': { 
                        int x = sc.nextInt();
                        int y = sc.nextInt();
    
                        Node insertionPoint = head;
                        for (int e = 0; e < x; e++) {
                            if (insertionPoint.next == null) break;
                            insertionPoint = insertionPoint.next;
                        }
    
                        Node restOfList = insertionPoint.next;
    
                        Node currentTail = insertionPoint;
                        for (int i = 0; i < y; i++) {
                            int value = sc.nextInt();
                            Node newNode = new Node(value, null);
                            currentTail.next = newNode;
                            currentTail = newNode;
                        }
    
                        currentTail.next = restOfList;
                        
                        if (restOfList == null) {
                            tail = currentTail;
                        }
                        break;
                    }
                }
            }
    
            Node curr = head.next;
            System.out.print("# ");
            for(int i = 0; i < 10 && curr != null; i++) {
                System.out.print(curr.value + " ");
                curr = curr.next;
            }
            System.out.println();
        }
    }
}
