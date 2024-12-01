from collections import deque
def solution(n, roads, sources, destination):
    edge = [[] for _ in range((max(max(k) for k in roads)+1))]
    for i,j in roads:
        edge[i].append(j)
        edge[j].append(i)
    visit = [210000000 for _ in range(len(edge))]
    visit[destination] = 0
    queue = deque()
    queue.append([destination,0])
    while queue:
        now,count = queue.popleft()
        for move in edge[now]:
            if visit[move]==210000000:
                visit[move]=count+1
                queue.append([move,count+1])
    answer = []
    for _ in sources:
        if visit[_]==210000000:
            answer.append(-1)
        else:
            answer.append(visit[_])
    return answer