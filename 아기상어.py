import sys
from collections import deque
import copy
input = sys.stdin.readline
n=int(input())
matrix = []
now = []
for _ in range(n):
    line = list(map(int, input().split()))
    for k in range(n):
        if line[k]==9:
            now = [_,k]
            line[k]=0
    matrix.append(line)


shark_size = 2
eat_fish = 0
time=0
def bfs():
    queue = deque()
    queue.append(now)
    visit= [[-1]*n for _ in range(n)]
    visit[now[0]][now[1]]=0
    while queue:
        y,x = queue.popleft()
        for nextY,nextX in [-1,0],[1,0],[0,-1],[0,1]:
            ny = y+nextY
            nx = x+nextX
            if 0<=ny<n and 0<=nx<n and visit[ny][nx]==-1 and matrix[ny][nx]<=shark_size:
                visit[ny][nx]=visit[y][x]+1
                queue.append([ny,nx])
    return visit

def find_find(visit):
    min_value = 999999999
    y,x =-1,-1
    for i in range(n):
        for j in range(n):
            if visit[i][j]!=-1 and 0<matrix[i][j]<shark_size:
                if min_value>visit[i][j]:
                    min_value=visit[i][j]
                    y,x =i,j
    if min_value==999999999:
        return False
    else:
        return y,x,min_value

while True:
    result = find_find(bfs())

    if not result:
        print(time)
        break
    else:
        now = [result[0],result[1]]
        time+=result[2]
        matrix[result[0]][result[1]]=0
        eat_fish+=1
        if eat_fish==shark_size:
            eat_fish=0
            shark_size +=1




