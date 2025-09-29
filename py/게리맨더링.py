from collections import deque
from itertools import combinations

n = int(input())

graph = [[] for _ in range(n)]
human = list(map(int, input().split()))
for i in range(n):
    now = list(map(int, input().split()))
    for k in now[1:]:
        graph[i].append(k-1)
        graph[k-1].append(i)
for i in range(n):
    graph[i]= list(set(graph[i]))

h = set(_ for _ in range(n))
def conn(arr):
    visit = [0]*n
    queue= deque()
    queue.append(arr[0])
    visit[arr[0]]=1
    while queue:
        now = queue.popleft()
        for _ in graph[now]:
            if visit[_]==0 and _ in arr:
                visit[_]=1
                queue.append(_)
    for k in arr:
        if not visit[k]:
            return False
    return True

answer =  99999

for i in range(1,n):
    for a in combinations(h,i):
        a= list(a)
        b = list(h.difference(a))
        if conn(a) and conn(b):
            now = abs(sum( human[_] for _ in b) - sum( human[_] for _ in a))
            answer = min(answer,now)
if answer==99999:
    print(-1)
else:
    print(answer)