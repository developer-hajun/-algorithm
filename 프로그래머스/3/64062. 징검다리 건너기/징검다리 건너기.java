import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int left=1,right=200000000;
        while(left<=right){
            int mid= (int)((left+right)/2);
            int cnt=0;
            for(int t : stones){
                if(t-mid<=0) cnt+=1;
                else cnt=0;
                if(cnt>=k) break;
            }
            if(cnt>=k) right=mid-1;
            else left=mid+1;
        }
        return left;
    }
}