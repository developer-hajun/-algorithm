from collections import deque
n,l,r=map(int,input().split())
matrix = [list(map(int,input().split())) for _ in range(n)]
answer = 0
while True:
    visit = [[0]*n for _ in range(n)]
    ch = True
    for i in range(n):
        for j in range(n):
            if visit[i][j]==0:
                visit[i][j]=1
                arr = [[i,j]]
                queue=deque()
                queue.append([i,j])
                while queue:
                    y,x = queue.popleft()
                    for ny,nx in (y-1,x),(y+1,x),(y,x-1),(y,x+1):
                        if 0<=ny<n and 0<=nx<n and visit[ny][nx]==0 and l<=abs(matrix[ny][nx]-matrix[y][x])<=r:
                            ch=False
                            visit[ny][nx]=1
                            queue.append([ny,nx])
                            arr.append([ny,nx])
                if len(arr)==0:
                    continue
                value = 0
                for y,x in arr:
                    value += matrix[y][x]
                value=value//len(arr)
                for y,x in arr:
                    matrix[y][x]=value
    if ch:
        break
    answer+=1

print(answer)