n,m = map(int,input().split())

move = [(-1,0),(0,1),(1,0),(0,-1)]
y,x,d = map(int,input().split())
matrix = [list(map(int,input().split())) for _ in range(n)]
count=0
while True:
    if matrix[y][x] == 0:
        count+=1
        matrix[y][x]=2
    check =True
    for i in range(4):
        d-=1
        if d==-1:
            d=3
        ny,nx = y+move[d][0],x+move[d][1]
        if 0<=ny<n and 0<=nx<m and matrix[ny][nx]==0:
            check=False
            y,x = ny,nx
            break
    if check:
        ny,nx = y-move[d][0],x-move[d][1]
        if 0<=ny<n and 0<=nx<m and matrix[ny][nx]!=1:
            y,x = ny,nx
        else:
            break

print(count)