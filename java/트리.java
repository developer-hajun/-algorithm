import java.util.*;
import java.io.*;

class Main {
    static int[] parent;
    static List<List<Integer>> tree = new ArrayList<>();

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        parent[start] = -1;
        int count=0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if(tree.get(current).size()==0){
                count++;
                continue;
            }
            for (int neighbor : tree.get(current)) {
                queue.add(neighbor);
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
             parent[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        parent[Integer.parseInt(st.nextToken())]=-2;
        for(int i=0;i<n;i++){
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if(parent[i]<0) continue;
            tree.get(parent[i]).add(i);
        }
        for(int i=0;i<n;i++){
            if(parent[i]==-1){
                System.out.println(bfs(i));
                return;
            }
        }
        System.out.println(0);
    }
}
