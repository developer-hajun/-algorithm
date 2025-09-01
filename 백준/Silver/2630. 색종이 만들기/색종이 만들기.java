import java.util.*;
import java.io.*;

public class Main {
	static int[] answer= new int[2];
	
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
		for(int i=1;i<4;i++) {
			if(next[i]!=next[i-1]) {
				for(int e=0;e<4;e++) {
					if(next[e]==-2) continue;
					answer[next[e]]++;
				}
				return -2;
			}
		}
		
		return next[0];
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
        int a = pick(0,0,n);
        if(a!=-2) {
        	answer[a]++;
        }
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        
    }
}
