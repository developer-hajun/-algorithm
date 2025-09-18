import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] num = new int[n+2];

		for(int i=1;i<=n;i++) num[i]=i;
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			while(l<r) {
				int temp = num[l];
				num[l]=num[r];
				num[r]=temp;
				l++;
				r--;
			}
		}
		StringBuilder sb= new StringBuilder();
		for(int i=1;i<=n;i++) {
			sb.append(num[i]).append(" ");
		}
		System.out.print(sb);
	}

}
