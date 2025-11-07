import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class Main{


    static class Event implements Comparable<Event> {
        int time; 
        int type; 
        int id;   

        public Event(int time, int type, int id) {
            this.time = time;
            this.type = type;
            this.id = id;
        }

        @Override
        public int compareTo(Event other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Event> events = new ArrayList<>();

        long[] uniqueCover = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            events.add(new Event(start, 1, i));
            events.add(new Event(end, -1, i));
        }

        Collections.sort(events);

        long totalCover = 0;      
        int activeCount = 0;    
        Set<Integer> activeSet = new HashSet<>(); 
        int lastEventTime = events.get(0).time; 

        for (Event currentEvent : events) {
            
  
            int duration = currentEvent.time - lastEventTime;

            if (activeCount > 0) {
                totalCover += duration;
            }
            if (activeCount == 1) {
                int uniqueId = activeSet.iterator().next();
                uniqueCover[uniqueId] += duration;
            }

            if (currentEvent.type == 1) {
                activeCount++;
                activeSet.add(currentEvent.id);
            } else {
                activeCount--;
                activeSet.remove(currentEvent.id);
            }

            lastEventTime = currentEvent.time;
        }

        long minUniqueCover = Long.MAX_VALUE;
        for (long time : uniqueCover) {
            minUniqueCover = Math.min(minUniqueCover, time);
        }

        System.out.println(totalCover - minUniqueCover);
    }
}