import sys
import heapq

def bubble_k_heap_sort(arr, k):
    n = len(arr)
    result = []
    heap = []

    for i in range(min(k + 1, n)):
        heapq.heappush(heap, arr[i])

    next_index = k + 1
    for _ in range(n):
        value = heapq.heappop(heap)
        result.append(value)

        # 다음 원소를 넣기
        if next_index < n:
            heapq.heappush(heap, arr[next_index])
            next_index += 1

    return result


# 입력 처리
input = sys.stdin.readline
n, k = map(int, input().split())
arr = list(map(int, input().split()))

res = bubble_k_heap_sort(arr, k)
print(*res)
