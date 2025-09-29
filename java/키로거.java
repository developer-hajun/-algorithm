import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int test_case = 0;test_case<t;test_case++){
            Deque<Character> left = new ArrayDeque<>();
            Deque<Character> right = new ArrayDeque<>();
            String now = sc.next();
            for(int i=0;i<now.length();i++){
                char value =now.charAt(i);
                if(value=='<'){
                    if(left.isEmpty()) continue;
                    right.addFirst(left.pollLast());
                }
                else if(value=='>')
                {
                    if(right.isEmpty()) continue;
                    left.addLast(right.poll());
                }
                else if(value=='-'){
                    if(left.isEmpty()) continue;
                    left.pollLast();
                }
                else{
                    left.add(value);
                }
                //System.out.println(left.toString());
                //System.out.println(right.toString());
            }
            StringBuilder sb = new StringBuilder();
            for (char ch : left) {
                sb.append(ch);
            }
            for (char ch : right) {
                sb.append(ch);
            }
            String result = sb.toString();
            System.out.println(result);
            //System.out.println("---------------------------------------");
        }
    }
}