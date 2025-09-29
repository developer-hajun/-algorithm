import java.util.*;

public class vector {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();


        graph.put("A", Arrays.asList("B", "E"));
        graph.put("B", Arrays.asList("A", "F"));
        graph.put("C", Arrays.asList("D", "G"));
        graph.put("D", Arrays.asList("C", "H"));
        graph.put("E", Arrays.asList("A", "F", "I"));
        graph.put("F", Arrays.asList("B", "E", "J"));
        graph.put("G", Arrays.asList("C", "H", "K"));
        graph.put("H", Arrays.asList("D", "G", "L"));
        graph.put("I", Arrays.asList("E", "J", "M"));
        graph.put("J", Arrays.asList("F", "I", "N"));
        graph.put("K", Arrays.asList("G", "L", "O"));
        graph.put("L", Arrays.asList("H", "K"));
        graph.put("M", Arrays.asList("I", "N"));
        graph.put("N", Arrays.asList("J", "M"));
        graph.put("O", Arrays.asList("K"));
        Set<String> visited = new HashSet<>();
        List<String> matching = new ArrayList<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                for (String neighbor : graph.get(node)) {
                    if (!visited.contains(neighbor)) {

                        matching.add(node + "-" + neighbor);
                        visited.add(node);
                        visited.add(neighbor);
                        break;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println("Maximal Matching: " + matching);
    }
}
