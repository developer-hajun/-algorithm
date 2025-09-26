import java.util.*;
import java.io.*;


public class Main {
	static class node{
		int start;
		int end;
		int height;
		public node(int a,int b,int c) {
			start=a;end=b;height=c;
		}
	}
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n=Integer.parseInt(st.nextToken());
    	int c=Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(br.readLine());
    	
    	node[] arr = new node[m];
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
        	int start=Integer.parseInt(st.nextToken());
        	int end=Integer.parseInt(st.nextToken());
        	int visit=Integer.parseInt(st.nextToken());
        	arr[i]=new node(start,end,visit); 
    	}
    	
    	Arrays.sort(arr,(o1,o2)->o1.end-o2.end);
    	
    	int[] sum = new int[n + 1]; // 각 마을 사이 구간에 현재 실려 있는 박스 수
    	
    	int total=0;
    	for (node now : arr) {
    	    int start = now.start;
    	    int end = now.end;
    	    int boxes = now.height;
    	    
    	    
    	    int minCapacity = c;
    	    for (int i = start; i < end; i++) {
    	        minCapacity = Math.min(minCapacity, c - sum[i]);
    	    }

    	    int canLoad = Math.min(minCapacity, boxes);

    	    for (int i = start; i < end; i++) {
    	        sum[i] += canLoad;
    	    }

    	    total += canLoad;
    	}
    	System.out.println(total);

    	
    }
}

