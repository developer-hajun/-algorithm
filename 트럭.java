import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int he;
    int t;
    public Pair(int h,int t){
        he =h;
        this.t=t;
    }
}
// The main method must be in a class named "Main".
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),w=sc.nextInt(),l=sc.nextInt();
        Queue<Integer> truck = new ArrayDeque<>();
        Queue<Pair> move = new ArrayDeque<>();
        for(int i=0;i<n;i++) truck.add(sc.nextInt());
        int now_l=0;
        int time=0;
        while(!truck.isEmpty() || !move.isEmpty()){
            time++;
            if(!move.isEmpty() && time - move.peek().t>=w){
                Pair now = move.poll();
                now_l-=now.he;
            }
            if(!truck.isEmpty() && truck.peek()+now_l<=l){
                int height = truck.poll();
                now_l+=height;
                move.add(new Pair(height,time));
            }
            if(!move.isEmpty() && !truck.isEmpty() && truck.peek()+now_l>l){
                //다음 건널 수 있는 시간 -1;
                time = move.peek().t+w-1; 
            }
        }
        System.out.println(time);
    }
}

