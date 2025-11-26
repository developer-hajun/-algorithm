import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        // dx, dy 순서는 작성하신 코드 기준: R, U, L, D
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        int[][] visited = new int[n][m]; 
        int[][] move = new int[n][m]; 
        
        for(int i = 0; i < n; i++){
            String now = br.readLine();
            for(int j = 0; j < m; j++){
                char value = now.charAt(j);
                switch (value) {
                    case 'R': move[i][j] = 0; break;
                    case 'U': move[i][j] = 1; break;
                    case 'L': move[i][j] = 2; break;
                    case 'D': move[i][j] = 3; break;
                }
            }
        }

        int counts = 0;
        int idx = 0; 

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] != 0) continue;
                
                idx++; 
                int y = i;
                int x = j;
                
                while(true){
                    visited[y][x] = idx; 
                    
                    int dir = move[y][x];
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if(visited[ny][nx] != 0) {
                        if(visited[ny][nx] == idx) {
                            counts++;
                        }
                        break; 
                    }
                    
                    y = ny;
                    x = nx;
                }
            }
        }
        System.out.println(counts);
    }
}