import java.util.*;
import java.io.*;

public class Main {
    static int[] green = new int[6];
    static int[] blue = new int[6];
    static int answer = 0;


    public static void add(int[] board, int input) {
        int idx = 0;

        for (int i = 1; i < 6; i++) {
            if ((board[i] & input) != 0) {
                idx = i - 1;
                board[idx] |= input;
                return;
            }
        }
 
        idx = 5;
        board[idx] |= input;
    }


    public static void down(int[] board) {
        int[] tmp = new int[6];
        int tmpIdx = 5; 

  
        for (int i = 5; i >= 0; i--) {
            if (board[i] == 15) { 
                answer++;
            } else if (board[i] != 0) {
                tmp[tmpIdx--] = board[i];
            }
        }

        for (int i = 0; i < 6; i++) {
            board[i] = tmp[i];
        }
    }


    public static void checkSpecialZone(int[] board) {
        int count = 0;

        if (board[0] != 0) count++;
        if (board[1] != 0) count++;

        if (count > 0) {
            for (int i = 5; i >= count; i--) {
                board[i] = board[i - count];
            }
            for (int i = 0; i < count; i++) {
                board[i] = 0;
            }
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (t == 1) {
                add(green, 1 << x);
                add(blue, 1 << y);
            } else if (t == 2) {
                add(green, (1 << x) | (1 << (x + 1)));
                add(blue, 1 << y);
                add(blue, 1 << y);
            } else { // t == 3
                add(green, 1 << x);
                add(green, 1 << x);
                add(blue, (1 << y) | (1 << (y + 1)));
            }
            
            down(green);
            checkSpecialZone(green);

            down(blue);
            checkSpecialZone(blue);
        }

        int tileCount = 0;
        for (int val : green) {
            tileCount += Integer.bitCount(val);
        }
        for (int val : blue) {
            tileCount += Integer.bitCount(val);
        }

        System.out.println(answer);
        System.out.println(tileCount);
    }
}