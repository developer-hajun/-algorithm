import java.util.*;
import java.io.*;

class Node {
    int start;
    int end;

    public Node(int s, int e) {
        start = Math.min(s, e); 
        end = Math.max(s, e);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Node> house = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            house.add(new Node(s, e));
        }

        house.sort((a, b) -> a.end - b.end);

        int length = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCount = 0;

        for (Node person : house) {
            int lineStart = person.end - length; 

            pq.offer(person.start);

            while (!pq.isEmpty() && pq.peek() < lineStart) {
                pq.poll();
            }

            maxCount = Math.max(maxCount, pq.size());
        }

        System.out.println(maxCount);
    }
}