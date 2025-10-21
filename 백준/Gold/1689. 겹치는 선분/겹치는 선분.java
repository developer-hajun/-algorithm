import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] lines = new int[n][2];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new int[]{s, e};
        }

        Arrays.sort(lines, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        
        int answer = 0;

        for(int[] line : lines){
            int sta = line[0], en = line[1];

            while(!queue.isEmpty() && queue.peek()[1] <= sta){
                queue.poll();
            }

            queue.add(new int[]{sta, en});

            answer = Math.max(answer, queue.size());
        }

        System.out.println(answer);
    }
}
