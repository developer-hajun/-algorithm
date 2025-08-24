import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		int[][] dist = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<n;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[0][0]=0;
		
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)-> o1[2]-o2[2]);
		queue.add(new int[] {0,0,0});
		A:while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int y = now[0];
			int x = now[1];
			int value = now[2];
			if (y == n - 1 && x == n - 1) {
                System.out.println(value);
                return; // 프로그램 종료
            }

			for(int[] next : new int[][] {{y+1,x},{y,x+1}}) {
				int ny = next[0];
				int nx = next[1];
				if(ny>=n ||nx>=n) continue; 
				
				int val = map[ny][nx]-map[y][x]+1;
				val = val<0 ? 0 : val;


				if(dist[ny][nx]>dist[y][x]+val) {
					dist[ny][nx]=dist[y][x]+val;
					queue.add(new int[] {ny,nx,dist[y][x]+val});
				}
				
			}
		}
		
	}

}
