import java.util.*;


public class BFS {
    static final int NUM_NODES = 5; // 총 노드 수
    static final int INF = Integer.MAX_VALUE;
    public static int calculateBound( boolean[] visited,List<Integer> path_nums) {
        //아직 방문안한 노드들
        int answer = 0;
        for(int i=0;i<5;i++){
            if (visited[i]){
                continue;
            }
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int num : graph[i]) {
                if (num == 0) continue;
                if (num < min1) {
                    min2 = min1; // 이전 최소값을 두 번째 최소값으로 업데이트
                    min1 = num;  // 새로운 최소값 갱신
                } else if (num < min2 && num != min1) {
                    min2 = num;  // 두 번째 최소값 갱신 (첫 번째 최소값과 같지 않은 경우)
                }
            }
            answer+=min1+min2;
        }

        for(int i=1;i<path_nums.size()-1;i++){

            answer += graph[path_nums.get(i-1)][path_nums.get(i)]+graph[path_nums.get(i)][path_nums.get(i+1)];
        }


        int zero = graph[0][path_nums.get(1)];
        int min1 = Integer.MAX_VALUE;
        for (int num : graph[0]) {
            if (num == 0 || num == zero) continue;
            if (num < min1) {
                min1 = num;  // 새로운 최소값 갱신
            }
        }
        int min2 = Integer.MAX_VALUE;
        int last = graph[path_nums.get(path_nums.size()-1)][path_nums.get(path_nums.size()-2)];
        for (int num : graph[path_nums.get(path_nums.size()-1)]) {
            if (num == 0 || num == last) continue;
            if (num < min2) {
                min2 = num;  // 새로운 최소값 갱신
            }
        }
        //System.out.println(min1 +" "+ zero +" "+ min2+" "+last);
        answer+=min1+min2+zero+last;;
        return (int) Math.ceil((double) answer /2);

    }

    // 그래프의 가중치 행렬
    static int[][] graph = {
            {0, 2, 7, 3, 10},  // A -> B, C, D, E
            {2, 0, 3, 5, 4},   // B -> C, D, E
            {7, 3, 0, 6, 1},   // C -> D, E
            {3, 5, 6, 0, 9},   // D -> E
            {10, 4, 1, 9, 0}   // E -> others
    };

    static int bestCost = INF; // 최단 경로 비용
    static String bestPath = ""; // 최단 경로 문자열

    // BFS 노드 클래스
    static class Node implements Comparable<Node>{
        int current;        // 현재 노드
        int level;          // 방문한 노드 수
        int cost;           // 현재까지의 비용
        String path;        // 경로
        boolean[] visited;  // 방문 표시 배열
        List<Integer> path_num;

        Node(int current, int level, int cost, String path, boolean[] visited,List<Integer> path_num) {
            this.path_num = path_num;
            this.current = current;
            this.level = level;
            this.cost = cost;
            this.path = path;
            this.visited = Arrays.copyOf(visited, visited.length); // 방문 배열 복사
        }
        @Override
        public int compareTo(Node node) {
            if(this.cost<node.cost) return -1;
            else if (this.cost>node.cost) return 1;
            else{
                if(this.level<node.level) return 1;
                else if (this.level>node.level) return -1;
                else return 0;
            }
        }
    }

    public static void bfs(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] initialVisited = new boolean[NUM_NODES];
        initialVisited[start] = true;
        List<Integer> path_num = new ArrayList<>();
        path_num.add(0);
        // 시작 노드 추가
        queue.add(new Node(start, 1, 0, getNodeName(start), initialVisited,path_num));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.cost>bestCost) continue;
            // 모든 노드를 방문한 후 시작점으로 돌아옴


            // 다음 노드를 탐색
            for (int next = 0; next < NUM_NODES; next++) {
                if (!node.visited[next] && graph[node.current][next] > 0) {
                    boolean[] newVisited = Arrays.copyOf(node.visited, NUM_NODES);
                    newVisited[next] = true; // 방문 표시
                    int bound = 0;
                    int newCost = node.cost + graph[node.current][next];
                    List<Integer> new_path_num = new ArrayList<>(node.path_num);
                    new_path_num.add(next);
                    bound = calculateBound(newVisited,new_path_num);
                    if(bound>=bestCost) continue;
                    if(node.level+1 == NUM_NODES){
                        if (graph[next][start] > 0) {
                            int totalCost = newCost + graph[next][start];
                            if (totalCost < bestCost) {
                                bestCost = totalCost;
                                bestPath = node.path + ", "+getNodeName(next)+", " + getNodeName(start);
                            }
                        }
                    }
                    else{
                        queue.add(new Node(next, node.level + 1, newCost, node.path + ", " + getNodeName(next), newVisited,new_path_num));
                    }

                }
            }
        }
    }

    // 노드 번호를 문자로 변환 (A, B, C, ...)
    public static String getNodeName(int node) {
        return String.valueOf((char) ('A' + node));
    }

    public static void main(String[] args) {
        bfs(0); // 시작점 A

        System.out.print("[" + bestPath+']');
        System.out.println(", 거리: " + bestCost);
    }
}
