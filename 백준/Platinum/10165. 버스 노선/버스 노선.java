import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] buslist = new int[m*2][3];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a>b){
                b = b+n;
            }
            buslist[i*2] = new int[]{a,b,i};
            buslist[i*2+1] = new int[]{a+n,b+n,i};
        }

        Arrays.sort(buslist,(o1,o2)-> {
            
            if(o1[0]==o2[0]) return o2[1]-o1[1];
            return o1[0]-o2[0];});
        boolean[] cancle = new boolean[m];
        int max_end = -1;

        for(int i=0;i<m*2;i++){
            if(buslist[i][1] <= max_end){
                cancle[buslist[i][2]] = true;
            }
            else{
                max_end = buslist[i][1];
            }
        }

        for(int i=0;i<m;i++){
            if(cancle[i]) continue;
            System.out.print((i+1)+" ");
        }
    }
}