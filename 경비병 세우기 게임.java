import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int tc=0;tc<t;tc++) {
        	st = new StringTokenizer(br.readLine());
        	int n=Integer.parseInt(st.nextToken());
        	int m= Integer.parseInt(st.nextToken());
        	int k= Integer.parseInt(st.nextToken());
        	if(m<2*k) {
        		System.out.println("Yuto");
        		continue;
        	}
        	int value = (n*m-(2*k*k))%2;
        	if(value==0) System.out.println("Platina");
        	else System.out.println("Yuto");
        }
    }

}
