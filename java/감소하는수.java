import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        if(n<10){
            System.out.println(n);
            return;
        }
        Queue<Long> queue = new ArrayDeque<>();
        for(int i=0;i<10;i++) queue.add((long)i);
        int count=9;
        while(!queue.isEmpty()){
            long now = queue.poll();
            for(int i=0;i<now%10;i++){
                count+=1;
                long value = now*10+i;
                if(count==n){
                    System.out.println(value);
                    return;
                }
                queue.add(value);
            }
        }
        System.out.println(-1);
    }
}