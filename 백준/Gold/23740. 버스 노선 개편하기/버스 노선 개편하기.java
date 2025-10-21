import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] bus = new int[n][3];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int s =  Integer.parseInt(st.nextToken());
            int e =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());
            bus[i]= new int[]{s,e,c};
        }

        Arrays.sort(bus,(o1,o2)-> {
            return o1[0]-o2[0];
        });

        int start=-1;
        int end= -1;
        int cost = Integer.MAX_VALUE;

        List<int[]> answer = new ArrayList<>();

        for(int[] next : bus){
            int ste = next[0],en = next[1],co = next[2];
            if(end<ste){
                answer.add(new int[]{start,end,cost});
                start = ste;
                end=en;
                cost=co;
            }
            else{
                end = Math.max(en,end);
                cost = Math.min(cost,co);
            }
        }
        answer.add(new int[]{start,end,cost});

        System.out.println(answer.size()-1);
        for(int i=1;i<answer.size();i++){
            int[] now = answer.get(i);
            System.out.println(now[0]+ " "+now[1]+" "+now[2]);
        }
        
        
    }
}