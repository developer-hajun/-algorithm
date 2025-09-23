import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static shark[] sharks;
    static int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    

    static class shark{
        int id; // 상어 번호
        int y, x, currentDir;
        int[][] dir_priority = new int[4][4];
        boolean isOut = false; 

        public shark(int y, int x, int id) {
            this.y=y;
            this.x=x;
            this.id = id;
        }
        public void set_dirs() throws IOException {
            for(int i=0;i<4;i++) {
                st= new StringTokenizer(br.readLine());
                for(int j=0;j<4;j++) {
                    dir_priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        public void set_dir(int d) {
            this.currentDir = d - 1; 
        }
    }

    public static void main(String[] args) throws Exception {
        st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        sharks = new shark[m];

     
        int[][] smellOwner = new int[n][n]; 
        int[][] smellTime = new int[n][n]; 

        for(int i=0;i<n;i++) {
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                int now = Integer.parseInt(st.nextToken());
                if(now==0) continue;
                sharks[now-1]=new shark(i,j, now); 
            }
        }

        st= new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) sharks[i].set_dir(Integer.parseInt(st.nextToken()));

        for(int i=0;i<m;i++) sharks[i].set_dirs();
        

        for(int i=0; i<m; i++){
            shark sh = sharks[i];
            smellOwner[sh.y][sh.x] = sh.id;
            smellTime[sh.y][sh.x] = k;
        }

        int time=0;
        int count=m;


        while(count > 1 && time < 1000) {
            time++;


            int[][] nextSharkPos = new int[n][n];
            
            for(int i = 0; i < m; i++) {
                if(sharks[i].isOut) continue;

                shark sh = sharks[i];
                boolean moved = false;
                

                for(int d=0; d<4; d++) {
                    int moveDir = sh.dir_priority[sh.currentDir][d];
                    int ny = sh.y + dir[moveDir][0];
                    int nx = sh.x + dir[moveDir][1];

                    if(ny<0||nx<0||ny>=n||nx>=n) continue;
                    
                    if (smellOwner[ny][nx] == 0) {
                        sh.y = ny;
                        sh.x = nx;
                        sh.currentDir = moveDir;
                        moved = true;
                        break;
                    }
                }
                
                if(moved) continue;

                for(int d=0; d<4; d++) {
                    int moveDir = sh.dir_priority[sh.currentDir][d];
                    int ny = sh.y + dir[moveDir][0];
                    int nx = sh.x + dir[moveDir][1];

                    if(ny<0||nx<0||ny>=n||nx>=n) continue;

                    if (smellOwner[ny][nx] == sh.id) {
                        sh.y = ny;
                        sh.x = nx;
                        sh.currentDir = moveDir;
                        break;
                    }
                }
            }

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if (smellTime[i][j] > 0) {
                        smellTime[i][j]--;
                        if (smellTime[i][j] == 0) {
                            smellOwner[i][j] = 0;
                        }
                    }
                }
            }
            
            shark[][] tempMap = new shark[n][n];
            for(int i=0; i<m; i++) {
                if(sharks[i].isOut) continue;
                shark sh = sharks[i];
                
                if(tempMap[sh.y][sh.x] == null) { 
                    tempMap[sh.y][sh.x] = sh;
                } else { 
                    if (tempMap[sh.y][sh.x].id > sh.id) { 
                        tempMap[sh.y][sh.x].isOut = true; 
                        count--;
                        tempMap[sh.y][sh.x] = sh; 
                    } else { 
                        sh.isOut = true; 
                        count--;
                    }
                }
            }
            
            for(int i=0; i<m; i++) {
                if(sharks[i].isOut) continue;
                shark sh = sharks[i];
                smellOwner[sh.y][sh.x] = sh.id;
                smellTime[sh.y][sh.x] = k;
            }
        }
        
     
        System.out.println(time >= 1000 && count > 1 ? -1 : time);
    }
}