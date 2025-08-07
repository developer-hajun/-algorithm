import java.util.*;
import java.lang.*;
import java.io.*;

class Problem{
    int deadline;
    int cup;
    public Problem(int a,int b){
        deadline=a;
        cup = b;
    }
}
class Main {
    static int max_dead_line=0;
    static int[] parent;

    public static int find(int x){
        if(parent[x]!=x){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }
    public static void union(int o1,int o2){
        int a = find(o1);
        int b = find(o2);
        parent[a]=b;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        PriorityQueue<Problem> queue = new PriorityQueue<>(new Comparator<Problem>(){
            @Override
            public int compare(Problem o1,Problem o2){
                return o2.cup-o1.cup;
            }
        });
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            queue.add(new Problem(deadline,cup));
            max_dead_line = Math.max(max_dead_line,deadline);
        }
        parent = new int[max_dead_line + 1];
        for (int i = 0; i <= max_dead_line; i++) {
            parent[i] = i;
        }
        int answer = 0;
        while(!queue.isEmpty()){
            Problem now = queue.poll();
            int start = now.deadline;
            int cup = now.cup;
            int next = find(start);
            if(next>0){
                answer+=cup;
                union(next,next-1);
            }
        }
        System.out.println(answer);
    }
}