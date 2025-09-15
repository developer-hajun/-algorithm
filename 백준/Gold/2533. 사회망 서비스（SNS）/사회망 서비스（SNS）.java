import java.io.*;
import java.util.*;

public class Main {
	static int answer = 0;
	static List<Integer>[] dist;
	static boolean[] visit;
	
	public static boolean dfs(int now) {
		visit[now]=true;
		boolean isLeaf=true;
		
		for(int next: dist[now]) {
			if(!visit[next]) {
				isLeaf=false;
				break;
			}
		}
		if(isLeaf) return false; //리프노드라면 안켜기
		
		boolean check=true;
		for(int next: dist[now]) {
			if(!visit[next]) {
				check = dfs(next)==false ? false : check; 
			}
		}
		if(!check) {answer++; return true;}
		//안킨게있다면 
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		dist = new ArrayList[n+1];
		visit = new boolean[n+1];
		for(int i=0;i<=n;i++) dist[i]=new ArrayList<>();
		for(int i=0;i<n-1;i++) {
			st = new StringTokenizer(br.readLine());
			int l =  Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			dist[l].add(r);
			dist[r].add(l);
		}
		dfs(1);
		System.out.print(answer);
	}

}