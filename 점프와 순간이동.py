import heapq


def solution(n):
    count = 210000000
    heap = []
    heapq.heappush(heap, [0, n])

    while heap:
        count, now = heapq.heappop(heap)
        if now == 1:
            return count + 1
        if now % 2 == 0:
            heapq.heappush(heap, [count, now // 2])
        else:
            if (now - 1) // 2 != 0:
                heapq.heappush(heap, [count + 1, (now - 1) // 2])