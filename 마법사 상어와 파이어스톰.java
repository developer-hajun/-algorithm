import java.util.*;



class Main
{
	static int[][] arr;

	public static void rotate(int y, int x, int ly, int lx) {
	    int size = ly - y + 1;
	    int[][] temp = new int[size][size];

	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            temp[j][size - 1 - i] = arr[y + i][x + j];
	        }
	    }

	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            arr[y + i][x + j] = temp[i][j];
	        }
	    }
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt();
		int k = (int) Math.pow(2,n);
		arr = new int[k][k];

		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		//입력
		for(int i=0;i<m;i++) {
			int L = sc.nextInt();
			int size = (int) Math.pow(2,L);
			for(int y=0;y<k;y+=size) {
				for(int x=0;x<k;x+=size) {
					rotate(y,x,y+size-1,x+size-1);
				}
			}
			//분할 정복 or 그냥 구현으로 크기 나누기 2차원 for문으로
			List<int[]> next = new ArrayList<>();
			for(int y=0;y<k;y++) {

				for(int x=0;x<k;x++) {
					if(arr[y][x]<=0) continue;
					int count=0;
					for(int[] move : new int[][]{{0,1},{0,-1},{1,0},{-1,0}}) {
						int ny = y+move[0],nx=x+move[1];
						if(0<=ny && ny<k && 0<=nx && nx<k && arr[ny][nx]>0) count++;
					}
					if(count<3) {
						next.add(new int[] {y,x});
					}
				}

			}
			for(int[] a : next) {
				arr[a[0]][a[1]]--;
			}
		}
		int answer =0;
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
				if(arr[i][j]<=0) continue;
				answer+=arr[i][j];
			}
		}
		System.out.println(answer);
		int answer2=0;
		boolean[][] visit = new boolean[k][k];
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
				if( arr[i][j]>0 && !visit[i][j]) {
					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] {i,j});
					visit[i][j]=true;
					int now=1;
					while(!queue.isEmpty()) {
						int[] coor = queue.poll();
						int y=coor[0],x=coor[1];
						for(int[] move : new int[][] {{1,0},{-1,0},{0,-1},{0,1}}) {
							int ny = y+move[0],nx=x+move[1];
							if(0<=ny && ny<k && 0<=nx && nx<k && arr[ny][nx]>0 &&!visit[ny][nx]) {
								now+=1;
								visit[ny][nx]=true;
								queue.add(new int[] {ny,nx});
							}

						}
					}
					answer2=Math.max(answer2, now);
				}
			}
		}
		System.out.println(answer2);
	}
}