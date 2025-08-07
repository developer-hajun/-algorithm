import java.io.*;
import java.math.BigInteger;

public class Main {
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sign;
        int absN = Math.abs(n);

        int[] fib = new int[absN + 2]; 
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= absN; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % MOD;
        }

        int result = fib[absN];

        if (n == 0) {
            sign = 0;
        } else if (n > 0) {
            sign = 1;
        } else {
            sign = (absN % 2 == 0) ? -1 : 1;
        }

        System.out.println(sign);
        System.out.println(result);
    }
}
