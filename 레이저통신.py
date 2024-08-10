import sys
from collections import deque

w,h = map(int,input().split())
matrix =[]

now =[]
k=[999999,-1]
visit=[]
for _ in range(h):
    line=[]
    for j in range(w):
        line.append([9999999,-1])
    visit.append(line)
for i in range(h):
    line= list(input().strip())
    for j in range(w):
        if line[j]=='C' and not now:
            now = [i,j]
            visit[i][j]=[0,0]
    matrix.append(line)
queue = deque()
for i in range(4):
    queue.append(now+[i]+[0])
move = [[-1,0],[0,-1],[1,0],[0,1]]
answer = 999999
while queue:
    y,x,d,count = queue.popleft()
    moves=[]
    if d%2==0:
        moves=[[d,0],[1,1],[3,1]]
    else:
        moves=[[d,0],[0,1],[2,1]]
    for ne,c in moves:
        ny = y+move[ne][0]
        nx = x+move[ne][1]
        nc = count+c
        if 0<=ny<h and 0<=nx<w and (visit[ny][nx][0]>nc or (visit[ny][nx][0]>=nc and visit[ny][nx][1]!=ne)) and matrix[ny][nx]!='*':
            if matrix[ny][nx]=='C':
                answer = min(answer,nc)
                continue
            visit[ny][nx]=[nc,ne]
            queue.append([ny,nx,ne,nc])
print(answer)