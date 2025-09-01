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
	static int[] parents;

	public static int find_parents(int x){
		if(parents[x]!=x) parents[x] = find_parents(parents[x]);
		return parents[x];
	}

	public static void union(int left_parent,int right_parent){
		if(left_parent<right_parent){
			parents[right_parent] = left_parent;
		}
		else{
			parents[left_parent] = right_parent;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int start=Integer.parseInt(st.nextToken());
		int end=Integer.parseInt(st.nextToken());

		parents = new int[n+1];
		for(int i=0;i<=n;i++) parents[i]=i;

		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o2[2]-o1[2]);
		for(int i=0;i<m;i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			queue.add(new int[]{u,v,c});
		}




		ArrayList<int[]>[] move = new ArrayList[n+1];
		for(int i=0;i<=n;i++) move[i] = new ArrayList<>();

		while(!queue.isEmpty()){
			int[] now = queue.poll();
			int lp = find_parents(now[0]);
			int rp = find_parents(now[1]);
			if(lp!=rp){
				move[now[0]].add(new int[] {now[1],now[2]});
				move[now[1]].add(new int[] {now[0],now[2]});
				union(lp,rp);
			}
			if(find_parents(start)==find_parents(end)) {
				System.out.println(now[2]);
				return;
			}
		}

//		int answer = Integer.MAX_VALUE;
//		queue = new PriorityQueue<>((o1,o2)->o2[1]-o1[1]);
//		queue.add(new int[] {start,Integer.MAX_VALUE});
//		boolean[] visit = new boolean[n+1];
//		visit[start]=true;
//
//		while(!queue.isEmpty()) {
//			int[] now = queue.poll();
//			if(now[0]==end) {
//				answer = Math.min(now[1], answer);
//				continue;
//			}
//			for(int[] next:move[now[0]]) {
//				if(visit[next[0]]) continue;
//				visit[next[0]] = true;
//				queue.add(new int[] {
//						next[0],Math.min(now[1], next[1])
//				});
//			}
//		}
//		System.out.print(answer);



	}
}
