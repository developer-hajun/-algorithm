import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt(),r =sc.nextInt();
		int[][] arr = new int[n][m];
		for(int y=0;y<n;y++) {
			for(int x=0;x<m;x++) {
				arr[y][x] = sc.nextInt();
			}
		}
		int round = (int) Math.ceil(Math.min(m,n)/2);
		int[] move_count = {n-1,m-1,n-1,m-1};
		int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
		for(int i=0;i<round;i++) {
			int y=i,x = i;
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			for(int e=0;e<4;e++) {
				for(int go=0;go<move_count[e];go++) {
					y+=move[e][0];
					x+=move[e][1];
					queue.add(arr[y][x]);
				}
			}
			for(int e=0;e<r%queue.size();e++) {
				queue.addFirst(queue.pollLast());
			}
			y=i;
			x = i;
			for(int e=0;e<4;e++) {
				for(int go=0;go<move_count[e];go++) {
					y+=move[e][0];
					x+=move[e][1];
					arr[y][x] =queue.poll();
				}
			}
			for(int e=0;e<4;e++) {
				move_count[e]-=2;
			}
		}
		for(int y=0;y<n;y++) {
			for(int x=0;x<m;x++) {
				System.out.print(arr[y][x]+" ");
			}
			System.out.println();
		}
	}

}
