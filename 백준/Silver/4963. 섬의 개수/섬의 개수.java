import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            int M = sc.nextInt();int N = sc.nextInt();
            if(N+M==0) break;
            int[][] board = new int[N][M];
            boolean[][] visit = new boolean[N][M];
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
    
            int count=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visit[i][j] && board[i][j] == 1) {
                        count++;
                        int size=1;
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.add(new int[]{i,j});
                        visit[i][j]=true;
                        while(!queue.isEmpty()){
                            int[] now = queue.poll();
                            int y=now[0],x=now[1];
                            for(int[] next : new int[][] { {y+1,x},{y-1,x},{y,x+1} ,{y,x-1},{y+1,x+1},{y-1,x-1},{y-1,x+1} ,{y+1,x-1}   }){
                                int ny = next[0],nx=next[1];
                                if(ny<0||ny>=N||nx<0||nx>=M||visit[ny][nx]||board[ny][nx]==0) continue;
                                visit[ny][nx]=true;
                                size++;
                                queue.add(new int[]{ny,nx});
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }
}
