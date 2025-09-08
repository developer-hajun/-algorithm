import java.util.*;
import java.io.*;
/**
 * 메모리:114,044KB, 시간:562ms
 **/

class ceil {
    int y, x, live_time, dead_time, value;

    public ceil(int a, int b, int c, int d, int v) {
        y = a;
        x = b;
        live_time = c;
        dead_time = d;
        value = v;
    }
}

class Solution_5653_줄기세포배양_이하준_2Key최적화 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Set<Integer> visit = new HashSet<>();
            PriorityQueue<ceil> not_live = new PriorityQueue<>((o1, o2) -> o1.live_time - o2.live_time);
            PriorityQueue<ceil> live = new PriorityQueue<>((o1, o2) -> o1.dead_time - o2.dead_time);

            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < m; x++) {
                    int now = Integer.parseInt(st.nextToken());
                    if (now == 0) continue;
                    int key = y * 10000 + x;
                    visit.add(key);
                    not_live.add(new ceil(y, x, now, now + now, now));
                }
            }

            int[] dy = {0, 0, 1, -1};
            int[] dx = {-1, 1, 0, 0};

            for (int time = 1; time < k; time++) {
                // 죽은 셀 제거
                while (!live.isEmpty() && live.peek().dead_time <= time) {
                    live.poll();
                }

                // 번식 후보를 좌표별로 병합하기 위한 Map (key -> ceil)
                Map<Integer, ceil> waitMap = new HashMap<>();

                // 비활성 -> 활성 전환
                while (!not_live.isEmpty() && not_live.peek().live_time <= time) {
                    ceil c = not_live.poll();
                    live.add(c);

                    int y = c.y;
                    int x = c.x;
                    int value = c.value;

                    for (int i = 0; i < 4; i++) {
                        int ny = y + dy[i];
                        int nx = x + dx[i];
                        int key = ny * 10000 + nx;

                        if (visit.contains(key)) continue; // 이미 방문한 좌표는 무시

                        ceil candidate = new ceil(ny, nx, time + value + 1, time + value + 1 + value, value);

                        // 기존에 같은 좌표에 후보가 없거나, value가 더 크면 갱신
                        if (!waitMap.containsKey(key) || waitMap.get(key).value < value) {
                            waitMap.put(key, candidate);
                        }
                    }
                }

                // 병합된 후보들을 큐에 넣고 방문처리
                for (Map.Entry<Integer, ceil> entry : waitMap.entrySet()) {
                    int key = entry.getKey();
                    if (visit.contains(key)) continue;
                    visit.add(key);
                    not_live.add(entry.getValue());
                }
            }

            // k시점 이후 죽은 셀 제거
            while (!live.isEmpty() && live.peek().dead_time <= k) {
                live.poll();
            }

            int answer = not_live.size() + live.size();
            System.out.println("#" + tc + " " + answer);
        }
    }
}
