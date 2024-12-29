import copy


def solution(n, s, a, b, fares):
    graph = [[99999999] * (n) for _ in range(n)]
    for i in range(n):
        graph[i][i] = 0
    for st, en, cost in fares:
        graph[st - 1][en - 1] = cost
        graph[en - 1][st - 1] = cost
    for k in range(n):
        for i in range(n):
            if i == k:
                continue
            for j in range(n):
                if i == j or j == k:
                    continue
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
    answer = 9999999

    for k in range(n):
        value = graph[s - 1][k]
        value += graph[k][a - 1]
        value += graph[k][b - 1]
        answer = min(answer, value)
    return answer



