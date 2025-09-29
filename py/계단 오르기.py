from collections import deque

t = int(input())
dist = [[0]*(t+1) for _ in range(3)]
value = [0]+list(int(input()) for _ in range(t))
queue = deque()

queue.append([0,0,0])
while queue:
    val,cost,which = queue.popleft()
    if which==t:
        continue
    if cost<=1 and which+1<=t and dist[cost+1][which+1]<val+value[which+1]:
        dist[cost+1][which+1] = val+value[which+1]
        queue.append([val+value[which+1],cost+1,which+1])
    if which+2<=t and dist[1][which+2]<val+value[which+2]:
        dist[1][which+2]=val+value[which+2]
        queue.append([val+value[which+2],1,which+2])
print(max(dist[0][t],dist[1][t],dist[2][t]))