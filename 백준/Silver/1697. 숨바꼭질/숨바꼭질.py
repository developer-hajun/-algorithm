import sys
from collections import deque

def bfs(last,count,queue):
    ss = len(queue)
    answer = 0
    count = 0
    while queue:
        now = queue.popleft()
        if now == last:
            print(answer)
            break
        for i in [now-1,now+1,now*2]:
            if 0<=i<100001 and visit[i]==0:
                queue.append(i)
                visit[i]=1
        count+=1
        if count==ss:
            count=0
            ss= len(queue)
            answer+=1
a,b = map(int,input().split())

queue = deque()
visit = [0]*100001
queue.append(a)
visit[a]=1
if a==b:
    print(0)
    sys.exit()
bfs(b,0,queue)