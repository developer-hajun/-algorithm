import sys
from collections import deque

n,m = map(int,input().split())

matrix = [list(input().strip()) for _ in range(n)]

queue = deque()
fire=deque()
for i in range(n):
    for j in range(m):
        if matrix[i][j]=='F':
            fire.append([i,j])
        elif matrix[i][j]=='J':
            queue.append([i,j,0])
            matrix[i][j]='.'

visit = [[10000001]*m for _ in range(n)]
while queue:
    new_queue = deque()
    while queue:
        y,x,count = queue.popleft()
        if matrix[y][x]=='F':
            continue
        for ny,nx in [y+1,x],[y-1,x],[y,x+1],[y,x-1]:
            if 0<=ny<n and 0<=nx<m and visit[ny][nx]>count+1 and matrix[ny][nx]=='.':
                new_queue.append([ny,nx,count+1])
                visit[ny][nx]= count+1
            elif not (0<=ny<n and 0<=nx<m):
                print(count+1)
                sys.exit()
    queue = new_queue
    new_fire=deque()
    while fire:
        y,x= fire.popleft()
        for ny,nx in [y+1,x],[y-1,x],[y,x+1],[y,x-1]:
            if 0<=ny<n and 0<=nx<m and matrix[ny][nx]=='.':
                matrix[ny][nx]='F'
                new_fire.append([ny,nx])
    fire=new_fire


print("IMPOSSIBLE")
