from collections import deque

n,m = map(int,input().split())
matrix = [list(map(str,input().strip())) for _ in range(n)]

answer = 0
for i in range(n):
    for j in range(m):
        if matrix[i][j]=='L':
            visit=[[0]*m for _ in range(n)]
            visit[i][j] = 1
            queue= deque()
            queue.append([i,j,0])
            v = 0
            while queue:
                y,x,count = queue.popleft()
                for ny,nx in (y-1,x),(y+1,x),(y,x+1),(y,x-1):
                    if 0<=ny<n and 0<=nx<m and visit[ny][nx]==0 and matrix[ny][nx]=='L':
                        visit[ny][nx] = 1
                        queue.append([ny,nx,count+1])
                        v = max(v,count+1)
            answer = max(v,answer)
print(answer)
