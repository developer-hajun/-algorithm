import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();  // 점원 수
            int B = sc.nextInt();  // 선반 높이
            int[] heights = new int[N];
            for (int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }

            // BFS 탐색을 위한 큐
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0}); // {합, 인덱스}
            int minExceed = Integer.MAX_VALUE;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int sum = cur[0];
                int idx = cur[1];

                if (sum >= B) {
                    minExceed = Math.min(minExceed, sum);
                }

                if (idx < N) {
                    // 현재 인덱스 점원을 포함하지 않는 경우
                    queue.offer(new int[]{sum, idx + 1});
                    // 포함하는 경우
                    queue.offer(new int[]{sum + heights[idx], idx + 1});
                }
            }

            System.out.println("#" + t + " " + (minExceed - B));
        }
    }
}