#include <iostream>
#include <algorithm>
#include <iomanip>

int main() {
    const int MAX_AMOUNT = 21;
    const int INF = 999999;
    
    // 배열 선언
    int arr[MAX_AMOUNT];
    for (int i = 0; i < MAX_AMOUNT; ++i) {
        arr[i] = INF;
    }
    arr[0] = 0;  // 0원을 만들기 위한 동전 수는 0
    arr[16] = 1; // 16원을 만들기 위한 동전 수
    arr[10] = 1; // 10원을 만들기 위한 동전 수
    arr[5] = 1;  // 5원을 만들기 위한 동전 수
    arr[1] = 1;  // 1원을 만들기 위한 동전 수

    // 동전의 가치 배열
    int cost[] = {16, 10, 5, 1};
    const int costSize = sizeof(cost) / sizeof(cost[0]);

    // 동적 프로그래밍을 통해 최소 동전 수 계산
    for (int i = 1; i < MAX_AMOUNT; ++i) {
        for (int j = 0; j < costSize; ++j) {
            int value = cost[j];
            if (i - value >= 0) {
                arr[i] = std::min(arr[i], arr[i - value] + 1);
            }
        }
    }

    // j 출력
    std::cout << "j";
    for (int i = 0; i < MAX_AMOUNT; ++i) {
        std::cout << std::setw(*std::max_element(arr, arr + MAX_AMOUNT) - 2) << i;
    }
    std::cout << std::endl;

    // c 출력
    std::cout << "c";
    for (int i = 0; i < MAX_AMOUNT; ++i) {
        std::cout << std::setw(*std::max_element(arr, arr + MAX_AMOUNT) - 2) << arr[i];
    }
    std::cout << std::endl;

    return 0;
}
