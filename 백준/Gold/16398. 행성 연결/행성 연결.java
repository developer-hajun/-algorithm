
import java.util.*;
import java.io.*;
public class Main {
	static int[] parents;
	
	public static int find_parents(int x) {
		if(parents[x]!=x) parents[x]=find_parents(parents[x]);
		return parents[x];
	}
	public static void union(int left,int right) {
		if(left<right) {
			parents[right]=left;
		}
		else {
			parents[left]=right;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)-> o1[2]-o2[2]);
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int now = Integer.parseInt(st.nextToken());
				if(i<j) {
					queue.add(new int[] {i,j,now});
				}
			}
		}
		parents = new int[n+1];
		for(int i=0;i<=n;i++) parents[i]=i;
		
		long answer = 0L;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int lp = find_parents(now[0]);
			int rp = find_parents(now[1]);
			if(lp!=rp) {
				union(lp,rp);
				answer+=now[2];
			}
		}
		System.out.println(answer);
	}

}
