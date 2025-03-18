def solution(info, edges):
    graph = [[] for _ in range(len(info))]
    for a, b in edges:
        graph[a].append(b)
    visit = [0] * len(info)
    visit[0] = -1
    for i in graph[0]:
        visit[i] = 1

    global answer
    answer = -1

    # : 이미 방문 0: 아직 방문 못함 1: 방문 가능
    def dfs(sheep, wolf, go):
        global answer
        answer = max(answer, sheep)
        for i in range(len(visit)):
            if go[i] == 1:
                if info[i] == 0:
                    for _ in graph[i]:
                        if go[_] == 0:
                            go[_] = 1
                    go[i] = -1
                    dfs(sheep + 1, wolf, go)
                    go[i] = 1
                    for _ in graph[i]:
                        if go[_] == 1:
                            go[_] = 0
                else:
                    if sheep > wolf + 1:
                        for _ in graph[i]:
                            if go[_] == 0:
                                go[_] = 1
                        go[i] = -1
                        dfs(sheep, wolf + 1, go)
                        go[i] = 1
                        for _ in graph[i]:
                            if go[_] == 1:
                                go[_] = 0

    dfs(1, 0, visit)
    return answer