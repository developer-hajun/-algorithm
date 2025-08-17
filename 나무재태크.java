import java.util.*;
import java.lang.*;
import java.io.*;


class tree{
    int y,x,age;
    public tree(int a,int b,int c){
        y=a;x=b;age=c;
    }
}

class Main {

    static int n,m,k;
    static int[][] map;
    static int[][] plus;
    static int[] dy = {0,-1,0,1,-1,1,-1,1};
    static int[] dx = {1,0,-1,0,1,-1,-1,1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        plus = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=5;
                plus[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        PriorityQueue<tree> trees = new PriorityQueue<>((o1,o2)->{return o1.age-o2.age;});

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.add(new tree(x-1,y-1,z));
        }
        for(int year=1;year<=k;year++){
            PriorityQueue<tree> next_trees = new PriorityQueue<>((o1,o2)->{return o1.age-o2.age;});
            List<tree> dead_trees = new ArrayList<>();
            List<tree> five_tree = new ArrayList<>();
            // 다음에도 살아있는트리,죽은트리,번식하는트리
            while(!trees.isEmpty()){
                tree t = trees.poll();
                int y = t.y;
                int x = t.x;
                if(map[y][x]>=t.age){
                    map[y][x]-=t.age;
                    t.age+=1;
                    next_trees.add(t);
                    if(t.age%5==0) five_tree.add(t);
                }
                else{
                    dead_trees.add(t);
                }
            }//양분먹기
            trees = next_trees;
            for(tree t : dead_trees){
                map[t.y][t.x]+=t.age/2;
            }//죽은 나무 양분으로주기

            for(tree t : five_tree){
                int y = t.y;
                int x = t.x;
                for(int i=0;i<8;i++){
                    int ny = y+dy[i];
                    int nx = x+dx[i];
                    if(ny<0||nx<0||ny>=n||nx>=n) continue;
                    trees.add(new tree(ny,nx,1));
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    map[i][j]+=plus[i][j];
                }
            }
        }
        System.out.println(trees.size());

    }
}
