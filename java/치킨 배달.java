import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n,m;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> house = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static int[][] pick;

    public static int sim(){
        int ans=0;
        for(int[] h: house){
            int value = Integer.MAX_VALUE;
            for(int[] p :pick){
                value=Math.min(value,Math.abs(h[0]-p[0])+Math.abs(h[1]-p[1]));
            }
            ans+=value;
        }
        return ans;
    }

    public static void dfs(int count,int start){
        if(count==m){
            answer=Math.min(sim(),answer);
            return;
        }
        for(int i = start;i<chicken.size();i++){
            pick[count] = chicken.get(i);
            dfs(count+1,i+1);
        }
    }

    
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pick = new int[m][2];

        map = new int[n][m];
        
        for(int y=0;y<n;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                int now = Integer.parseInt(st.nextToken());
                if(now==1) house.add(new int[]{y,x});
                else if(now==2) chicken.add(new int[]{y,x});
            }
        }
        dfs(0,0);
        System.out.println(answer);
    }
}