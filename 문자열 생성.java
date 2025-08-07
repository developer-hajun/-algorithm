import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = br.readLine().charAt(0);
        }

        int left = 0;
        int right = n - 1;

        int lineCount = 0;

        while (left <= right) {
            boolean takeLeft = false;

            // 앞과 뒤를 비교해 사전순으로 더 빠른 쪽을 고름
            for (int i = 0; left + i <= right; i++) {
                if (s[left + i] < s[right - i]) {
                    takeLeft = true;
                    break;
                } else if (s[left + i] > s[right - i]) {
                    takeLeft = false;
                    break;
                }
            }

            if (takeLeft) {
                result.append(s[left++]);
            } else {
                result.append(s[right--]);
            }

            lineCount++;
            if (lineCount % 80 == 0) {
                result.append("\n");
            }
        }

        System.out.println(result.toString());
    }
}
