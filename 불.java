import java.util.*;
import java.io.*;

class fire{
    int y,x;
    public fire(int a,int b){
        y=a;
        x=b;
    }
    @Override
    public boolean equals(Object o){
        fire now = (fire)o;
        if(now.y==this.y && now.x==this.x) return true;
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(y,x);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dy = {0,0,-1,1};
        int[] dx = {-1,1,0,0};
        int t = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=t;tc++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            boolean[][] visit = new boolean[h][w];
            char[][] map = new char[h][w];
            Queue<int[]> queue = new ArrayDeque<>();
            Set<fire> fires = new HashSet<>();
            for(int y=0;y<h;y++){
                String now = br.readLine();
                for(int x=0;x<w;x++){
                    map[y][x] = now.charAt(x);
                    if(map[y][x]=='@'){
                        map[y][x]='.';
                        visit[y][x]=true;
                        queue.add(new int[]{y,x,0});
                    }
                    else if(map[y][x]=='*'){
                        map[y][x]='.';
                        visit[y][x]=true;
                        fires.add(new fire(y,x));
                    }
                }
            }
            boolean check=true;
            A:while(!queue.isEmpty()){
                Set<fire> next_fire = new HashSet<>();

                for(fire f : fires){
                    int y =f.y;
                    int x =f.x;
                    for(int i=0;i<4;i++){
                        int ny= y+dy[i];
                        int nx= x+dx[i];
                        if(ny<0||nx<0||ny>=h||nx>=w) continue;
                        if(visit[ny][nx]||map[ny][nx]=='#') continue;
                        visit[ny][nx] = true;
                        next_fire.add(new fire(ny,nx));    
                    }
                }
                fires=next_fire;
                Queue<int[]> next_queue = new ArrayDeque<>();
                while(!queue.isEmpty()){
                    int[] now = queue.poll();
                    int y=now[0],x=now[1],count = now[2];
                    /*System.out.println(y+":"+x+":"+count);
                    if(tc==2){
                        System.out.println(visit[3][1]);
                    }*/
    
                    for(int i=0;i<4;i++){
                        int ny= y+dy[i];
                        int nx= x+dx[i];
                        if(ny<0||nx<0||ny>=h||nx>=w){
                            System.out.println(count+1);
                            check=false;
                            break A;
                        }
                        if(visit[ny][nx]||map[ny][nx]=='#') continue;
                        visit[ny][nx] = true;
                        next_queue.add(new int[]{ny,nx,count+1});       
                    }
                }
                queue=next_queue;
                
            }
            if(check) System.out.println("IMPOSSIBLE");

            
        }
    }
}
