import java.util.*;
import java.io.*;

public class Main {
	
	public static int pick(int start_y,int start_x,int size) {
		if(size==1) return map[start_y][start_x];
		int s = size/2;
		
		int[] next = new int[4];
		int num=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				next[num]= pick(start_y+s*i,start_x+s*j,s);
				num++;
			}
		}
		Arrays.sort(next);
		
		return next[2];
	}
	static int[][] map;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        int size = (int)Math.pow(n, 2);
        System.out.print(pick(0,0,n));
        
    }
}
