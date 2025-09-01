import java.util.*;
import java.io.*;

public class Main {
	static int[] parent;
	public static int find_parent(int x){
		if(parent[x]!=x) parent[x]=find_parent(parent[x]);
		return parent[x];
		
	}
	public static void union_parent(int lp,int rp) {
		if(lp<rp) parent[rp]=lp;
		else parent[lp]=rp;
	}
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        parent = new int[v+1];
        int answer = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
        for(int i=1;i<=v;i++) parent[i]=i;
        for(int i=1;i<=e;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int lp = find_parent(a);
        	int rp = find_parent(b);
        	if(lp!=rp) {
        		union_parent(lp,rp);
        	}
        	else {
        		System.out.println(i);
        		return;
        	}
        }
        System.out.print(0);
        
        

    }
}