import java.util.*;
import java.io.*;

class ceil{
    int y,x,live_time,dead_time,value;
    public ceil(int a,int b,int c, int d,int v){
        y=a;x=b;live_time=c;dead_time=d;value =v;
    }
}
class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Set<String> visit = new HashSet<>();//방문
            PriorityQueue<ceil> not_live = new PriorityQueue<>( (o1,o2)-> { return o1.live_time-o2.live_time;});
            PriorityQueue<ceil> live = new PriorityQueue<>( (o1,o2)-> { return o1.dead_time-o2.dead_time;});
            for(int y=0;y<n;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<m;x++){
                    int now = Integer.parseInt(st.nextToken());
                    String value = y+","+x;
                    if(now==0) continue;
                    visit.add(value);
                    not_live.add(new ceil(y,x,now,now+now,now));
                }
            }

            int[] dy ={0,0,1,-1};
            int[] dx ={-1,1,0,0};
            for(int time=1;time<k;time++){
                //활성 상태인것중 죽일꺼 죽이기
                while(!live.isEmpty()&&live.peek().dead_time<=time){
                    live.poll();
                }
                PriorityQueue<ceil> wait = new PriorityQueue<>( (o1,o2)-> { return o2.value-o1.value;});
                //비활성 상태인것중 활성상태로 바꿔야 할것들 활성시키기
                while(!not_live.isEmpty()&&not_live.peek().live_time<=time){
                    ceil c = not_live.poll();
                    live.add(c);
                    //활성시키기
                    int y = c.y;
                    int x = c.x;
                    int value = c.value;
                    //번식
                    for(int i=0;i<4;i++){
                        int ny = y+dy[i];
                        int nx = x+dx[i];
                        wait.add(new ceil(ny,nx,time+value+1,time+value+1+value,value));
                    }
                }
                while(!wait.isEmpty()){
                    ceil c = wait.poll();
                    int y = c.y;
                    int x = c.x;
                    String value = y+","+x;
                    if(visit.contains(value)) continue;
                    visit.add(value);
                    not_live.add(c);
                }
            }
            while(!live.isEmpty()&&live.peek().dead_time<=k){
                    live.poll();
            }
            int answer = not_live.size()+live.size();
            System.out.println("#"+tc+ " "+answer);
        }
    }
}
