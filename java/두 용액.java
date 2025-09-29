import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) nums[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        int start=0;
        int end = n-1;
        int left=0,right=0,min_value=Integer.MAX_VALUE;
        while(start<end){
            int value = nums[start]+nums[end];
            int abs = Math.abs(value);
            if(min_value>abs){
                min_value = abs;
                left = nums[start];
                right = nums[end];
            }

            if(value<0){
                start++;
            }
            else if(value>0){
                end--;
            }
            else{
                break;
            }
        }
        System.out.println(left+" "+right);
        //-일경우에는 왼쪽을 더 큰걸로
        //+일 경우에는 오른쪽을 더 작은걸로
    }
}