#include <iostream>
#include <vector>
#include <queue>
#include <time.h>
#include <tuple>
using namespace std;

int main() {
	clock_t start = clock();
	vector<pair<int, int>> arr;
	arr.push_back(make_pair(10,60));
	arr.push_back(make_pair(50, 5));
	arr.push_back(make_pair(25, 10));
	arr.push_back(make_pair(15, 75));
	priority_queue<tuple<float,int,int>> pq;
	for (pair<int, int> now : arr) {
		int h = now.first;
		int w = now.second;
		float hw = float(w) / float(h);
		pq.push(make_tuple(hw, h, w));
	}
	float a = 40;
	float answer = 0;
	while (!pq.empty() && a!=0) {
		tuple<float, int, int> now = pq.top();
		pq.pop();
		float q = get<0>(now);
		int h = get<1>(now);
		int w = get<2>(now);
		if (a >= h) {
			answer += w;
			a -= h;
			
		}
		else {
			float value = q * a;
			answer += value;
			a = 0;
		}
		printf("%f %f\n", answer, 40.0 - a);
	}
	printf("%f %f\n", answer, 40.0 - a);
	clock_t end = clock();
	printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);
	
}