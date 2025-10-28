import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;

class Main {

 
    public static void hanoi(int count, int l, int r, int o, StringBuilder sb) {
        if (count == 0) return;

        hanoi(count - 1, l, o, r, sb);
        sb.append(l + " " + o + "\n"); 
        hanoi(count - 1, r, l, o, sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        if (n > 20) {
            BigInteger count = new BigInteger("2");
            count = count.pow(n).subtract(BigInteger.ONE);
            System.out.println(count);
        } 
        else {
            System.out.println((int)Math.pow(2, n) - 1);

            StringBuilder sb = new StringBuilder();
            hanoi(n, 1, 2, 3, sb);
            
            System.out.print(sb.toString()); 
        }
    }
}