import java.io.*;
import java.util.*;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()),m=Integer.parseInt(st.nextToken());

        List<List<Integer>> tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());

        int[] ar = new int[n+1];
        
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
    		int bi = Integer.parseInt(st.nextToken()),sm=Integer.parseInt(st.nextToken());
            tree.get(bi).add(sm);
            ar[sm]+=1;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(ar[i]==0) queue.add(i);
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int now = queue.poll();
            sb.append(now).append(" ");

            for(int next:tree.get(now)){
                ar[next]--;
                if(ar[next]==0){
                    queue.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}