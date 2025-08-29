
import java.util.*;
import java.io.*;

class edge{
	int next;
	int value;
	public edge(int next,int value) {
		this.next = next;
		this.value = value;
	}
}
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		boolean[] visit = new boolean[n+1];
		visit[1]=true;
		
		List<List<int[]>> move = new ArrayList<>();
		for(int i=0;i<=n;i++) move.add(new ArrayList<>());
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			move.get(a).add(new int[] {b,c});
			move.get(b).add(new int[] {a,c});
		}
		PriorityQueue<edge> edges = new PriorityQueue<>((o1,o2)-> o1.value-o2.value );
		for(int[] next : move.get(1)) edges.add(new edge(next[0],next[1]));
		int plus=0;
		int answer=0;
		while(!edges.isEmpty()) {
			edge next = edges.poll();
			if(visit[next.next]) continue;
			answer+=next.value+plus;
			plus+=t;
			visit[next.next] = true;
			for(int[] nexts : move.get(next.next)) {
				if(visit[nexts[0]]) continue;
				edges.add(new edge(nexts[0],nexts[1]));
			}
		}
		System.out.println(answer);
		
	}

}
