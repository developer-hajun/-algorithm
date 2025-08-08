import java.util.*;
import java.io.*;

class Main {
    static int max_value = -Integer.MAX_VALUE;
    static int min_value = Integer.MAX_VALUE;
    static int n=0;
    static int[] oper = new int[4];
    static List<Integer> num = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) num.add(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++) oper[i]=Integer.parseInt(st.nextToken());
        dfs(1,num.get(0));
        System.out.println(max_value);
        System.out.println(min_value);
    }

    public static void dfs(int count,int value){
        if(count==n){
            max_value = Math.max(value,max_value);
            min_value = Math.min(value,min_value);
            return;
        }
        if(oper[0]!=0){
            oper[0]-=1;
            dfs(count+1,value+num.get(count));
            oper[0]+=1;
        }
        if(oper[1]!=0){
            oper[1]-=1;
            dfs(count+1,value-num.get(count));
            oper[1]+=1;
        }
        if(oper[2]!=0){
            oper[2]-=1;
            dfs(count+1,value*num.get(count));
            oper[2]+=1;
        }
        if(oper[3]!=0){
            oper[3]-=1;
            dfs(count+1,value/num.get(count));
            oper[3]+=1;
        }
        
    }
}
