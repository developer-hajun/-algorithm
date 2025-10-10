import java.util.*;
class cars{
    int y;
    int x;
    int count;
    int dist;
    cars(int ny,int nx,int ncount,int ndist){
        y=ny;
        x=nx;
        count=ncount;
        dist=ndist;
    }
}
class Solution {
    public int solution(int[][] board) {
        int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
        int[][][] visit = new int[board.length][board[0].length][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (int d = 0; d < 4; d++) {
                    visit[i][j][d] = Integer.MAX_VALUE;
                }
            }
        }
        visit[0][0]= new int[4];
        Queue<cars> queue = new LinkedList<>();
        queue.add(new cars(0,0,0,0));
        queue.add(new cars(0,0,0,2));
        while(!queue.isEmpty()){
            cars now = queue.poll();
            int[] move_dist={0,1,2,3};;

            for(int dist : move_dist){
                int now_count = (now.dist==dist) ? now.count+100 : now.count+600;
                int ny = now.y+move[dist][0];
                int nx = now.x+move[dist][1];
                
                if (0<=ny && ny<board.length && 0<=nx && nx<board[0].length && board[ny][nx]==0  &&visit[ny][nx][dist]>now_count){
                    
                    visit[ny][nx][dist]=now_count;
                    queue.add(new cars(ny,nx,now_count,dist));
                }
            }   
        }

        return Arrays.stream(visit[board.length-1][board[0].length-1]).min().getAsInt();
    }
}