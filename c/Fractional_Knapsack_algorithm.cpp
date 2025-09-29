#include <iostream>
#include <vector>
#include <queue>
#include <time.h>
#include <tuple>
using namespace std;

int main() {
	clock_t start = clock();
	vector<tuple<int, int,string>> arr;
	arr.push_back(make_tuple(10,60,"platinum")); 
	arr.push_back(make_tuple(50, 5,"tin")); 
	arr.push_back(make_tuple(25, 10,"silver"));
	arr.push_back(make_tuple(15, 75,"goid"));
	priority_queue<tuple<float,int,int,string>> pq;
	for (tuple<int, int,string> now : arr) {
		int h = get<0>(now);
		int w = get<1>(now);
		string name = get<2>(now);
		float hw = float(w) / float(h);
		pq.push(make_tuple(hw, h, w,name));
	}
	float a = 40;
	float answer = 0;
	printf("%7s  %10s   %10s\n", "Goods", "Weight of goods in knapsack", "Value of goods in knapsack");
	while (!pq.empty()) {
		tuple<float, int, int,string> now = pq.top();
		pq.pop();
		float q = get<0>(now);
		int h = get<1>(now);
		int w = get<2>(now);
		string e = get<3>(now);
		if (a >= h) {
			answer += w;
			a -= h;
			
		}
		else {
			float value = q * a;
			answer += value;
			a = 0;
		}
		printf("%8s %14.1f %32.1f\n",e.c_str(), answer, 40.0 - a);
	}
	printf("%8s %14.1f %32.1f\n","sum", answer, 40.0 - a);
	clock_t end = clock();
	printf("running time: %lf\n", (double)(end - start) / CLOCKS_PER_SEC);
	
}
