import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n,m;
    static int[][] map=new int[101][101];
    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        
        for(int q=0;q<n;q++){
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int g=Integer.parseInt(st.nextToken());
            
            List<Integer> stack = new ArrayList<>();
            map[y][x]=1;
            map[y+dy[d]][x+dx[d]]=1;
            y+=dy[d];
            x+=dx[d];
            stack.add(d);
            //스택에 들어있는 값 반대로 탐색한거에 +1
            for(int i=0;i<g;i++){
                List<Integer> plus = new ArrayList<>();
                for(int e=stack.size()-1;e>=0;e--){
                    int now = (stack.get(e)+1)%4;
                    plus.add(now);
                    y+=dy[now];
                    x+=dx[now];
                    map[y][x]=1;
                }
                stack.addAll(plus);
            }
        }
        int count=0;
        for(int y=0;y<=99;y++){
            for(int x=0;x<=99;x++){
                if(map[y][x]==0) continue;
                if(map[y+1][x]==1 && map[y][x+1]==1 && map[y+1][x+1]==1) count++;
            }
        }
        System.out.println(count);
    }
}
// 오(0) -> 위(1) -> 왼(2)위(1) -> 왼(2)아래(3)왼(2)위(1); 