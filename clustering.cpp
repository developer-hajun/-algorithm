#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <cmath>
#include <unordered_set>

using namespace std;

struct Point {
    double x, y;

    // �ߺ� Ȯ���� ���� �ؽ� �Լ� �����ε�
    bool operator==(const Point& other) const {
        return x == other.x && y == other.y;
    }
};

// �ؽ� �Լ�
namespace std {
    template <>
    struct hash<Point> {
        size_t operator()(const Point& p) const {
            return hash<double>()(p.x) ^ hash<double>()(p.y);
        }
    };
}

vector<Point> readFile(const string& filename) {
    ifstream file(filename);
    double x, y;
    vector<Point> points;
    unordered_set<Point> uniquePoints;

    while (file >> x >> y) {
        Point p = { x, y };
        if (uniquePoints.insert(p).second) {
            points.push_back(p);
        }
    }

    return points;
}
double distance(const Point& p1, const Point& p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}
// ���� �Լ�
int main() {
    string filename = "clustering_input.txt";
    vector<Point> points = readFile(filename);
    int k = 8;
    vector<bool> visit(points.size(), false);
    vector<Point> center;
    visit[0] = true;
    center.push_back(points[0]);

    for (int count = 1; count < k; count++) {
        vector<pair<int, double>> D;
        for (int now = 0; now < 100; now++) {
            if (visit[now]) continue;
            double dist = 210000000;
            for (int p = 0; p < center.size(); p++) {
                Point nc = center[p];
                Point xp = points[now];
                dist = min(dist, distance(nc, xp));
            }
            D.push_back(make_pair(now, dist));
        }
        sort(D.begin(), D.end(), [](const pair<int, double>& a, const pair<int, double>& b) {
            return a.second > b.second; // dist�� ū ������ ����
            });
        visit[D[0].first] = true;
        center.push_back(points[D[0].first]);
    }

    vector<vector<Point>> cluster(8);
    for (int now = 0; now < 100; now++) {
        if (visit[now]) continue;
        int wh = -1;
        double dist = 210000000;
        for (int p = 0; p < center.size(); p++) {
            Point nc = center[p];
            Point xp = points[now];
            double value = distance(nc, xp);
            if (value < dist) {
                wh = p;
                dist = value;
            }
        }
        cluster[wh].push_back(points[now]);
        visit[now] = 1;
    }

    for (int i = 0; i < 8; i++) {
        cout << "Center " << i + 1 << ": [" << center[i].x << ", " << center[i].y << "]" << endl;
        cout << "Cluter " << i + 1 << ": ";
        for (int j = 0; j < cluster[i].size(); j++) {
            cout << " [" << cluster[i][j].x << " , " << cluster[i][j].y << "]";
        }
        cout << endl<<endl;
    }
}


 
