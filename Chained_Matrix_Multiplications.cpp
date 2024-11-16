#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <regex>
#include <vector>
#include <unordered_map>
#include <algorithm>  
#include <climits>   

using namespace std;

unordered_map<string, pair<int, int> > read_matrix_dimensions(const string& file_path) {
    ifstream file(file_path);
    if (!file.is_open()) {
        cerr << "Error opening file: " << file_path << endl;
        exit(1);
    }
    
    stringstream buffer;
    buffer << file.rdbuf();
    string data = buffer.str();

    unordered_map<string, pair<int, int> > matrix_dimensions;
    regex matrix_split_regex("\n(?=A\\d+ =)");
    sregex_token_iterator iter(data.begin(), data.end(), matrix_split_regex, -1);
    sregex_token_iterator end;
    
    for (; iter != end; ++iter) {
        string matrix = *iter;
        regex name_regex("(A\\d+) =");
        smatch name_match;
        if (regex_search(matrix, name_match, name_regex)) {
            string matrix_name = name_match[1];
            
            size_t equal_pos = matrix.find('=');
            string matrix_data = matrix.substr(equal_pos + 1);
            matrix_data = regex_replace(matrix_data, regex("\\]\\s*\\["), "];[");  
            
            vector<string> rows;
            stringstream ss(matrix_data);
            string row;
            while (getline(ss, row, ';')) {
                rows.push_back(row);
            }
            int num_rows = rows.size();
            
            regex number_regex("-?\\d+");
            sregex_iterator row_begin(rows[0].begin(), rows[0].end(), number_regex);
            sregex_iterator row_end;
            int num_columns = distance(row_begin, row_end);
            
            // 결과 저장
            matrix_dimensions[matrix_name] = make_pair(num_rows, num_columns);
        }
    }
    
    return matrix_dimensions;
}

int matrixChainOrder(const vector<pair<int, int> >& matrices) {
    int n = matrices.size();
    vector<vector<int> > dp(n, vector<int>(n, 0));

    for (int length = 2; length <= n; length++) {
        for (int i = 0; i < n - length + 1; i++) {
            int j = i + length - 1;
            dp[i][j] = INT_MAX; 
            for (int k = i; k < j; k++) {
                int cost = dp[i][k] + dp[k + 1][j] + matrices[i].first * matrices[k].second * matrices[j].second;
                if (cost < dp[i][j]) {
                    dp[i][j] = cost;
                }
            }
        }
    }

    cout << "\t";
    for (int j = 0; j < n; ++j) {
        cout << j << "\t";
    }
    cout << endl;

    for (int i = 0; i < n; ++i) {
        cout << i << "\t";
        for (int j = 0; j < n; ++j) {
            if (dp[i][j] == INT_MAX) {
                cout << "INF" << "\t";
            } else {
                cout << dp[i][j] << "\t";
            }
        }
        cout << endl;
    }

    return dp[0][n - 1];
}

// 정렬을 위한 비교 함수 객체 정의
struct CompareMatrixNames {
    bool operator()(const pair<string, pair<int, int> >& a, const pair<string, pair<int, int> >& b) const {
        int num_a = stoi(a.first.substr(1));
        int num_b = stoi(b.first.substr(1));
        return num_a < num_b;
    }
};

int main() {
    string file_path = "matrix_input.txt";
    unordered_map<string, pair<int, int> > matrix_dimensions = read_matrix_dimensions(file_path);
    vector<pair<string, pair<int, int> > > sorted_matrix_dimensions(matrix_dimensions.begin(), matrix_dimensions.end());
    sort(sorted_matrix_dimensions.begin(), sorted_matrix_dimensions.end(), CompareMatrixNames());

    vector<pair<int, int> > value;
    for (vector<pair<string, pair<int, int> > >::iterator it = sorted_matrix_dimensions.begin(); it != sorted_matrix_dimensions.end(); ++it) {
        value.push_back(make_pair(it->second.first, it->second.second));
    }

    int minCost = matrixChainOrder(value);
    cout << "answer: " << minCost << endl;

    return 0;
}
