import java.util.*;
import java.io.*;

public class Main {

	static class node{
		int a;int b;
		public node(int q,int w) {
			a=q;b=w;
		}
	}
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
    	int test_case = Integer.parseInt(br.readLine());
    	for(int tc=0;tc<test_case;tc++){
    		int n = Integer.parseInt(br.readLine());
    		node[] arr = new node[n];
    		for(int i=0;i<n;i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			arr[i]= new node(a,b);
    		}
    		Arrays.sort(arr,(o1,o2)->{ return o1.a-o2.a; });
    		Stack<Integer> stack = new Stack<>(); 
        	for(int i=0;i<n;i++) {
        		int now = arr[i].b;
        		if(stack.isEmpty()||stack.peek()>now) {
        			stack.add(now);
        		} 
        	}
        	System.out.println(stack.size());
    	}
    	
    	
    }
}

