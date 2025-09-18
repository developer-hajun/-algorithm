import sys
import heapq

n = int(sys.stdin.readline())
distances = [9999999999] * (n + 1)
bus = []

bus_value = int(sys.stdin.readline())
for i in range(n+1):
    line=[]
    bus.append(line)
for i in range(bus_value):
    a, b, c = map(int, sys.stdin.readline().split())
    bus[a].append([c,b])

start , end = map(int, sys.stdin.readline().split())
queue = []
distances[start]=0
heapq.heappush(queue,[0,start])
while queue:
    cost,node = heapq.heappop(queue)
    if distances[node]<cost:
        continue
    for go_cost,go_node in bus[node]:
        distance = cost + go_cost
        if distance < distances[go_node]:
            distances[go_node] = distance
            heapq.heappush(queue,[distance,go_node])
print(distances[end])