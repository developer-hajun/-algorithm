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
        parents = new int[n+1];
		for(int i=0;i<=n;i++) parents[i]=i;
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
			int now = Integer.parseInt(st.nextToken());
			int now2 = Integer.parseInt(st.nextToken());
            int lp = find_parents(now);
			int rp = find_parents(now2);
            if(command==0){
                if(lp!=rp) union(lp,rp);
            }
            else{
                if(lp==rp) System.out.println("YES");
                else System.out.println("NO");
            }
		}
	}

}
