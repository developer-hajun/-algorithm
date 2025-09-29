import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n,m,h;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> find;

    public static boolean check(){
        for(int x=1;x<=n;x++){
            int start = x;
            for(int y=1;y<=h;y++){
                if(map[y][start]==1) start = start+1;
                else if(map[y][start-1]==1) start=start-1;
            }
            if(start!=x) return false;
        }
        return true;
    }

    public static void dfs(int count,int start){
        if(count>=answer) return;
        if(check()){
            answer=count;
            return;
        }
        if(count==3) return;

        for(int i=start;i<find.size();i++){
            int[] now = find.get(i);
            int y = now[0],x=now[1];
            if (map[y][x - 1] == 1 || map[y][x + 1] == 1) continue;
            map[y][x]=1;
            dfs(count+1,i+1);
            map[y][x]=0;
        }
    }

    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h+1][n+2];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b]=1;
        }
        find = new ArrayList<>();
        for(int y=1;y<=h;y++){
            for(int x=1;x<=n;x++){
                if(map[y][x]==1||map[y][x-1]==1||map[y][x+1]==1) continue;
                find.add(new int[]{y,x});
            }
        }
        dfs(0,0);
        System.out.println(answer>3 ? -1:answer);
    }
}