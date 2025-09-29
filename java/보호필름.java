import java.util.*;
import java.lang.*;
import java.io.*;


class Solution {
    static int d,w,k;
    static int[][] map;
    static int[] A;
    static int[] B;
    static int answer;

    public static boolean check() {
        for (int x = 0; x < w; x++) {
            int count = 1; 
            int real = 1;
            for (int y = 1; y < d; y++) {
                if (map[y][x] == map[y-1][x]) {
                    count++;
                } else {
                    count = 1;
                }
                real = Math.max(real, count);
            }
            if (real < k) return false;
        }
        return true;
    }

    public static void dfs(int depth,int count){
        if(count>=answer) return;
        if(check()){
            answer = count;
            return;
        }
        for(int next = depth;next<d;next++){
            int[] temp = map[next];
            map[next] = A.clone();
            dfs(next+1,count+1);
            map[next] = B.clone();
            dfs(next+1,count+1);
            map[next]=temp;
        }
        
        
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=t;tc++){
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            answer = k;
            map = new int[d][w];
            A = new int[w];
            B = new int[w];
            Arrays.fill(B,1);
            for(int y=0;y<d;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<w;x++){
                    map[y][x]= Integer.parseInt(st.nextToken());
                }
            }
            dfs(0,0);
            System.out.println("#"+tc+" "+answer);
            
        }
    }
}