import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=t;tc++){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent[]= new int[n+1];
            int level[] = new int[n+1];
            List<List<Integer>> tree = new ArrayList<>();
            for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
            for(int i=2;i<=n;i++){
                parent[i]= Integer.parseInt(st.nextToken());
                tree.get(parent[i]).add(i);
                level[i] = level[parent[i]]+1;
            }
            int answer = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(1);
            int curNode=1;
            while(!queue.isEmpty()){
                int now =queue.poll();
                for(int next: tree.get(now)){
                    queue.add(next);
                    int temp=next;
                    while(level[next]!=level[curNode]){
                        answer++;
                        next = parent[next];
                    }
                    while(next!=curNode){
                        answer+=2;
                        next = parent[next];
                        curNode = parent[curNode];
                    }
                    curNode=temp;
                }
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
            
        }
        System.out.println(sb);
    }
}
