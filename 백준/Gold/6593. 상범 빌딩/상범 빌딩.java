import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dz = {0,0,0,0,-1,1};
    static int[] dy = {0,1,0,-1,0,0};
    static int[] dx = {1,0,-1,0,0,0};
    
    public static void main(String[] args) throws Exception{
        while(true){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(l+r+c==0) return;
            
            int sz=0,sy=0,sx=0;
            int ez=0,ey=0,ex=0;
            boolean[][][] visit = new boolean[l][r][c];
    
            for(int i=0;i<l;i++){
                for(int j=0;j<r;j++){
                    String now = br.readLine();
                    for(int k=0;k<c;k++){
                        char value = now.charAt(k);
                        switch (value){
                            case '#':
                                visit[i][j][k]=true;
                                break;
                            case 'S':
                                visit[i][j][k]=true;
                                sz=i;sy=j;sx=k;
                                break;
                            case 'E':
                                ez=i;ey=j;ex=k;
                                break;
                        }
                            
                    }
                }
                br.readLine();
            }
            Queue<int[]> queue = new ArrayDeque<>();
            int answer=-1;
            queue.add(new int[]{sz,sy,sx,0});
            while(!queue.isEmpty()){
                int[] now = queue.poll();
                int z = now[0];
                int y = now[1];
                int x = now[2];
                int count= now[3];
                for(int i=0;i<6;i++){
                    int nz = z+dz[i];
                    int ny = y+dy[i];
                    int nx = x+dx[i];
                    int ncount = count+1;
                    if(nz<0||nz>=l||ny<0||ny>=r||nx<0||nx>=c||visit[nz][ny][nx]) continue;
                    visit[nz][ny][nx]=true;
                    if(nz==ez&&ny==ey&&nx==ex){
                        answer=ncount;
                        break;
                    }
                    queue.add(new int[]{nz,ny,nx,ncount});
                }
                if(answer!=-1) break;
            }
            if(answer==-1){
                System.out.println("Trapped!");
            }
            else{
                System.out.println("Escaped in "+answer+" minute(s).");
            }
        }
    }
}