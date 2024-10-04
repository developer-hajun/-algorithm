import copy
import heapq

n,m,x = map(int,input().split())

move = [[101]*(n+1) for _ in range(n+1)]
visit = [[False]*(n+1) for _ in range(n+1)]

edge = [[] for _ in range(n+1)]
for _ in range(m):
    a,b,c = map(int,input().split())
    edge[a].append([b,c])



def dijkstra(start):
    q=[]
    heapq.heappush(q, (0, start))
    distance[start]=0
    while q : # 큐가 비어있지 않다면
        dist, now= heapq.heappop(q)
        if distance[now] < dist:
            continue
        for i in edge[now]:
            cost = dist + i[1]
            if cost <distance[i[0]]:
                distance[i[0]]=cost
                heapq.heappush(q, (cost, i[0]))

distance = [int(1e9)] * (n + 1)
dijkstra(x)
now = copy.deepcopy(distance)


dist = [0]*(n+1)
for i in range(1,n+1):
    if i==x:
        continue
    distance = [int(1e9)] * (n + 1)
    dijkstra(i)
    dist[i]= distance[x]+now[i]
print(max(dist))