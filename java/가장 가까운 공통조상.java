import java.util.*;

class Main {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= t; test_case++) {
            int n = Integer.parseInt(sc.nextLine());
            parent = new int[n + 1]; // 부모 정보 저장

            for (int i = 1; i <= n - 1; i++) {
                String[] input = sc.nextLine().split(" ");
                int p = Integer.parseInt(input[0]);
                int c = Integer.parseInt(input[1]);
                parent[c] = p;
            }

            String[] query = sc.nextLine().split(" ");
            int a = Integer.parseInt(query[0]);
            int b = Integer.parseInt(query[1]);

            Set<Integer> ancestors = new HashSet<>();
            while (a != 0) {
                ancestors.add(a);
                a = parent[a];
            }

            while (b != 0) {
                if (ancestors.contains(b)) {
                    System.out.println(b);
                    break;
                }
                b = parent[b];
            }
        }
    }
}
