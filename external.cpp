
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

void insertionSort(std::vector<int>& arr) {
    int n = arr.size();
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

vector<int> merge(vector<int>& vec1,vector<int>& vec2) {
    vector<int> merged;
    int i = 0, j = 0;

    // 두 배열을 비교하면서 합병
    while (i < vec1.size() && j < vec2.size()) {
        if (vec1[i] < vec2[j]) {
            merged.push_back(vec1[i]);
            i++;
        } else {
            merged.push_back(vec2[j]);
            j++;
        }
    }

    // 남아있는 요소 추가
    while (i < vec1.size()) {
        merged.push_back(vec1[i]);
        i++;
    }
    while (j < vec2.size()) {
        merged.push_back(vec2[j]);
        j++;
    }

    return merged;
}
void writeOutput(const string& filename, const vector<int>& arr) {
    ofstream outFile(filename);
    for (int i=0;i<arr.size();i++) {
        int num = arr[i];
        outFile << num << endl;
    }
    outFile.close();
}

int main()
{
    vector<int> value[10];
    
    ifstream inFile("input.txt");
    int num;
    
    int now = 0;
    while (inFile >> num) {
        value[now].push_back(num);
        if(value[now].size()==100){
            insertionSort(value[now]);
            now+=1;
        }
    }
    inFile.close();
    
    queue<vector<int> > input;
    queue<vector<int> > output;
    for(int i=0;i<10;i++){
        input.push(value[i]);
    }
    while (input.size()>1){
        while(input.size()!=0){
            if (input.size()>1){
                vector<int> first = input.front();
                input.pop();
                vector<int> second = input.front();
                input.pop();
                output.push(merge(first,second));
            }
            else{
                output.push(input.front());
                input.pop();
            }
        }
        while(output.size()!=0){
            input.push(output.front());
            output.pop();
        }
    }
    vector<int> answer = input.front();
    writeOutput("external_output.txt",answer);
}