import java.util.*;
import java.lang.*;
import java.io.*;

class Hole{
    int y;
    int x;

    public Hole(int a,int b){
        y=a;
        x=b;
    }
}
class Main {

    static int n;
    static int[][] map;
    static List<Map<String,Hole>> wormhole;
    static int dy[] = {0,0,1,-1};
    static int dx[] = {1,-1,0,0};
    static int[][] block = {
        {},
        {-1,3,0,-1},//오,왼,아,위
        {-1,2,-1,0},
        {2,-1,-1,1},
        {3,-1,1,-1},
        {-1,-1,-1,-1},
    };

    static int next_dir(int dir,int num){return block[num][dir];}

    static int sim(int y,int x,int dir){
        int count=0;
        int sy = y;
        int sx = x;
        while(true){
            y = y+dy[dir]; 
            x = x+dx[dir];

            if(y<0 || x<0 || y>=n||x>=n){
                count=count*2+1;
                break;
            }
                
            if(y==sy && x==sx) break;
            
            if(map[y][x]==-1) break;

            if(map[y][x]==0) continue;

            if(map[y][x]<=5){
                dir = next_dir(dir,map[y][x]);
                count++;
            }

            if(dir==-1){
                count=count*2-1;
                break;
            }

            if(map[y][x]>=6){
                Hole next = wormhole.get(map[y][x]).get(y+","+x);
                y=next.y;
                x=next.x;
            }
        }
        return count;
    }


    
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        
        
        int t = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1;test_case<=t;test_case++){
            n = Integer.parseInt(br.readLine().trim());
            

            wormhole = new ArrayList<>();
            for(int i=0;i<=10;i++) wormhole.add(new HashMap<>());
            map = new int[n][n];
            int answer = 0;
            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]>5){
                        Map<String,Hole> hole = wormhole.get(map[i][j]);
                        if(hole.size()!=0){
                            String key = hole.keySet().iterator().next();
                            String[] parts = key.split(",");
                            int left = Integer.parseInt(parts[0]); 
                            int right = Integer.parseInt(parts[1]);
                            hole.put(key,new Hole(i,j));
                            hole.put(i+","+j,new Hole(left,right));
                        }
                        else{
                            hole.put(i+","+j,null);
                        }
                    }
                }
            }

            
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    if(map[y][x]!=0) continue;
                    for(int i=0;i<4;i++){
                        answer=Math.max(sim(y,x,i),answer);
                    }
                }
            }
            System.out.println("#"+test_case+ " "+answer);
            
        }
    }
}