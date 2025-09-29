from collections import deque

circle = list(deque(map(int,input().strip())) for _ in range(4))


def rotate(now,dir,visit):
    visit[now]=1
    l,r = circle[now][6],circle[now][2]
    if dir==1:
        circle[now].appendleft(circle[now].pop())
    else:
        circle[now].append(circle[now].popleft())
    nl = now-1
    nr = now+1
    next_dir = 1 if dir==-1 else -1
    if 0<=nl<4 and circle[nl][2]!=l and visit[nl]==0:
        rotate(nl,next_dir,visit)
    if 0<=nr<4 and circle[nr][6]!=r and visit[nr]==0:
        rotate(nr,next_dir,visit)

k = int(input())
for i in range(k):
    cir,d = map(int,input().split())
    rotate(cir-1,d,[0]*4)

num=1
answer = 0
for i in circle:
    if i[0]==1:
        answer+=num
    num*=2
print(answer)