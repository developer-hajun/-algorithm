#include <iostream>
#include <vector>
#include <limits>
#include <string>

using namespace std;

const int NUM_NODES = 5;
int graph[NUM_NODES][NUM_NODES] = {
    {0, 2, 7, 3, 10},
    {2, 0, 3, 5, 4},
    {7, 3, 0, 6, 1},
    {3, 5, 6, 0, 9},
    {10, 4, 1, 9, 0}
};

bool visited[NUM_NODES] = {false}; // 방문 여부 확인
int minPathCost = numeric_limits<int>::max(); // 최단 경로 비용
string bestPath = ""; // 최단 경로를 저장할 문자열

void tspBacktracking(int currentNode, int count, int cost, string path) {
    if (cost >= minPathCost) return;
    // 종료 조건: 모든 노드를 방문하고 다시 시작점(0)으로 돌아옴
    if (count == NUM_NODES && graph[currentNode][0] > 0) {
        cost += graph[currentNode][0]; // 시작점으로 돌아가는 비용 추가
        if (cost >= minPathCost) return;
        path += ",A";
        minPathCost = cost;
        bestPath = path;
        return;
    }
    // 백트래킹: 다음 노드 탐색
    for (int nextNode = 0; nextNode < NUM_NODES; nextNode++) {
        if (!visited[nextNode] && graph[currentNode][nextNode] > 0) {
            visited[nextNode] = true; // 방문 표시
            tspBacktracking(nextNode, count + 1, cost + graph[currentNode][nextNode], path + "," + char('A' + nextNode));
            visited[nextNode] = false; // 백트래킹: 방문 해제
        }
    }
}

int main() {
    visited[0] = true; // 시작점 A 방문 처리
    tspBacktracking(0, 1, 0, "A");

    cout << "[" << bestPath << "], 거리 = " << minPathCost << endl;
    return 0;
}
