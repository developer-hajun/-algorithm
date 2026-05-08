import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int[][] visit= new int[maps.length][maps[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visit[0][0]=1;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int[] next : new int[][]{{1,0},{0,1},{0,-1},{-1,0}}){
                int ny = now[0]+next[0];
                int nx = now[1]+next[1];
                if(ny<0||ny>=maps.length||nx<0||nx>=maps[0].length||visit[ny][nx]!=0||maps[ny][nx]==0) continue;
                visit[ny][nx]=visit[now[0]][now[1]]+1;
                queue.add(new int[]{ny,nx});
            }
        }
        if(visit[maps.length-1][maps[0].length-1]==0) return -1;
        return visit[maps.length-1][maps[0].length-1];
    }
}