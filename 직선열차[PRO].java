import java.util.*;

class UserSolution {

    private Map<Integer, Integer> trainIdToIndex;
    private int trainCount;


    private List<Integer>[] portToTrains;

    private List<Integer>[] trainToPorts;

    private Set<Integer> removedTrainIndexes;

    public void init(int N, int K, int mId[], int sId[], int eId[], int mInterval[]) {
        trainIdToIndex = new HashMap<>();
        trainCount = 0;
        removedTrainIndexes = new HashSet<>();

        portToTrains = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            portToTrains[i] = new ArrayList<>();
        }
        
        trainToPorts = new ArrayList[205]; 

        for (int i = 0; i < K; i++) {
            add(mId[i], sId[i], eId[i], mInterval[i]);
        }
    }

    public void add(int mId, int sId, int eId, int mInterval) {
        int idx = trainCount++;
        trainIdToIndex.put(mId, idx);

        trainToPorts[idx] = new ArrayList<>();
        for (int port = sId; port <= eId; port += mInterval) {
            portToTrains[port].add(idx);
            trainToPorts[idx].add(port);
        }
    }

    public void remove(int mId) {
        if (trainIdToIndex.containsKey(mId)) {
            removedTrainIndexes.add(trainIdToIndex.get(mId));
        }
    }

    public int calculate(int sId, int eId) {

        for (int startTrainIdx : portToTrains[sId]) {
            if (removedTrainIndexes.contains(startTrainIdx)) continue;
            for (int port : trainToPorts[startTrainIdx]) {
                if (port == eId) {
                    return 0;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>(); 
        boolean[] visitedTrain = new boolean[trainCount];


        for (int startTrainIdx : portToTrains[sId]) {
            if (removedTrainIndexes.contains(startTrainIdx)) continue;
            
            if (!visitedTrain[startTrainIdx]) {
                queue.add(new int[]{startTrainIdx, 0});
                visitedTrain[startTrainIdx] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentTrainIdx = current[0];
            int transfers = current[1];


            for (int stopPort : trainToPorts[currentTrainIdx]) {

                for (int nextTrainIdx : portToTrains[stopPort]) {
                    if (removedTrainIndexes.contains(nextTrainIdx)) continue;
                    
                    if (!visitedTrain[nextTrainIdx]) {

                        for (int port : trainToPorts[nextTrainIdx]) {
                            if (port == eId) {
                                return transfers + 1;
                            }
                        }
                        visitedTrain[nextTrainIdx] = true;
                        queue.add(new int[]{nextTrainIdx, transfers + 1});
                    }
                }
            }
        }

        return -1; 
    }
}
