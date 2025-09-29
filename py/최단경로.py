import heapq
from collections import deque

n, m = map(int, input().split())

distances = [9999999]*(n+1)
start = int(input())

mini = []
for i in range(n+1):
    line =[]
    mini.append(line)
for i in range(m):
    a,b,c = map(int, input().split())
    mini[a].append([c,b])

distances[start]=0
queue = []
heapq.heappush(queue,[0,start])
while queue:
    dist,node = heapq.heappop(queue)
    if distances[node]<dist:
        continue
    for go_cost,go_node in mini[node]:
        go_dist=dist+go_cost
        if distances[go_node] > go_dist:
            distances[go_node]=go_dist
            heapq.heappush(queue,[go_dist,go_node])

for i in range(1,len(distances)):
    if distances[i]==9999999:
        print("INF")
    else:
        print(distances[i])