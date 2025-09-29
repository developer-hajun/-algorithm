import java.util.*;
import java.lang.*;
import java.io.*;

class boom {
    int number;
    int value;
    public boom(int h, int t) {
        number = h;
        value = t;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque<boom> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.add(new boom(i + 1, sc.nextInt()));
        }

        List<Integer> answer = new ArrayList<>();
        while (true) {
            boom now = queue.pollFirst();
            int val = now.value;
            answer.add(now.number);
            if (queue.isEmpty()) break;

            if (val > 0) val--;
            if (val > 0) {
                while (val-- > 0) {
                    queue.addLast(queue.pollFirst());
                }
            } else {
                while (val++ < 0) {
                    queue.addFirst(queue.pollLast());
                }
            }
        }

        for (int a : answer) {
            System.out.print(a + " ");
        }
    }
}
