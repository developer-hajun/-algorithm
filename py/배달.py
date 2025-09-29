from collections import deque


def solution(N, road, K):
    visit = [999999] * (N + 1)
    visit[1] = 0
    graph = [[] for _ in range(N + 1)]
    for start, end, count in road:
        graph[start].append([end, count])
        graph[end].append([start, count])

    queue = deque()
    queue.append([1, 0])
    while queue:
        start, count = queue.popleft()

        for end, plus in graph[start]:
            if visit[end] > count + plus:
                visit[end] = count + plus
                queue.append([end, count + plus])
    count = 0
    for i in visit[1:]:
        if i <= K:
            count += 1
    return count
