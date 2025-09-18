import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		List<int[]>[] edges = new ArrayList[n+1];
		for(int i=0;i<=n;i++) edges[i]=new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edges[start].add(new int[] {end,dist});
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
		
		queue.add(new int[] {start,0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int node = now[0];
			int dist = now[1];
			if(distance[node]<dist) continue;
			for(int[] next: edges[node]) {
				int next_node = next[0];
				int next_dist = dist+next[1];
				if(distance[next_node]<=next_dist) continue;
				distance[next_node] = next_dist;
				queue.add(new int[] {next_node,next_dist});
			}
		}
		System.out.println(distance[end]);
		
	}

}