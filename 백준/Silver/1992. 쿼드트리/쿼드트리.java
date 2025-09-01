import java.util.*;
import java.io.*;

public class Main {

	
	public static String pick(int start_y,int start_x,int size) {
		if(size==1) return String.valueOf(map[start_y][start_x]);
		int s = size/2;
		
		String[] next = new String[4];
		int num=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				next[num]= pick(start_y+s*i,start_x+s*j,s);
				num++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<4;i++) {
			if(!next[i].equals(next[i-1])||next[i].length()!=1) {
				sb.append('(');
				for(int e=0;e<4;e++) {
					sb.append(next[e]);
				}
				sb.append(')');
				return sb.toString();
			}
		}
		
		return next[0];
	}
	static char[][] map;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        for(int i=0;i<n;i++) {
        	String e =  br.readLine();
        	for(int j=0;j<n;j++) {
        		map[i][j] = e.charAt(j);
        	}
        }
        String a = pick(0,0,n);
        System.out.print(a);
        
    }
}
