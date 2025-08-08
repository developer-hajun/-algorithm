import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int n,m,y,x,dir;
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        for(int y=0;y<n;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<m;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        int answer= 0;
        A: while(true){
            if(arr[y][x]==0){
                arr[y][x]=2;
                answer++;
            }

            //1번
            
            boolean clean_check = true;
            for(int[] next : move){
                int ny = y+next[0];
                int nx = x+next[1];
                if(ny<0||ny>=n||nx<0||nx>=m) continue;
                if(arr[ny][nx]==0){
                    clean_check=false;
                    break;
                }
            }
            // 2.주변 4칸 청소 여부 체크

            
            if(clean_check){ //3.청소 할 칸 없는경우
                int ny = y-move[dir][0];
                int nx = x-move[dir][1];
                if(ny<0||ny>=n||nx<0||nx>=m||arr[ny][nx]==1) break A;
                y=ny;x=nx;
            }
            else{ //3. 청소 할 칸 있는 경우
                dir = dir-1==-1 ? 3:dir-1;
                int ny = y+move[dir][0];
                int nx = x+move[dir][1];
                if(ny<0||ny>=n||nx<0||nx>=m||arr[ny][nx]!=0) continue;
                y=ny;x=nx;
            }
        }
        System.out.println(answer);
    }
}