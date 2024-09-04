import copy
from collections import deque
from itertools import combinations

n,m=map(int,input().split())

virus=[]
matrix=[]
for i in range(n):
    line=list(map(int,input().split()))
    for j in range(n):
        if line[j]==1:
            line[j]='-'
        elif line[j]==2:
            line[j]='*'
            virus.append([i,j])
    matrix.append(line)
answer=999999
for now in combinations(virus,m):
    n_matrix=copy.deepcopy(matrix)
    visit=[[0]*n for _ in range(n)]
    queue = deque()
    ma = 0
    for y,x in now:
        queue.append([y,x,0])
    while queue:
        y,x,count = queue.popleft()
        for ny,nx in [y-1,x],[y+1,x],[y,x+1],[y,x-1]:
            if 0<=ny<n and 0<=nx<n and n_matrix[ny][nx]!='-' and visit[ny][nx]==0:
                visit[ny][nx]=1
                if n_matrix[ny][nx]==0:
                    n_matrix[ny][nx]=count+1
                    ma = max(ma,count+1)
                queue.append([ny,nx,count+1])
    check = False
    for _ in n_matrix:
        if _.count(0):
            check=True
            break
    if check:
        continue


    answer=min(answer,ma)
if answer==999999:
    print(-1)
else:
    print(answer)