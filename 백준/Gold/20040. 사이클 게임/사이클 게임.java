
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
		int m = Integer.parseInt(st.nextToken());
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			int now2 = Integer.parseInt(st.nextToken());
			queue.add(new int[] {now,now2});
		}
		parents = new int[n+1];
		for(int i=0;i<=n;i++) parents[i]=i;
		
		long answer = 0L;
		boolean check = false;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int lp = find_parents(now[0]);
			int rp = find_parents(now[1]);
			if(lp!=rp) {
				union(lp,rp);
				answer++;
			}
			else {
				answer++;
				check = true;
				break;
			}
		}
		answer = check ? answer : 0L;
		System.out.println(answer);
	}

}
