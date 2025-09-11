import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();

    static List<int[]> human = new ArrayList<>();
    static List<int[]> stair = new ArrayList<>();
    static int[] choice;
    static int answer;
    
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=t;tc++){
            
            int n = Integer.parseInt(br.readLine());

            human.clear();
            stair.clear();

            for(int y=0;y<n;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<n;x++){
                    int value = Integer.parseInt(st.nextToken());
                    if(value==1) human.add(new int[]{y,x});
                    else if(value>1) stair.add(new int[]{y,x,value});
                }
            }
            
            choice = new int[human.size()];
            answer = Integer.MAX_VALUE;

            pick(0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
            
        }
        System.out.println(sb);
    }

    public static void pick(int count){
        if(count==human.size()){
            answer = Math.min(answer,Math.max(solve(0),solve(1)));
            return;
        }
        choice[count]=0;
        pick(count+1);
        choice[count]=1;
        pick(count+1);
    }

    public static int solve(int choice_stair){
        List<Integer> move_time = new ArrayList<>();
        int[] now_stair = stair.get(choice_stair);
        for(int i=0;i<human.size();i++){
            if(choice[i]!=choice_stair) continue;
            int a = Math.abs(now_stair[0]-human.get(i)[0]);
            int b = Math.abs(now_stair[1]-human.get(i)[1]);
            move_time.add(a+b);
        }
        Collections.sort(move_time);
        
        Queue<Integer> onStair = new ArrayDeque<>();
        int finishTime = 0;

        for (int move : move_time) {
            int startTime = move + 1;


            while (onStair.size() >= 3) {
                int done = onStair.poll();
                if (done > startTime) startTime = done;
            }

            int endTime = startTime + now_stair[2];
            onStair.add(endTime);
            finishTime = Math.max(finishTime, endTime);
        }

        return finishTime;
    }
    

    
}
