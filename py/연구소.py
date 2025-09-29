import copy
from collections import deque


n ,m = map(int ,input().split())
matrix = [list(map(int ,input().split())) for _ in range(n)]
bias =[]
walls = 0
for y in range(n):
    for x in range(m):
        if matrix[y][x] == 1:
            walls+=1
        if matrix[y][x]==2:
            bias.append([y ,x])
answer = -1
def wall(ny, nx, count):
    global answer,walls
    if count == 3:
        count = len(bias)
        n_matrix = copy.deepcopy(matrix)
        queue = deque(bias)
        while queue:
            y ,x = queue.popleft()
            for ny,nx in [(y,x+1),(y,x-1),(y-1,x),(y+1,x)]:
                if 0<=ny<n and 0<=nx<m and n_matrix[ny][nx]==0:
                    count+=1
                    n_matrix[ny][nx]=2
                    queue.append([ny,nx])
        answer = max(answer,(n*m)-(count+walls+3))
        return
    else:
        for y in range(ny, n):
            if y == ny:
                for x in range(nx, m):
                    if matrix[y][x] == 0:
                        matrix[y][x] = 1
                        wall(y, x, count + 1)
                        matrix[y][x] = 0
            else:
                for x in range(m):
                    if matrix[y][x] == 0:
                        matrix[y][x] = 1
                        wall(y, x, count + 1)
                        matrix[y][x] = 0
wall(0,0,0)
print(answer)