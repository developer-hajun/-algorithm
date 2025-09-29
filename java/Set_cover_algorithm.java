import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Set_cover_algorithm {
    static List<Integer>[] list;

    public static void main(String[] args) {

        int[][] edges = {{1, 2},
                {1, 8},
                {1, 3},
                {2, 1},
                {2, 8},
                {2, 3},
                {2, 4},
                {3, 1},
                {3, 4},
                {3, 2},
                {4, 2},
                {4, 3},
                {4, 8},
                {4, 7},
                {4, 5},
                {5, 7},
                {5, 4},
                {5, 6},
                {6, 9},
                {6, 10},
                {6, 7},
                {6, 5},
                {7, 4},
                {7, 5},
                {7, 6},
                {8, 1},
                {8, 2},
                {8, 4},
                {9, 6},
                {10, 6}
        };//경로
        int[] setting = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Set<Integer> U = new HashSet<>();
        for (int num : setting) {
            U.add(num); // 각 요소를 Set에 추가
        }
        list = new ArrayList[11];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>(); // 각 인덱스를 ArrayList로 초기화
            list[i].add(i);
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            list[a].add(b);
        }
        List<Set<Integer>> sets = new ArrayList<>(List.of());
        for (int i = 1; i <= 10; i++) {

            sets.add(new HashSet<>(list[i]));
        }
        //Set생성
        List<Set<Integer>> c = new ArrayList<>(List.of());
        Instant beforeTime = Instant.now();
        while (!U.isEmpty()){
            Set<Integer> now = Set.of();
            Set<Integer> pick = Set.of();
            for (Set<Integer> set : sets) {
                Set<Integer> intersection = new HashSet<>(U);
                intersection.retainAll(set);
                if (now.size() < intersection.size()) {
                    now = intersection;
                    pick=set;
                }
            }
            sets.remove(pick);
            c.add(pick);
            U.removeAll(pick);
            System.out.println(pick);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis();
        System.out.println("running time: "+diffTime+"nanosec");

        for (int num : setting) {
            U.add(num); // 각 요소를 Set에 추가
        }



        beforeTime = Instant.now();
        while (!U.isEmpty()){
            Set<Integer> now = Set.of();
            Set<Integer> pick = Set.of();
            for (Set<Integer> set : c) {
                Set<Integer> intersection = new HashSet<>(U);
                intersection.retainAll(set);
                if (now.size() < intersection.size()) {
                    now = intersection;
                    pick=set;
                }
            }
            c.remove(pick);
            U.removeAll(pick);
        }
        afterTime = Instant.now();
        diffTime = Duration.between(beforeTime, afterTime).toMillis();
        System.out.println("Optimal running time: "+diffTime+"nanosec");

    }
}
