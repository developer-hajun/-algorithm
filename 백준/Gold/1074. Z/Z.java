import java.util.*;
import java.io.*;

public class Main {

	static int r,c;
	public static void pick(int start_y,int start_x,int size,int pick) {
		if(size==1) {
			System.out.print(pick);
			return;
		}
		int rsize = size/2;
		
		int num=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				int ry = start_y+rsize*i;
				int rx = start_x+rsize*j;
				if(ry<=r&&r<ry+rsize&&rx<=c&&c<rx+rsize) {
					pick(ry,rx,rsize,pick+rsize*rsize*num);
				}
				num++;
			}
		}
	}
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        
        int size = (int)Math.pow(2,n);
        
        pick(0,0,size,0);
        
    }
}
