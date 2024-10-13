#include <iostream>
#include <fstream>
#include <vector>
#include <time.h>
using namespace std;


vector<int> parent;
struct Edge {
    int cost;
    int src;
    int dest;
};
int find_parent(vector<int> parent, int x) {
    if (parent[x] != x) parent[x] = find_parent(parent, parent[x]);
    return parent[x];
}

void union_parent(int a, int b, int x, int y) {
    if (a < b) parent[a] = y;
    else parent[b] = x;
}

int main() {
    vector<Edge> edges = {
        {1,1,2} ,{1,2,5},{2,1,5},{2,0,3},{3,3,4},{4,0,4},{4,1,3},{7,3,5},{8,0,1},{9,4,5}
    };
    
    for (int i = 0; i < 6; i++) {
        parent.push_back(i);
    }
    vector<Edge> answer;

    for (const auto& edge : edges) {
        int cost = edge.cost;
        int x = edge.src;
        int y = edge.dest;
        
        int a = find_parent(parent, x);
        int b = find_parent(parent, y);

        if (a!=b) {
            union_parent(a, b, x, y);
            answer.push_back(edge);
        }
    }
    for (const auto& edge : answer) {
        cout << "("<<edge.src<<", "<<edge.dest<<", "<<edge.cost<<')'<< endl;
    }
}