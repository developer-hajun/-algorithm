#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <queue>
#include <unordered_set>
using namespace std;


struct Point {
    char label;
    double x, y;
};


double distance(const Point& p1, const Point& p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}


struct Edge {
    int u, v;
    double weight;

    bool operator<(const Edge& other) const {
        return weight < other.weight;
    }
};


struct UnionFind {
    vector<int> parent, rank;

    UnionFind(int n) : parent(n), rank(n, 0) {
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    bool unite(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;
        if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
        else if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
};


void dfs(int node, const vector<vector<pair<int, double>>>& tree, vector<bool>& visited, vector<int>& tour) {
    visited[node] = true;
    tour.push_back(node);

    for (const auto& neighbor : tree[node]) {
        int nextNode = neighbor.first;
        if (!visited[nextNode]) {
            dfs(nextNode, tree, visited, tour);
        }
    }
    tour.push_back(node);
}


vector<int> removeDuplicates(const vector<int>& tour) {
    vector<int> optimizedTour;
    unordered_set<int> visitedSet;

    for (int node : tour) {
        if (visitedSet.find(node) == visitedSet.end()) {
            optimizedTour.push_back(node);
            visitedSet.insert(node);
        }
    }
    return optimizedTour;
}

int main() {

    vector<Point> points = {
        {'A', 0, 3}, {'B', 7, 5}, {'C', 6, 0}, {'D', 4, 3},
        {'E', 1, 0}, {'F', 5, 3}, {'G', 2, 2}, {'H', 4, 1}
    };

    int n = points.size();
    vector<Edge> edges;


    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            edges.push_back({ i, j, distance(points[i], points[j]) });
        }
    }

    sort(edges.begin(), edges.end());


    UnionFind uf(n);
    vector<vector<pair<int, double>>> mstTree(n);

    for (const auto& edge : edges) {
        if (uf.unite(edge.u, edge.v)) {
            mstTree[edge.u].push_back({ edge.v, edge.weight });
            mstTree[edge.v].push_back({ edge.u, edge.weight });
        }
    }

    for (int i = 0; i < n; i++) {
        sort(mstTree[i].begin(), mstTree[i].end(), [&points](const pair<int, double>& a, const pair<int, double>& b) {

            return points[a.first].label > points[b.first].label;
            });
    }

    vector<bool> visited(n, false);
    vector<int> tour;
    dfs(0, mstTree, visited, tour);

    vector<int> optimizedTour = removeDuplicates(tour);


    cout << "TSP Tour: ";
    double totalDistance = 0;
    for (int i = 0; i < optimizedTour.size(); i++) {
        cout << points[optimizedTour[i]].label;
        if (i < optimizedTour.size() - 1) cout << " -> ";
        if (i > 0) {
            totalDistance += distance(points[optimizedTour[i - 1]], points[optimizedTour[i]]);
        }
    }

    totalDistance += distance(points[optimizedTour.back()], points[optimizedTour[0]]);
    cout << " -> " << points[optimizedTour[0]].label << endl;

    cout << "Total Distance: " << totalDistance << endl;

    return 0;
}
