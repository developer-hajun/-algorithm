import heapq


def tt(t):
    t1, t2 = t.split(":")
    return int(t1) * 60 + int(t2)


def solution(book_time):
    for i in range(len(book_time)):
        book_time[i] = [tt(book_time[i][0]), tt(book_time[i][1])]
    book_time.sort(key=lambda x: x[0])
    heap = []
    answer = 0
    for start, end in book_time:
        end += 10

        if heap and start >= heap[0]:
            heapq.heappop(heap)
        else:
            answer += 1

        heapq.heappush(heap, end)
    return answer