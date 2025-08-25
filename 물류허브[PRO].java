import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

class UserSolution {
    private static final int MAX_CITIES = 600;
    private static final int INF = 100 * MAX_CITIES + 1;

    private int[][] dist;
    private Map<Integer, Integer> cityToIndex;
    private int cityCount;

    public int init(int N, int sCity[], int eCity[], int mCost[]) {
        cityToIndex = new HashMap<>();
        cityCount = 0;

        for (int i = 0; i < N; i++) {
            if (!cityToIndex.containsKey(sCity[i])) {
                cityToIndex.put(sCity[i], cityCount++);
            }
            if (!cityToIndex.containsKey(eCity[i])) {
                cityToIndex.put(eCity[i], cityCount++);
            }
        }
        
        dist = new int[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int u = cityToIndex.get(sCity[i]);
            int v = cityToIndex.get(eCity[i]);
            dist[u][v] = mCost[i];
        }

        for (int k = 0; k < cityCount; k++) {
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return cityCount;
    }


    public void add(int sCity, int eCity, int mCost) {
        int u = cityToIndex.get(sCity);
        int v = cityToIndex.get(eCity);

        if (dist[u][v] <= mCost) {
            return;
        }
        dist[u][v] = mCost;

        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                if (dist[i][j] > dist[i][u] + dist[u][v] + dist[v][j]) {
                    dist[i][j] = dist[i][u] + dist[u][v] + dist[v][j];
                }
            }
        }
    }

    public int cost(int mHub) {
        int hubIndex = cityToIndex.get(mHub);
        int totalCost = 0; 
        for (int i = 0; i < cityCount; i++) {
            if (i == hubIndex) continue;
            totalCost += dist[i][hubIndex] + dist[hubIndex][i];
        }
        return totalCost;
    }
}
