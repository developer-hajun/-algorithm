import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gold_14718 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] solider;
    static int n;
    static int k;
    static int answer=Integer.MAX_VALUE;

    static void find(int f, int s, int t, int depth, int start){
        if(f+s+t >= answer) return;

        if(depth == n){
            answer = Math.min(answer, f+s+t);
            return;
        }

        for(int i=start; i<k; i++){
            find(
                    Math.max(f, solider[i][0]),
                    Math.max(s, solider[i][1]),
                    Math.max(t, solider[i][2]),
                    depth+1,
                    i+1
            );
        }
    }


    public static void main(String[] args) throws Exception{

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        solider = new int[n][3];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            solider[i][0] = Integer.parseInt(st.nextToken());
            solider[i][1] = Integer.parseInt(st.nextToken());
            solider[i][2] = Integer.parseInt(st.nextToken());
        }
        find(0,0,0,0,0);
        System.out.println(answer);

    }
}