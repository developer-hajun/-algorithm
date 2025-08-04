import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static List<StringBuilder> sb = new ArrayList<>();
    static int[][] tree;
    public static void dfs(int start){
        char value = (char)(start+64);
        sb.get(0).append(value);
        if(tree[start][0]!=0) dfs(tree[start][0]);
        sb.get(1).append(value);
        if(tree[start][1]!=0) dfs(tree[start][1]);
        sb.get(2).append(value);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i=0;i<3;i++) sb.add(new StringBuilder());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        tree = new int [27][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int val = st.nextToken().charAt(0)-64;
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if(left!='.') tree[val][0]=left-64;
            if(right!='.') tree[val][1]=right-64;
        }
        dfs(1);
        for(StringBuilder s : sb){
            System.out.println(s.toString());
        }
    }
}