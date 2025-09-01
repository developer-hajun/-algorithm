import java.util.*;
import java.io.*;

public class Main {

	static char[][] map;
	public static void pick(int start_y,int start_x,int size) {
		if(size==1) {
			map[start_y][start_x] = '*';
			return;
		}
		int rsize = size/3;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int ry = start_y+rsize*i;
				int rx = start_x+rsize*j;
				if(i==1&&j==1) continue;
				pick(ry,rx,rsize);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        for(int i=0;i<n;i++) Arrays.fill(map[i],' ');
        
        pick(0,0,n);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
        	for(int j=0;j<n;j++) {
        		sb.append(map[i][j]);
        	}
        	sb.append("\n");
        }
        System.out.print(sb);
    }
}
