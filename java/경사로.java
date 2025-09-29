import java.util.*;
import java.lang.*;
import java.io.*;


class cam{
    int y;
    int x;
    int number;
    public cam(int a,int b,int c){
        y=a;
        x=b;
        number = c;
    }
}

class Main {
    static int[][][][] arr = {{},
    {{{0,1}},{{1,0}},{{-1,0}},{{0,-1}}},
    {{{0,1},{0,-1}},{{1,0},{-1,0}}},
    {{{0,1},{-1,0}},{{0,1},{1,0}},{{0,-1},{1,0}},{{0,-1},{-1,0}}},
    {{{0,1},{1,0},{-1,0}},{{0,-1},{1,0},{-1,0}},{{1,0},{0,-1},{0,1}},{{-1,0},{0,1},{0,-1}}},
    {{{0,1},{-1,0},{1,0},{0,-1}}}};
    static int[][] original_map;
    static int[][] copy_map;
    static int zero=0,n,m;
    static List<cam> cams = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static int answer =0;

    static int[][] copy(int[][] map) {
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            res[i] = map[i].clone();
        }
        return res;
    }

    static int vue(int[][] map,int y,int x,int dy,int dx){
        int count=0;
        int k=0;
        while(true){
            int ny = y+dy*k;
            int nx = x+dx*k;
            if(ny<0 || nx<0 ||ny>=n||nx>=m||map[ny][nx]==6) break;
            if(map[ny][nx]==0){
                count++;
                map[ny][nx]=9;
            }
            k++;
        }
        return count;
        
    }
    public static void dfs(int[][] map,int depth,int count){
        if(depth==cams.size()){
            answer= Math.max(answer,count);
            return;
        }
        cam now = cams.get(depth);

        for(int[][] see:arr[now.number]){
            int[][] copy_map =  copy(map);
            int counts=0;
            for(int[] watch:see){
                counts+=vue(copy_map,now.y,now.x,watch[0],watch[1]);
            }
            dfs(copy_map,depth+1,count+counts);
        }
        
        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        original_map = new int[n][m];
        for(int y=0;y<n;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<m;x++){
                original_map[y][x] = Integer.parseInt(st.nextToken());
                if(original_map[y][x]==0) zero++;
                else if(original_map[y][x]!=6) cams.add(new cam(y,x,original_map[y][x]));
            }
        }

        dfs(original_map,0,0);
        
        

        System.out.println(zero-answer);
        //각 캠에서 0을 차지할 수 있는 경우의 수를 뽑음

        
    }
}
