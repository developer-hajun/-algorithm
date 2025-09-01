import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n= Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		int[][] visit = new int[n][m];
		for(int i=0;i<n;i++) {
			String now= br.readLine();
			Arrays.fill(visit[i],Integer.MAX_VALUE);
			for(int j=0;j<m;j++) {
				map[i][j]=now.charAt(j);
			}
		}
		st = new StringTokenizer(br.readLine());
		int sy=Integer.parseInt(st.nextToken())-1;
		int sx=Integer.parseInt(st.nextToken())-1;
		int ey=Integer.parseInt(st.nextToken())-1;
		int ex=Integer.parseInt(st.nextToken())-1;
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		visit[sy][sx]=0;
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
		queue.add(new int[] {sy,sx,0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int y = now[0],x=now[1],count=now[2];
			if(visit[y][x]<count) continue;
			if(y==ey&&x==ex) {
				System.out.println(count);
				return;
			}
			for(int j=0;j<4;j++){
				for(int i=1;i<=k;i++) {
					int ny = y+dy[j]*i;
					int nx = x+dx[j]*i;
					if(ny<0||ny>=n||nx<0||nx>=m||visit[ny][nx]==count+1) continue;
					if(map[ny][nx]=='#'||visit[ny][nx]<count+1) break;
					visit[ny][nx]=count+1;
					queue.add(new int[] {ny,nx,count+1});
				}
			}
			
			
		}
		System.out.println(-1);
		
	}

}
