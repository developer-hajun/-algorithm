import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int a = sc.nextInt(),b=sc.nextInt();
            String now;
            if(  (b & ((1<<a)-1)) == ((1<<a)-1)) now = "ON";
            else now="OFF";
            sb.append("#").append(test_case).append(" ").append(now).append("\n");
            
            
		}
        System.out.println(sb);
	}
}
