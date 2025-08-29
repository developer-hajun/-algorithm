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

        int t=Integer.parseInt(st.nextToken());
        for(int tc=1;tc<=t;tc++){
            st = new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            Queue<int[]> queue = new ArrayDeque<>();

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                queue.add(new int[]{u,v});
            }
    
            parents = new int[n+1];
            for(int i=0;i<n+1;i++) parents[i]=i;
            int answer = 0;
            while(!queue.isEmpty()){
                int[] now = queue.poll();
                int lp = find_parents(now[0]);
                int rp = find_parents(now[1]);
                if(lp!=rp){
                    answer++;
                    union(lp,rp);
                }
            }
            System.out.println(answer);
        }
	}
}
