import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            int mask = 0;
            int fullMask = (1 << 10) - 1; 
            int count = 0;

            while (mask != fullMask) {
                count++;
                int num = N * count;

                while (num > 0) {
                    int digit = num % 10;
                    mask |= (1 << digit);
                    num /= 10;
                }
            }

            System.out.println("#" + test_case + " " + (N * count));
        }
    }
}
