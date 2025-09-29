import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

class Pair{
	int y,x;
	public Pair(int a,int b) {
		y=a;
		x=b;
	}
}
class Main {
    static char[][] arr = new char[12][6];
    static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int boom(){
    	int ans=0;
    	boolean[][] visit = new boolean[12][6];
    	for(int y=0;y<12;y++) {
    		for(int x=0;x<6;x++) {
    			if(arr[y][x]=='.'||visit[y][x]) continue;
    			
    			List<Pair> find = new ArrayList<>();
    			Queue<Pair> queue = new ArrayDeque<>();
    			find.add(new Pair(y,x));
    			queue.add(new Pair(y,x));
    			visit[y][x]=true;
    			while(!queue.isEmpty()) {
    				Pair now = queue.poll();
    				for(int[] m : move) {
    					int ny = now.y+m[0];
    					int nx = now.x+m[1];
    					if(ny<0 || ny>=12||nx<0||nx>=6) continue;
    					if(visit[ny][nx] || arr[y][x]!=arr[ny][nx]) continue;
    					find.add(new Pair(ny,nx));
    					queue.add(new Pair(ny,nx));
    					visit[ny][nx]=true; 
    				}
    			}
    			if(find.size()>=4) {
    				for(Pair now : find) {
    					arr[now.y][now.x]='.';
    				}
    				ans++;
    			}
    			
    		}
    		
    	}
    	return ans;
    }
    public static void down() {
    	for(int x=0;x<6;x++) {
    		for(int y=11;y>=0;y--) {
    			if(arr[y][x]=='.') continue;
    			int ny = y;
    			while(ny+1<12 && arr[ny+1][x]=='.') {
    				arr[ny + 1][x] = arr[ny][x];
                    arr[ny][x] = '.';
                    ny++;
    			}
    		}
    	}
    }
    public static void pr() {
    	for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println(); // 줄 바꿈
        }
    	System.out.println("-----------------------------------------");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<12;i++){
            String value = sc.nextLine();
            for(int j=0;j<6;j++) {
            	arr[i][j]=value.charAt(j);
            }
        }
        int answer=0;
        while(true) {
        	int value = boom();
        	if(value==0) {
        		break;
        	}
        	answer+=value;
        	down();
        }
        System.out.println(answer);
    }
}