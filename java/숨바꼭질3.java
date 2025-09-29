import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int value;
    int count;
    public Pair(int v,int c){
        value=v;
        count=c;
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        int[] answer = new int[100001];
        Arrays.fill(answer, Integer.MAX_VALUE);
        Queue<Pair> queue = new ArrayDeque<>();
        answer[n]=0;
        queue.add(new Pair(n,0));
        while(!queue.isEmpty()){
            Pair now = queue.poll();
            if(now.value==k) continue;
            for(Pair var : new Pair[]{new Pair(now.value+1,now.count+1),new Pair(now.value-1,now.count+1),new Pair(now.value*2,now.count)})
            {
                int index = var.value;
                int count = var.count;
                if(index>100000 || index<0 || answer[index]<=count ) continue;
                answer[index] = count;
                queue.add(new Pair(index,count));
            }
        }
        System.out.println(answer[k]);
    }
}