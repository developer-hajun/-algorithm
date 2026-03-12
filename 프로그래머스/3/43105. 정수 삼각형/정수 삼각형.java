import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for(int i=1;i<triangle.length;i++){
            triangle[i][0] += triangle[i-1][0];
            triangle[i][triangle[i].length-1] +=triangle[i-1][triangle[i-1].length-1];
            for(int j=1;j<triangle[i].length-1;j++){
                triangle[i][j] +=Math.max(triangle[i-1][j-1],triangle[i-1][j]);
            }
        }
        for(int i=0;i<triangle[triangle.length-1].length;i++){
            answer = Math.max(answer,triangle[triangle.length-1][i]);
        }
        
        return answer;
    }
}