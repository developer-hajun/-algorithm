from collections import deque

n = int(input())

dist = [9999999]*(n+1)
dist[n]=0
queue = deque()
queue.append([n,0])
while queue:
    value,count = queue.popleft()
    if value==1:
        continue
    if value%3==0 and dist[value//3]>count+1:
        dist[value//3] = count+1
        queue.append([value//3,count+1])
    if value%2==0 and dist[value//2]>count+1:
        dist[value//2] = count+1
        queue.append([value//2,count+1])
    if dist[value-1]>count+1:
        dist[value-1]=count+1
        queue.append([value-1,count+1])
print(dist[1])