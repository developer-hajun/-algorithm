import java.util.*;
class Solution {
    static int[][] matrix;
    
    public int rotate(int sy,int sx,int ey,int ex){
        Deque<Integer> queue = new ArrayDeque<>();
        for(int x=sx;x<=ex;x++) queue.add(matrix[sy][x]);
        for(int y=sy+1;y<=ey;y++) queue.add(matrix[y][ex]);
        for(int x=ex-1;x>=sx;x--) queue.add(matrix[ey][x]);
        for(int y=ey-1;y>=sy+1;y--) queue.add(matrix[y][sx]);
        int value = Collections.min(queue);
        queue.addFirst(queue.pollLast());
        for(int x=sx;x<=ex;x++) matrix[sy][x]=queue.poll();
        for(int y=sy+1;y<=ey;y++) matrix[y][ex]=queue.poll();
        for(int x=ex-1;x>=sx;x--) matrix[ey][x]=queue.poll();
        for(int y=ey-1;y>=sy+1;y--) matrix[y][sx]=queue.poll();
        return value;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        matrix= new int[rows+1][columns+1];
        
        int value = 1;
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=columns;j++){
                matrix[i][j]=value;
                value++;
            }
        }
        int[] answer = new int[queries.length];
        int val=0;
            
        for(int[] command : queries){
            int sy = command[0];
            int sx = command[1];
            int ey = command[2];
            int ex = command[3];
            answer[val]=rotate(sy,sx,ey,ex);
            val++;
        }
        return answer;
    }
}