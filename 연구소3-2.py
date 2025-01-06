import sys
from itertools import combinations
from collections import deque
import copy
n,m = map(int,input().split())

matrix = [list(map(int,input().split())) for _ in range(n)]

bias = []
count = 0
for i in range(n):
    for j in range(n):
        if matrix[i][j]==2:
            bias.append([i,j])
        elif matrix[i][j]==0:
            count+=1

answer = 99999999

def bfs(bis,count):
    value=0
    queue = deque()
    for y,x in bis:
        queue.append([y,x,0])
    visit = [[0]*n for _ in range(n)]
    n_matrix = copy.deepcopy(matrix)
    while count!=0 and queue:
        y,x,c = queue.popleft()
        value = max(value, c)
        for ny,nx in [(y,x-1),(y,x+1),(y+1,x),(y-1,x)]:
            if 0<=ny<n and 0<=nx<n and n_matrix[ny][nx]!=1 and visit[ny][nx]==0:
                queue.append([ny,nx,c+1])
                if matrix[ny][nx]==0:
                    count-=1
                n_matrix[ny][nx]=2
                visit[ny][nx]=1
    return value,count

if count==0:
    print(0)
    sys.exit()

for bi in combinations(bias,m):
    v,c = bfs(bi,count)
    if c!=0:
        continue
    answer = min(answer,v+1)
if answer == 99999999:
    print(-1)
else:
    print(answer)
