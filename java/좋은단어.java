import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int count=0;
        for(int t=0;t<n;t++){
            String now = new StringTokenizer(br.readLine()).nextToken();
            Stack<Character> stack = new Stack<>();
            boolean check = true;
            for(int i=0;i<now.length();i++){
                char ch = now.charAt(i);
                stack.add(ch);
                while(stack.size()>=2){
                    char ch1=stack.pop(),ch2=stack.pop();
                    if(ch1==ch2) continue;
                    else{
                        stack.add(ch2);
                        stack.add(ch1);
                        break;
                    }
                }
            }
            if(stack.isEmpty()) count++;
        }
        System.out.println(count);
    }
}