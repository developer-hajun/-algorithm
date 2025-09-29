import java.io.*;
import java.util.*;
 


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 행/열 크기
        int M = Integer.parseInt(st.nextToken()); // 추가 정보 (필요에 따라 사용)


        int[][] arr = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()); 
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                if(i==k) continue;
                for(int j=0;j<N;j++){
                    if(j==k || j==i) continue;
                    arr[i][j]=Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(arr[from-1][to-1]<=weight) sb.append("Enjoy other party\n");
            else sb.append("Stay here\n");
        }
        System.out.println(sb.toString());
    }
}
