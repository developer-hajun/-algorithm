import java.util.*;

class Main {
    static List<List<Integer>> up;
    static List<List<Integer>> down;
    static boolean[] visit;
    static int count;

    public static void up_count(int now) {
        for (int next : up.get(now)) {
            if (visit[next]) continue;
            visit[next] = true;
            count++;
            up_count(next);
        }
    }

    public static void down_count(int now) {
        for (int next : down.get(now)) {
            if (visit[next]) continue;
            visit[next] = true;
            count++;
            down_count(next);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        up = new ArrayList<>();
        down = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            up.add(new ArrayList<>());
            down.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            up.get(a).add(b);
            down.get(b).add(a);
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            visit = new boolean[n];
            visit[i] = true;
            count = 1;

            up_count(i);
            down_count(i);

            if (count == n) answer++;
        }

        System.out.println(answer);
    }
}
