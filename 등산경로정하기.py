from collections import defaultdict
from math import inf
import heapq


def solution(n, paths, gates, summits):
    graph = defaultdict(list)
    summits = set(summits)
    for a, b, c in paths:
        graph[a].append([c, b])
        graph[b].append([c, a])

    for _ in graph:
        graph[_].sort()
    distance = [inf] * (n + 1)

    queue = []

    for gate in gates:
        distance[gate] = 0
        heapq.heappush(queue, [0, gate])

    while queue:
        cost, now = heapq.heappop(queue)
        if now in summits or distance[now] < cost:
            continue
        for value, move in graph[now]:
            cost = max(cost, value)
            if distance[move] > cost:
                distance[move] = cost
                heapq.heappush(queue, [cost, move])
    summits = sorted(list(summits))
    answer = [-1, inf]
    for i in summits:
        if answer[1] > distance[i]:
            answer = [i, distance[i]]
    return answer

