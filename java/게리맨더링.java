import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int[] human;
    static List<List<Integer>> graph=new ArrayList<>();
    static Stack<Integer> left = new Stack<>();
    static Stack<Integer> right = new Stack<>();
    static int n;
    static int answer=Integer.MAX_VALUE;

    public static boolean connect(Stack<Integer> now){
        int start = now.peek();
        int count = 1;
        boolean[] visit = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visit[start]=true;
        while(!queue.isEmpty()){
            int e = queue.poll();
            for(int next:graph.get(e)){
                if(now.contains(next)&&!visit[next]){
                    queue.add(next);
                    count++;
                    visit[next]=true;
                }
            }
        }
        return count==now.size();
    }

    public static void combinations(int start){
        if(start==n+1){
            if(left.isEmpty() || right.isEmpty()) return;
            if(connect(left) && connect(right)){
                int left_value = 0;
                for(int e:left) left_value+=human[e];
                int right_value = 0;
                for(int e:right) right_value+=human[e];
                answer=Math.min(answer,Math.abs(left_value-right_value));
            }
            return;
        }
        left.add(start);
        combinations(start+1);
        right.add(left.pop());
        combinations(start+1);
        right.pop();
    }
    
    public static void main(String[] args) throws Exception {
        n=Integer.parseInt(br.readLine());
        human = new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) human[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j=0;j<k;j++){
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        combinations(1);
        answer = answer==Integer.MAX_VALUE ? -1:answer;
        System.out.println(answer);
    }
}