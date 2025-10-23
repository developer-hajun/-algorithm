import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    /**
     * 건물의 시작점과 끝점을 나타내는 이벤트 클래스
     * 1. x좌표 오름차순
     * 2. (x가 같을 시) 높이 오름차순
     * - 시작점 (음수 높이)이 끝점 (양수 높이)보다 먼저 처리됨
     * - 시작점이 여러 개면, 더 높은 건물 (음수 값이 작은)이 먼저 처리됨
     */
    static class Event implements Comparable<Event> {
        int x;
        int h; // 시작점은 -h, 끝점은 +h

        public Event(int x, int h) {
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Event o) {
            // 1. x좌표 오름차순
            if (this.x != o.x) {
                return this.x - o.x;
            }
            // 2. x가 같으면, 높이 오름차순
            return this.h - o.h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            events.add(new Event(L, -H));
            events.add(new Event(R, H));
        }

        Collections.sort(events);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        map.put(0, 1);

        int currentMaxHeight = 0; 
        StringBuilder sb = new StringBuilder();

        for (Event event : events) {
            if (event.h < 0) {
                int height = -event.h;
                map.put(height, map.getOrDefault(height, 0) + 1);
            } else {                int height = event.h;
                map.put(height, map.get(height) - 1);
                if (map.get(height) == 0) {
                    map.remove(height);
                }
            }
            int newMaxHeight = map.lastKey(); 
            if (currentMaxHeight != newMaxHeight) {
                sb.append(event.x).append(" ").append(newMaxHeight).append(" ");
                currentMaxHeight = newMaxHeight;
            }
        }

        System.out.println(sb.toString());
    }
}