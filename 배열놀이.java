import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for(int test_case=1;test_case<=t;test_case++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];
            for(int y=0;y<n;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<n;x++){
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] plus = new int[n][n+1];
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken())-1;
                int c1 = Integer.parseInt(st.nextToken())-1;
                int r2 = Integer.parseInt(st.nextToken())-1;
                int c2 = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                for(int y=r1;y<=r2;y++){
                    plus[y][c1]+=v;
                    plus[y][c2]+=-v;
                }
            }
            for(int y=0;y<n;y++){
                for(int x=1;x<=n;x++){
                    plus[y][x]+=plus[y][x-1];
                }
            }
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    arr[y][x]+=plus[y][x];
                }
            }

            //가로
            for(int[] ar : arr){
                System.out.print(Arrays.stream(ar).sum()+" ");
            }
            System.out.println();
            //세로
            for (int x = 0; x < arr[0].length; x++) {
                int colSum = 0;
                for (int y = 0; y < arr.length; y++) {
                    colSum += arr[y][x];
                }
                System.out.print(colSum + " ");
            }
            System.out.println();

        }
    }
}
