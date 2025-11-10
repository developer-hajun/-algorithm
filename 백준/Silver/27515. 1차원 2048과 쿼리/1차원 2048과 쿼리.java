import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int Q = Integer.parseInt(br.readLine());

        long[] counts = new long[64]; 

        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            String op = x<0 ? "-":"+";
            x=Math.abs(x);
            
           
            if (x > 0) {
                int k = Long.numberOfTrailingZeros(x);
                
                if (op.equals("+")) {
                    counts[k]++;
                } else { // "-"
                    counts[k]--;
                }
            }
            long carry = 0;
            int max_k = -1; 

   
            for (int k = 0; k <= 62; k++) {
                long total_bits = counts[k] + carry;
                
                if (total_bits % 2 != 0) {
                    max_k = k; 
                }
                

                carry = total_bits / 2;
            }


            int k = 63;
            while (carry > 0) {
                if (carry % 2 != 0) {
                    max_k = k; 
                }
                carry /= 2;
                k++;
            }

            if (max_k == -1) {
                bw.write("0\n");
            } else {
                long result = 1L << max_k;
                bw.write(result + "\n");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}