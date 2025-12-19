import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];
        boolean[][] visit = new boolean[n][n];

        int count=0;

        for(int i=0;i<n;i++){
            String now = br.readLine();
            for(int j =0;j<n;j++){
                map[i][j]=now.charAt(j);
            }
        }
        for(int i=0;i<n;i++){
            for(int j =0;j<n;j++){
                if(visit[i][j]) continue;
                count++;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i,j,map[i][j]});
                visit[i][j]= true;
                while(!queue.isEmpty()){
                    int[] now = queue.poll();
                    int y = now[0];
                    int x= now[1];
                    char value = (char)now[2];

                    for(int dir=0;dir<4;dir++){
                        int ny = y+dy[dir];
                        int nx = x+dx[dir];
                        if(ny<0||ny>=n||nx<0||nx>=n||map[ny][nx]!=value||visit[ny][nx]) continue;
                        visit[ny][nx]=true;
                        queue.add(new int[]{ny,nx,value});
                    }    
                }
            }
        }
        System.out.print(count+ " ");

        visit = new boolean[n][n];
        count=0;
        
        for(int i=0;i<n;i++){
            for(int j =0;j<n;j++){
                if(visit[i][j]) continue;
                count++;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i,j,map[i][j]});
                visit[i][j]= true;
                while(!queue.isEmpty()){
                    int[] now = queue.poll();
                    int y = now[0];
                    int x= now[1];
                    char value = (char)now[2];

                    for(int dir=0;dir<4;dir++){
                        int ny = y+dy[dir];
                        int nx = x+dx[dir];
                        if(ny<0||ny>=n||nx<0||nx>=n||visit[ny][nx]) continue;
                        if((value=='R'||value=='G') && (map[ny][nx]=='R'||map[ny][nx]=='G')){
                            visit[ny][nx]=true;
                            queue.add(new int[]{ny,nx,value});
                        }
                        else if(map[ny][nx]==value){
                            visit[ny][nx]=true;
                            queue.add(new int[]{ny,nx,value});
                        }
                        
                    }    
                }
            }
        }
        System.out.println(count);
    }
}
