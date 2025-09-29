import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int go;
    int dist;
    public Pair(int g,int d){
        this.go = g;
        this.dist = d;
    }
}
class Main {
    static List<List<Pair>> tree = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),m = sc.nextInt(); 
        sc.nextLine();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());
        for (int i = 1; i <= n-1; i++) {
            String[] now = sc.nextLine().split(" "); 
            int start = Integer.parseInt(now[0]);
            int end = Integer.parseInt(now[1]);
            int value = Integer.parseInt(now[2]);
            tree.get(start).add(new Pair(end,value));
            tree.get(end).add(new Pair(start,value));
        }
        for(int i=1;i<=m;i++){
            String[] now = sc.nextLine().split(" "); 
            int start = Integer.parseInt(now[0]);
            int end = Integer.parseInt(now[1]);
            int[] visit= new int[n+1];
            Arrays.fill(visit,Integer.MAX_VALUE);
            visit[start]=0;
            Queue<Pair> queue = new ArrayDeque<>();
            queue.add(new Pair(start,0));
            while(!queue.isEmpty()){
                Pair v = queue.poll();
                int nows = v.go;
                int count = v.dist;
                for(Pair t : tree.get(nows)){
                    int now_count=count+t.dist;
                    if(visit[t.go]>now_count){
                        visit[t.go]=now_count;
                        queue.add(new Pair(t.go,now_count));
                    }
                }
            }
            System.out.println(visit[end]);
        }
    }
}
