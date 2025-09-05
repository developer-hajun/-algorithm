
import java.util.*;

class UserSolution {
    static final int MAX_N = 1000;

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State {
        int city;
        int visitedMask;
        int weight;

        State(int city, int visitedMask, int weight) {
            this.city = city;
            this.visitedMask = visitedMask;
            this.weight = weight;
        }
    }

    static List<Edge>[] graph = new ArrayList[MAX_N];
    static int N;

    public void init(int n, int k, int[] sCity, int[] eCity, int[] mLimit) {
        N = n;
        for (int i = 0; i < N; ++i) graph[i] = new ArrayList<>();

        for (int i = 0; i < k; ++i) {
            graph[sCity[i]].add(new Edge(eCity[i], mLimit[i]));
            graph[eCity[i]].add(new Edge(sCity[i], mLimit[i]));
        }
        
    }

    public void add(int sCity, int eCity, int mLimit) {
        graph[sCity].add(new Edge(eCity, mLimit));
        graph[eCity].add(new Edge(sCity, mLimit));
    }

    public int calculate(int sCity, int eCity, int M, int[] mStopover) {
        Map<Integer, Integer> stopIndex = new HashMap<>();
        for (int i = 0; i < M; i++) {
            stopIndex.put(mStopover[i], i);
        }

        int[][] maxWeight = new int[N][1 << M];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(maxWeight[i], -1);
        }

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
        pq.offer(new State(sCity, 0, 30000));
        maxWeight[sCity][0] = 30000;

        int allVisited = (1 << M) - 1;

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.city == eCity && curr.visitedMask == allVisited) {
                return curr.weight;
            }

            for (Edge edge : graph[curr.city]) {
                int nextCity = edge.to;
                int nextMask = curr.visitedMask;

                if (stopIndex.containsKey(nextCity)) {
                    int idx = stopIndex.get(nextCity);
                    nextMask |= (1 << idx);
                }

                int nextWeight = Math.min(curr.weight, edge.weight);
                if (nextWeight > maxWeight[nextCity][nextMask]) {
                    maxWeight[nextCity][nextMask] = nextWeight;
                    pq.offer(new State(nextCity, nextMask, nextWeight));
                }
            }
        }

        return -1; 
    }
}
