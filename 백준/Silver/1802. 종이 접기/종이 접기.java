import java.util.*;
import java.io.*;

public class Main {

    static String now;

    public static boolean pick(int start, int end) {
        if (start >= end) return true;

        int len = end - start + 1;
        int half = len / 2;

        for (int i = 0; i < half; i++) {
            if (now.charAt(start + i) == now.charAt(end - i)) {
                return false;
            }
        }

        int mid = (start + end) / 2;
        return pick(start, mid - 1) && pick(mid + 1, end);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            now = br.readLine();
            if (pick(0, now.length() - 1)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
