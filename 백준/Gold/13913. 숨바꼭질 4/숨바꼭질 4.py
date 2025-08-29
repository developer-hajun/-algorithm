from collections import deque
import sys
n , k = map(int, input().split())
queue = deque()
queue.append([n,0])
visit=[0]*100001
boom = [-1]*100001
while queue:
    now,count = queue.popleft()
    if now==k:
        print(count)
        answer = []
        answer.append(now)
        while now!=n:
            now = boom[now]
            answer.append(now)
        answer.reverse()
        for i in answer:
            print(i,end=' ')
        break
    for i in [now+1,now-1,now*2]:
        if 0<=i<=100000 and visit[i]==0:
            queue.append([i,count+1])
            visit[i]=1
            boom[i]=now


