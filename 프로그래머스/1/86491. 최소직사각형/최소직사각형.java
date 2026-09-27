import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int l=0,r=0;
        for(int[] s : sizes){
            int q = Math.max(s[0],s[1]);
            int w = Math.min(s[0],s[1]);
            l=Math.max(l,q);
            r=Math.max(r,w);
        }
        
        
        return l*r;
    }
}

//음 가로길이,세로길이?