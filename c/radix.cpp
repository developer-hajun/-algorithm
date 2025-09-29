#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;


void SortLSD(vector<int>& arr) {
    int maxVal = *max_element(arr.begin(), arr.end());
    int exp = 1; 
    vector<int> output(arr.size());
    
    while (maxVal / exp > 0) {
        vector<int> count(10, 0);

        for (int i=0;i<arr.size();i++) {
            int num = arr[i];
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }

        arr = output;
        exp *= 10;
    }
}

void SortMSD(vector<int>& arr, int left, int right, int exp) {
    if (left >= right || exp == 0) return;

    vector<int> buckets[10];

    for (int i = left; i <= right; i++) {
        int digit = (arr[i] / exp) % 10;
        buckets[digit].push_back(arr[i]);
    }

    int idx = left;
    for (int i = 0; i < 10; i++) {
        for (int j=0;j<buckets[i].size();j++) {
            int num= buckets[i][j];
            arr[idx++] = num;
        }
    }

    idx = left;
    for (int i = 0; i < 10; i++) {
        if (!buckets[i].empty()) {
            SortMSD(arr, idx, idx + buckets[i].size() - 1, exp / 10);
            idx += buckets[i].size();
        }
    }
}

void MSD(vector<int>& arr) {
    int maxVal = *max_element(arr.begin(), arr.end());
    int exp = 1;
    while (maxVal / exp > 0) exp *= 10;
    exp /= 10;
    SortMSD(arr, 0, arr.size() - 1, exp);
}



void writeOutput(const string& filename, const vector<int>& arr) {
    ofstream outFile(filename);
    for (int i=0;i<arr.size();i++) {
        int num = arr[i];
        outFile << num << endl;
    }
    outFile.close();
}

int main() {

    ifstream inFile("input.txt");
    vector<int> arr;
    int num;
    
    while (inFile >> num) {
        arr.push_back(num);
    }
    inFile.close();


    vector<int> arrLSD = arr;
    SortLSD(arrLSD);
    writeOutput("radix_lsd_output.txt", arrLSD);

    vector<int> arrMSD = arr;
    
    int maxVal = *max_element(arrMSD.begin(), arrMSD.end());
    int exp = 1;
    while (maxVal / exp > 0) exp *= 10;
    exp /= 10;
    
    SortMSD(arrMSD, 0, arrMSD.size() - 1, exp);
    
    
    writeOutput("radix_msd_output.txt", arrMSD);

    return 0;
}
