import java.util.*;
import java.io.*;


public class Main {
	static class fish{
		int id,y, x,dir;
		public fish(int a,int b,int i,int j) {
			id = a-1;dir = b-1;y=i;x=j;
			fishMap[i][j]=this;
			fishById[id]=this;
		}
	}
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static fish[][] fishMap = new fish[4][4];
	static fish[] fishById = new fish[16];
	static int[][] move= { {-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static int answer = -Integer.MAX_VALUE;
	static fish[][] clone(fish[][] original) {
	    int n = original.length;
	    fish[][] newMap = new fish[n][];
	    for (int i = 0; i < n; i++) {
	        newMap[i] = Arrays.copyOf(original[i], original[i].length);
	    }
	    return newMap;
	}
	
	
	public static void dfs(int y,int x,int dir,int count){
		fish[][] clone = clone(fishMap);
		//다시 돌릴 배열
		int[][] backupPos = new int[16][3]; 
		for(int i=0;i<16;i++) {
		    if(fishById[i]==null) {
		        backupPos[i][0] = -1; 
		        continue;
		    }
		    fish f = fishById[i];
		    backupPos[i][0] = f.y;
		    backupPos[i][1] = f.x;
		    backupPos[i][2] = f.dir;
		}

		//이동하기
		
		for(int i=0;i<16;i++) {
			if(fishById[i]==null) continue;
			fish f = fishById[i];
			for(int j=0;j<8;j++) {
				int dirs = (f.dir+j)%8;
				int ny = f.y+move[dirs][0];
				int nx = f.x+move[dirs][1];
				
				if(ny<0||nx<0||ny>=4||nx>=4) continue;
				if(y==ny&&x==nx) continue;
				
				fish change = fishMap[ny][nx];
				if(change!=null) {
					change.y=f.y; change.x=f.x;
				}
				fishMap[f.y][f.x]=change;
				f.y=ny; f.x=nx; f.dir=dirs;
				fishMap[ny][nx]=f;
				break;
			}
		}
		
		
		//상어먹기
		
		boolean eat_check=true;
		int c=1;
		while(true) {
			int ny = y+move[dir][0]*c;
			int nx = x+move[dir][1]*c;
			c++;
			if(ny<0||nx<0||ny>=4||nx>=4) break;
			if(fishMap[ny][nx]==null) continue;
			fish now = fishMap[ny][nx];
			fishMap[ny][nx] = null;
			fishById[now.id]=null;
			dfs(ny,nx,now.dir,count+now.id+1);
			fishMap[ny][nx] = now;
			fishById[now.id]=now;
			eat_check=false;
		}
		fishMap=clone;
		for(int i=0;i<16;i++) {
		    if(fishById[i]==null) continue;
		    fish f = fishById[i];
		    f.y = backupPos[i][0];
		    f.x = backupPos[i][1];
		    f.dir = backupPos[i][2];
		    fishMap[f.y][f.x] = f;
		}
		if(eat_check) answer= Math.max(count, answer);
		
	}
	
    public static void main(String[] args) throws Exception {
    	
    	for(int i=0;i<4;i++) {
    		st=new StringTokenizer(br.readLine());
    		for(int j=0;j<4;j++) {
    			int id = Integer.parseInt(st.nextToken());
    			int dir = Integer.parseInt(st.nextToken());
    			fish f = new fish(id,dir,i,j);
    		}
    	}
    	fish eat = fishMap[0][0];
    	fishMap[0][0]=null;
    	fishById[eat.id]=null;
    	int shark_dir=eat.dir;
    	dfs(0,0,shark_dir,eat.id+1);
    	System.out.print(answer);
    	
    	
    }
}