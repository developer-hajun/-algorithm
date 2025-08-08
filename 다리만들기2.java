import java.util.*;
import java.lang.*;
import java.io.*;

class Pair{
    int y;
    int x;
    public Pair(int a,int b){
        y=a;
        x=b;
    }
}
class Edge{
    int start;
    int end;
    int count;
    public Edge(int a, int b ,int c){
        start=a;
        end=b;
        count=c;
    }

    @Override
    public boolean equals(Object o){
        Edge st = (Edge)o;
        int thisStart = Math.min(this.start, this.end);
        int thisEnd = Math.max(this.start, this.end);
        int otherStart = Math.min(st.start, st.end);
        int otherEnd = Math.max(st.start, st.end);
        return thisStart == otherStart && thisEnd == otherEnd && this.count == st.count;
    }

    @Override 
    public int hashCode(){
        int a = Math.min(start, end);
        int b = Math.max(start, end);
        return Objects.hash(a, b, count);
    }
}

class Main {
    static int n,m;
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static Set<Edge> edges = new HashSet<>();
    static int[] answer;

    public static void land() throws Exception{
        arr = new int[n][m];
        for(int y=0;y<n;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<m;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        int[][] land = new int[n][m];
        boolean[][] visit = new boolean[n][m];
        int num=1;
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                if(arr[y][x]==0 || visit[y][x]) continue;
                Queue<Pair> queue = new ArrayDeque<>();
                visit[y][x]=true;
                land[y][x]=num;
                queue.add(new Pair(y,x));
                while(!queue.isEmpty()){
                    Pair now = queue.poll();
                    for(int[] move : new int[][]{{now.y+1,now.x},{now.y-1,now.x},{now.y,now.x+1},{now.y,now.x-1}}){
                        int ny = move[0],nx=move[1];
                        if(ny<0 || ny>=n ||nx<0||nx>=m||arr[ny][nx]==0||visit[ny][nx]) continue;
                        land[ny][nx]=num;
                        visit[ny][nx]=true;
                        queue.add(new Pair(ny,nx));
                    }
                }
                num++;
            }
        }
        answer=new int[num+1];
        for(int i=1;i<=num;i++) answer[i]=i;
        arr=land;
    }
    public static void find_edge(int y,int x,int start){
        
        for(int[] move:new int[][]{{-1,0},{1,0},{0,1},{0,-1}}){
            int k=1;
            boolean check = true;
            int ny,nx;
            while(true){
                ny = y+move[0]*k;
                nx = x+move[1]*k;
                if(ny<0 || ny>=n ||nx<0||nx>=m||arr[ny][nx]==start){
                    check=false;
                    break;
                }
                else if(arr[ny][nx]!=0){
                    break;
                }
                k++;
            }
            if(check && k>2){
                edges.add(new Edge(start,arr[ny][nx],k-1));
            }
        }
    }

    public static int find(int x){
        if(answer[x]!=x){
            answer[x]=find(answer[x]);
        }
        return answer[x];
    }

    public static void union(int a,int b,int c,int d){
        if(a<b) answer[a]=d;
        else answer[b]=c;
        
    }
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        
        land();
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                if(arr[y][x]==0) continue;
                find_edge(y,x,arr[y][x]);
            }
        }
        List<Edge> edgeList = new ArrayList<>(edges);
        edgeList.sort((o1,o2)->{
            return o1.count-o2.count;
        });
        int ans=0;
        int ans2=0;
        for(Edge edge:edgeList){
            int start =edge.start , end=edge.end,count = edge.count;
            int l = find(start);
            int r = find(end);
            if(l!=r){
                ans+=1;
                union(l,r,start,end);
                ans2+=count;
            }
        }
        if(ans!=(answer.length-3)){
            System.out.println(-1);
        }
        else{
            System.out.println(ans2);
        }
    }
}