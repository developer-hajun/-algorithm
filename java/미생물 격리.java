import java.util.*;
import java.io.*;


class live{
    int y,x,value,dir;
    public live(int a,int b,int v,int d){
        y =a;x=b;
        value =v;dir=d;
    }
}
public class Main {
    
    static live[][] map;
    static int[] dy = {0,-1,1,0,0};
    static int[] dx = {0,0,0,-1,1};
    static int[] next_dir = {-1,2,1,4,3};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());


        for(int test_case = 1;test_case<=t;test_case++){
            st = new StringTokenizer(br.readLine());
            int n= Integer.parseInt(st.nextToken()),m=Integer.parseInt(st.nextToken()),k=Integer.parseInt(st.nextToken());
            map = new live[n][n];

            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                int y= Integer.parseInt(st.nextToken());
                int x= Integer.parseInt(st.nextToken());
                int value= Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[y][x] = new live(y,x,value,dir);
            }

            for(int e=0;e<m;e++){
                List<live> next = new ArrayList<>();
                for(int y=0;y<n;y++){
                    for(int x=0;x<n;x++){
                        if(map[y][x]==null) continue;
                        live now = map[y][x];
                        map[y][x]=null;
                        int ny = now.y+dy[now.dir];
                        int nx = now.x+dx[now.dir];

                        if(ny==0||nx==0||ny==n-1||nx==n-1){
                            now.value /=2;
                            now.dir = next_dir[now.dir];
                        }
                        if(now.value==0) continue;
                        now.y=ny;
                        now.x=nx;
                        next.add(now);
                    }
                }
                //이동
                int[][] bigest= new int[n][n];
                for(live l : next){
                    int y = l.y;
                    int x = l.x;
                    int value = l.value;
                    int dir = l.dir;
                    if(bigest[y][x]==0){
                        bigest[y][x]=value;
                        map[y][x] = l;
                    }
                    else if(bigest[y][x]<value){
                        bigest[y][x]=value;
                        live now = map[y][x];
                        now.value+=value;
                        now.dir = dir;
                    }
                    else{
                        live now = map[y][x];
                        now.value+=value;
                    }
                }//합쳐지기
            }
            int answer=0;
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    if(map[y][x]==null) continue;
                    answer+=map[y][x].value;
                }
            }
            System.out.println("#"+test_case+" "+answer);
            
        }
    }
}
