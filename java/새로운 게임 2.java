import java.util.*;
import java.lang.*;
import java.io.*;

@SuppressWarnings("unchecked")
class Main {
    static class horse{
        int y;
        int x;
        int dir;
        public horse(int a,int b,int d){
            y=a;
            x=b;
            dir =d;
        }
    }
    static Map<Integer,horse> horses = new HashMap<>();
    static List<horse>[][] horse_map;
    static int[][] map;
    static int n,k;
    static int[] dy = {0,0,-1,1};
    static int[] dx = {1,-1,0,0};

    public static int[] move(int y,int x,int dir){
        int ny = y+dy[dir];
        int nx = x+dx[dir];
        if(ny<0||ny>=n||nx<0||nx>=n){}
        else if(map[ny][nx]!=2){
            return new int[]{ny,nx,dir};
        }
        switch (dir) {
            case 0:
                dir=1;
                break;
            case 1:
                dir=0;
                break;
            case 2:
                dir=3;
                break;
            case 3:
                dir=2;
                break;
        }
        ny = y+dy[dir];
        nx = x+dx[dir];
        if(ny<0||ny>=n||nx<0||nx>=n){}
        else if(map[ny][nx]!=2){
            return new int[]{ny,nx,dir};
        }

        return new int[]{y,x,dir};
        
    }

    public static boolean sim(){
        for(int i=1;i<=k;i++){
            horse now = horses.get(i);
            int y = now.y;
            int x = now.x;
            int dir = now.dir;

            int[] m = move(y,x,dir);
            int my = m[0];
            int mx = m[1];
            now.dir = m[2];
            
            if(y==my && x==mx) continue;
            //이동할 위치 정하기

            
            
            int index = horse_map[y][x].indexOf(now);
            List<horse> separated = new ArrayList<>(horse_map[y][x].subList(index, horse_map[y][x].size()));
            horse_map[y][x].subList(index, horse_map[y][x].size()).clear();
            //이동 할 부분 정하고 이동할 거 자르기
            for(horse h : separated){
                h.y=my;
                h.x=mx;
            }
            if(map[my][mx]==1){
                Collections.reverse(separated);
            }
            horse_map[my][mx].addAll(separated);
            if(horse_map[my][mx].size()>=4) return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        
        st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());
        horse_map = new ArrayList[n][n];
        map = new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                    map[y][x]= Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                horse_map[i][j] = new ArrayList<>();
            }
        }
        
        for(int i=1;i<=k;i++){
            st=new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x= Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            horse n_horse = new horse(y,x,dir);
            horses.put(i,n_horse);
            horse_map[y][x].add(n_horse);
        }

        int count=0;
        while(true){
            count++;
            if(count>1000){
                System.out.println(-1);
                return;
            }
            if(sim()){
                break;
            }
        }
        System.out.println(count);
        
        
    
            
    }
}