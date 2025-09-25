import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] student = new int[n];
        Arrays.fill(student,1);
        for(int i : lost) student[i-1]--;
        for(int i : reserve) student[i-1]++;
        
        for(int i=0;i<n;i++){
            if(student[i]<=1) continue;
            if(student[i]>=2){
                boolean left = i-1<0 ? true : student[i-1]==0 ? false:true;
                boolean right = i+1>=n ? true : student[i+1]==0 ? false:true;
                if((!left&&right)||(!left&&!right)){
                    if(i-1<0) continue;
                    student[i-1]++;
                    student[i]--;
                }
                else if(left&&!right){
                    if(i+1>=n) continue;
                    student[i+1]++;
                    student[i]--;
                }
                else if(left&&right){
                    if(i-1<0) continue;
                    student[i-1]++;
                    student[i]--;
                }
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(student[i]!=0) count++;
        }
        return count;
    }
}