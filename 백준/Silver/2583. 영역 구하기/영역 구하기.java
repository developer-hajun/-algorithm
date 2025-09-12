

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(),m = sc.nextInt(),k = sc.nextInt();
		int[][] board = new int[n+1][m+1];
		boolean[][] visit = new boolean[n][m];

		for (int i = 0; i < k; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			for(int e=y;e<ey;e++){
				board[e][x]++;
				if(ex>m) continue;
				
				board[e][ex]--;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < m; j++){
				board[i][j]+=board[i][j-1];
			}
		}

		int count=0;
		List<Integer> s = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && board[i][j] == 0) {
					int size=1;
					Queue<int[]> queue = new ArrayDeque<>();
					queue.add(new int[]{i,j});
					visit[i][j]=true;
					while(!queue.isEmpty()){
						int[] now = queue.poll();
						int y=now[0],x=now[1];
						for(int[] next : new int[][] { {y+1,x},{y-1,x},{y,x+1} ,{y,x-1}}){
							int ny = next[0],nx=next[1];
							if(ny<0||ny>=n||nx<0||nx>=m||visit[ny][nx]||board[ny][nx]!=0) continue;
							visit[ny][nx]=true;
							size++;
							queue.add(new int[]{ny,nx});
						}
					}
					s.add(size);
					count++;
				}
			}
		}
		Collections.sort(s);
		
		System.out.println(count);
		for(int i : s) {
			System.out.print(i+" ");
		}
		
	}
}
