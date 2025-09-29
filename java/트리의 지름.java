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
    static int[] answer;
    static boolean[] visit;

    public static int dfs(int start,int move){
        visit[start]=true;
        //방문 체크
        if(tree.get(start).size()==0) return move;
        
        List<Integer> value = new ArrayList<>();
        for(Pair next: tree.get(start)){
            value.add(dfs(next.go,next.dist));
        }
        //방문
        int firstMax = 0;
        int secondMax = 0;
        
        for (int v : value) {
            if (v > firstMax) {
                secondMax = firstMax;
                firstMax = v;
            } else if (v > secondMax) {
                secondMax = v;
            }
        }
        answer[start] = firstMax+secondMax;
        return firstMax+move;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());
        for (int i = 1; i <= n-1; i++) {
            String[] now = sc.nextLine().split(" "); 
            int start = Integer.parseInt(now[0]);
            int end = Integer.parseInt(now[1]);
            int value = Integer.parseInt(now[2]);
            tree.get(start).add(new Pair(end,value));
        }
        visit = new boolean[n+1];
        answer = new int[n+1];
        dfs(1,0);
        int max = Arrays.stream(answer).max().getAsInt();
        System.out.println(max);

    }
}

//양쪽 더한건 자신
//넘기는건 둘중에 더 긴걸로