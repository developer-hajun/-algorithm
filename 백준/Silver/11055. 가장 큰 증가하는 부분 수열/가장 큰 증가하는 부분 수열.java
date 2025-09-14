import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) nums[i]=Integer.parseInt(st.nextToken());
        
        int[] answer = nums.clone();
        
        for(int i=1;i<n;i++) {
        	for(int j=0;j<i;j++) {
        		if(nums[j]<nums[i]) {
        			answer[i]=Math.max(answer[i],nums[i]+answer[j]);
        		}
        	}
        }
       
        System.out.println(Arrays.stream(answer).max().getAsInt());
    }
}
