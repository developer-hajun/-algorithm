import java.util.*;
import java.io.*;

class Main {
    static int[][] matrix;

    static int white=0;
    static int blue=0;

    public static int solve(int start_x,int end_x,int start_y,int end_y){
        if(start_x==end_x&&start_y==end_y) return matrix[start_y][start_x];
        int mid_x = (start_x+end_x)/2;
        int mid_y = (start_y+end_y)/2;
        int[] value = new int[4];
        value[0]=solve(start_x,mid_x,start_y,mid_y);
        value[1]=solve(mid_x+1,end_x,start_y,mid_y);
        value[2]=solve(start_x,mid_x,mid_y+1,end_y);
        value[3]=solve(mid_x+1,end_x,mid_y+1,end_y);
        if (value[0] == value[1] && value[1] == value[2] && value[2] == value[3]) return value[0];
        else{
            for(int i:value){
                if(i==0) white++;
                else if(i==1) blue++;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = solve(0,n-1,0,n-1);
        if(answer==0) white++;
        else if(answer==1) blue++;
        System.out.println(white);
        System.out.println(blue);  
    
    }
}
