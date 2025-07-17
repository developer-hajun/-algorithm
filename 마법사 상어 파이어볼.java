package input_train;

import java.util.*;


class FireBall{
	int r,c,m,s,d,which;
	public FireBall(int q,int w, int e,int r,int t) {
		this.r=q;
		this.c=w;
		this.m=e;
		this.s=r;
		this.d=t;
	}
}

class InputTrain1
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt(),k=sc.nextInt();
		Queue<FireBall> ball = new LinkedList<>();
		for(int i=0;i<m;i++) {
			int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt(),d=sc.nextInt(),e=sc.nextInt();
			ball.add(new FireBall(a-1,b-1,c,d,e)); //y,x,질량,속력,방향
		}
		List<FireBall>[][] map = new ArrayList[n][n];
		for(int i = 0; i < n; i++) {
		    for(int j = 0; j < n; j++) {
		        map[i][j] = new ArrayList<>();
		    }
		}
		int[][] move = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
		for(int i=0;i<k;i++) {
			while(!ball.isEmpty()){
				FireBall now = ball.poll();
				now.r = now.r + (now.s * move[now.d][0]);
				now.c = now.c + (now.s * move[now.d][1]);

				now.r = (now.r % n + n) % n;
				now.c = (now.c % n + n) % n;
				
				map[now.r][now.c].add(now);
			}
			
			for(int y = 0; y < n; y++) {
			    for(int x = 0; x < n; x++) { 
			    	if (map[y][x].size()==1){
			        	ball.add(map[y][x].get(0));
			        	map[y][x]=new ArrayList<>();
			        }
			    	else if(!map[y][x].isEmpty()){
			    		int size = 0;
			    		int speed = 0;
			    		boolean l = false, r = false;
			    		for(FireBall b : map[y][x]) {
			    		    size += b.m;
			    		    speed += b.s;
			    		    if(b.d % 2 == 0) l = true;
			    		    else r = true;
			    		}
			    		int small_size = size / 5;
			    		int small_speed = speed / map[y][x].size();
			    		map[y][x] = new ArrayList<>();
			    		if(small_size == 0) continue;
			    		if(l && r) {
			    		    for(int q : new int[] {1,3,5,7}) {
			    		        ball.add(new FireBall(y, x, small_size, small_speed, q));
			    		    }
			    		} else {
			    		    for(int q : new int[] {0,2,4,6}) {
			    		        ball.add(new FireBall(y, x, small_size, small_speed, q));
			    		    }
			    		}
			    	}
			        
			    }
			}
		}
		int answer=0;
		while(!ball.isEmpty()) {
			answer+=ball.poll().m;
		}
		System.out.println(answer);
	}
}