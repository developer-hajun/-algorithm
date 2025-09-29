
import java.util.*;

class UserSolution {
    // 도로의 목적지, 시간, 전력 소모량을 저장하는 클래스
	class Edge {
		int end;
		int time;
		int power;

		public Edge(int end, int time, int power) {
			this.end = end;
			this.time = time;
			this.power = power;
		}
	}

    // Graph: 각 도시에서 출발하는 도로들의 정보를 담는 인접 리스트
	Map<Integer, Edge>[] graph;
    // 도시별 단위 시간당 충전량
	int[] charge;
    // 도로 ID(Key)와 해당 도로의 출발 도시 ID(Value)를 매핑
	Map<Integer, Integer> edgeStartCity;
	int N_size;

	public void init(int N, int mCharge[], int K, int mId[], int sCity[], int eCity[], int mTime[], int mPower[]) {
		N_size = N;
		graph = new HashMap[N];
		charge = mCharge;
		edgeStartCity = new HashMap<>();

		for (int i = 0; i < N; i++) {
			graph[i] = new HashMap<>();
		}

		for (int i = 0; i < K; i++) {
			add(mId[i], sCity[i], eCity[i], mTime[i], mPower[i]);
		}
	}

	public void add(int mId, int sCity, int eCity, int mTime, int mPower) {
        // 도로 ID에 출발 도시를 매핑하여 저장 (remove 함수에서 사용)
		edgeStartCity.put(mId, sCity);
        // 그래프에는 sCity -> eCity 단방향 도로 정보만 추가
		graph[sCity].put(mId, new Edge(eCity, mTime, mPower));
	}

	public void remove(int mId) {
        // 도로 ID를 이용해 출발 도시를 찾음
		int sCity = edgeStartCity.get(mId);
        
        // 해당 출발 도시의 인접 리스트에서 도로를 제거
		graph[sCity].remove(mId);
        // 맵에서도 도로 정보를 제거
		edgeStartCity.remove(mId);
	}

	public int cost(int B, int sCity, int eCity, int M, int mCity[], int mTime[]) {
		// 1. 각 도시별 전염병 도달 시간 계산
		int[] infectedTime = new int[N_size];
		Arrays.fill(infectedTime, Integer.MAX_VALUE);
		PriorityQueue<int[]> infectionQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		for (int i = 0; i < M; i++) {
			int city = mCity[i];
			int time = mTime[i];
			infectionQueue.add(new int[]{city, time});
			infectedTime[city] = time;
		}

		while (!infectionQueue.isEmpty()) {
			int[] now = infectionQueue.poll();
			int currentCity = now[0];
			int currentTime = now[1];

			if (infectedTime[currentCity] < currentTime) continue;
            
            // 단방향 그래프를 따라 전염병 전파
			for (Edge next : graph[currentCity].values()) {
				int nextCity = next.end;
				int nextTime = next.time + currentTime;
				if (infectedTime[nextCity] > nextTime) {
					infectedTime[nextCity] = nextTime;
					infectionQueue.add(new int[]{nextCity, nextTime});
				}
			}
		}

		// 2. 다익스트라 알고리즘으로 최소 이동 시간 계산
		int[][] minTime = new int[N_size][B + 1];
		for (int i = 0; i < N_size; i++) {
			Arrays.fill(minTime[i], Integer.MAX_VALUE);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		if (infectedTime[sCity] <= 0) {
			return -1;
		}

		minTime[sCity][B] = 0;
		pq.add(new int[]{0, sCity, B});

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int currentTime = current[0];
			int currentCity = current[1];
			int currentBattery = current[2];

			if (currentCity == eCity) {
				return currentTime;
			}

			if (currentTime > minTime[currentCity][currentBattery]) {
				continue;
			}
			
			if (currentTime >= infectedTime[currentCity]) {
			    continue;
			}

			// 행동 1: 인접 도시로 이동 (단방향)
			for (Edge edge : graph[currentCity].values()) {
				if (currentBattery >= edge.power) {
					int nextCity = edge.end;
					int newTime = currentTime + edge.time;
					int newBattery = currentBattery - edge.power;

					if (newTime < infectedTime[nextCity] && newTime < minTime[nextCity][newBattery]) {
						minTime[nextCity][newBattery] = newTime;
						pq.add(new int[]{newTime, nextCity, newBattery});
					}
				}
			}

			// 행동 2: 현재 도시에서 1시간 충전
			if (currentBattery < B) {
                int newTime = currentTime + 1;
                int newBattery = Math.min(B, currentBattery + charge[currentCity]);

                if (newTime < infectedTime[currentCity] && newTime < minTime[currentCity][newBattery]) {
                    minTime[currentCity][newBattery] = newTime;
                    pq.add(new int[]{newTime, currentCity, newBattery});
                }
            }
		}

		return -1;
	}
}
