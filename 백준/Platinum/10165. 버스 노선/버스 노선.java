import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        long[][] buslist = new long[m * 2][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            
            if (a > b) {
                b = b + n;
            }
            buslist[i * 2] = new long[]{a, b, i};
            buslist[i * 2 + 1] = new long[]{a + n, b + n, i};
        }

        Arrays.sort(buslist, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Long.compare(o2[1], o1[1]); 
            }
            return Long.compare(o1[0], o2[0]);
        });
        
        boolean[] cancle = new boolean[m];
        
        long max_end = -1L; 

        for (int i = 0; i < m * 2; i++) {
            if (buslist[i][1] <= max_end) {
                cancle[(int)buslist[i][2]] = true;
            } else {
                max_end = buslist[i][1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (cancle[i]) continue;
            sb.append(i + 1).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}