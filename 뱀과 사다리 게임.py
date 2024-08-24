from collections import deque

n,m = map(int,input().split())
dic = {}

for i in range(n+m):
    a,b = map(int,input().split())
    dic[a]=b

queue=deque()
queue.append([1,0])
visit=[99999999]*101
visit[1]=0
while queue:
    now , count = queue.popleft()
    if now==100:
        print(count)
        break
    for i in [1,2,3,4,5,6]:
        move = now+i
        if move>100:
            continue
        elif move in dic:
            move = dic[move]
        if visit[move]>count:
            visit[move]=count+1
            queue.append([move,count+1])