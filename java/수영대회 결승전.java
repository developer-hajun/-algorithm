import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int y,x,depth;
    public Pair(int a,int b, int c){
        this.y=a;
        this.x=b;
        this.depth=c;
    }
}


class Solution {

    static int[][] arr;

    public static int bfs(Pair s,Pair e){
        boolean[][] visit = new boolean[arr.length][arr[0].length];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(s);
        visit[s.y][s.x] = true;
        while(!queue.isEmpty()){
            Pair now = queue.poll();
            for(int[] move : new int[][]{{0,1},{0,-1},{1,0},{-1,0} }){
                int next_y = now.y+move[0];
                int next_x = now.x+move[1];
                int next_depth = now.depth+1;

                if(next_y<0 || next_y>=arr.length || next_x<0 || next_x>=arr[0].length) continue;

                if(next_y==e.y && next_x==e.x) return next_depth;

                if(arr[next_y][next_x]==1 || visit[next_y][next_x]) continue;
                else if(arr[next_y][next_x]==0) { queue.add(new Pair(next_y,next_x,next_depth)); visit[next_y][next_x]=true;}
                else if(next_depth%3==0){ queue.add(new Pair(next_y,next_x,next_depth)); visit[next_y][next_x]=true;}
                else {queue.add(new Pair(now.y,now.x,next_depth)); visit[now.y][now.x]=true;}

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        for(int test_case=1;test_case<=T;test_case++){
            int n = sc.nextInt();
            arr = new int[n][n];
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    arr[y][x]=sc.nextInt();
                }
            }
            Pair start = new Pair(sc.nextInt(),sc.nextInt(),0);
            Pair end = new Pair(sc.nextInt(),sc.nextInt(),0);
            System.out.println("#"+ test_case+" "+ bfs(start,end));
        }
    }
}