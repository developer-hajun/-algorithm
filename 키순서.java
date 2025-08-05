import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine()); // 테스트 케이스 수

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer = 0;
            int n = Integer.parseInt(sc.nextLine()); // 학생 수
            int m = Integer.parseInt(sc.nextLine()); // 비교 수

            // dic[i][0] : i가 이긴 학생들 리스트
            // dic[i][1] : i가 진 학생들 리스트
            ArrayList<Integer>[][] dic = new ArrayList[n + 1][2];
            for (int i = 1; i <= n; i++) {
                dic[i][0] = new ArrayList<>();
                dic[i][1] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                String[] input = sc.nextLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                dic[a][0].add(b); // a가 이김
                dic[b][1].add(a); // b는 짐
            }

            for (int i = 1; i <= n; i++) {
                boolean[] visited = new boolean[n + 1];
                visited[i] = true;

                // 승리한 학생 찾기
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                int winCount = 0;

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int next : dic[current][0]) {
                        if (!visited[next]) {
                            visited[next] = true;
                            winCount++;
                            queue.offer(next);
                        }
                    }
                }

                // 패배한 학생 찾기
                queue.offer(i);
                int lossCount = 0;

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int next : dic[current][1]) {
                        if (!visited[next]) {
                            visited[next] = true;
                            lossCount++;
                            queue.offer(next);
                        }
                    }
                }

                if (winCount + lossCount == n - 1) {
                    answer++;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}
