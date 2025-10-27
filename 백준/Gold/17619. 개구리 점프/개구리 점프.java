import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  
        int q = Integer.parseInt(st.nextToken());  
        int[][] value = new int[n][2];  

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            value[i] = new int[] {l,r}; 
        }
        Arrays.sort(value, (o1, o2) ->{ return o1[0] - o2[0];});

        int quest[][] = new int[q][2];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            quest[i] = new int[] {l,r}; 
        }

        Arrays.sort(quest, (o1, o2) ->{
             if(o1[0]==o2[0]) return o1[1]-o2[1];
             return o1[0] - o2[0];}
        );

        int quest_count = 0;
        List<int[]> gugan = new ArrayList<>();
        int left=value[0][0];
        int right=value[0][1];
        int[] idxs = new int[n];  
        int idx=0;
        idxs[0]=0;
        
        
        for(int i=1;i<n;i++){
            int[] wood = value[i];
            int n_l = wood[0];
            int n_r = wood[1];
            

            if(n_l>right){
                left = n_l;
                right = n_r;
                idx++;
                idxs[i]=idx;
            }
            else{
                right = n_r;
                idxs[i]=idx;
            }
        }
        
        for(int i=0;i<q;i++){
            int l = quest[i][0]-1;
            int r = quest[i][1]-1;
            if(idxs[l]==idxs[r]) System.out.println(1);
            else System.out.println(0);
        }
        
        
    }
}
