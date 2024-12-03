from collections import deque


def solution(cacheSize, cities):
    for i in range(len(cities)):
        cities[i] = cities[i].upper()
    queue = deque()
    answer = 0
    if cacheSize == 0:
        return len(cities) * 5

    for i in cities:
        if i in queue:
            save = deque()
            while queue and queue[0] != i:
                save.append(queue.popleft())
            queue.popleft()
            queue = save + queue
            queue.append(i)
            answer += 1
        else:
            if len(queue) >= cacheSize:
                queue.popleft()
            answer += 5
            queue.append(i)

    return answer