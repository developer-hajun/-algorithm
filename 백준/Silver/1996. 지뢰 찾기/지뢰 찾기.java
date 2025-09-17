import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int[][] arr = new int[N][N];
        for(int i=0;i<N;i++) {
        	String now = br.readLine();
        	for(int j=0;j<N;j++) {
        		char value = now.charAt(j);
   
        		if(value=='.') continue;
        		arr[i][j]=-1;
        		int val = value-'0';
        		for(int y=i-1;y<=i+1;y++) {
        			for(int x=j-1;x<=j+1;x++) {
        				if(y<0||x<0||x>=N||y>=N||arr[y][x]==-1) continue;
        				arr[y][x]+=val;
        			}
        		}
        	}
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<N;i++) {
        	for(int j=0;j<N;j++) {
        		if(arr[i][j]==-1) sb.append('*');
        		else sb.append(arr[i][j] < 10 ? arr[i][j]: "M" );
        	}
        	sb.append('\n');
        }
        System.out.println(sb);
        
        
    }
}
