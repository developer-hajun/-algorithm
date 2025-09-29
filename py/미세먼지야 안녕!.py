from collections import deque

def cycle():
    for i in range(1, c):
        cycle1.append([w[0] - 1, i])
        cycle2.append([w[0], i])
    now = 1
    while True:
        ny1 = w[0] - 1 - now
        ny2 = w[0] + now
        if 0 > ny1 and ny2 >= r:
            break
        if 0 <= ny1:
            cycle1.append([ny1, c - 1])
        if ny2 < r:
            cycle2.append([ny2, c - 1])
        now += 1
    for i in range(c - 2, -1, -1):
        cycle1.append([0, i])
        cycle2.append([r - 1, i])
    now = 1
    while True:
        ny1 = 0 + now
        ny2 = r - 1 - now
        if ny1 >= w[0] - 1 and ny2 <= w[0]:
            break
        if ny1 < w[0] - 1:
            cycle1.append([ny1, 0])
        if ny2 > w[0]:
            cycle2.append([ny2, 0])
        now += 1
    cycle1.reverse()
    cycle2.reverse()



r,c,t  = map(int,input().split())

w = []
matrix=[]
queue=deque()
for i in range(r):
    line = list(map(int,input().split()))
    for j in range(c):
        if line[j] != -1 and line[j]!=0:
            queue.append([i,j,line[j]])
        elif line[j]==-1:
            w=[i,j]
    matrix.append(line)
cycle1=[]
cycle2=[]
cycle()

for _ in range(t):
    #미세먼지 확장
    plus = []
    move = []
    while queue:
        y,x,value = queue.popleft()
        count=0
        for ny,nx in [y+1,x],[y-1,x],[y,x+1],[y,x-1]:
            if 0<=ny<r and 0<=nx<c and matrix[ny][nx]!=-1:
                count+=1
                plus.append([ny,nx,matrix[y][x]//5])
        matrix[y][x] = matrix[y][x] - ((matrix[y][x]//5)*count)
    for y,x,value in plus:
        matrix[y][x]+=value
    #공기 청정기 작동
    for i in range(1,len(cycle1)):
        go_y,go_x = cycle1[i-1]
        now_y,now_x = cycle1[i]
        matrix[go_y][go_x]=matrix[now_y][now_x]
        matrix[now_y][now_x]=0
    for i in range(1,len(cycle2)):
        go_y,go_x = cycle2[i-1]
        now_y,now_x = cycle2[i]
        matrix[go_y][go_x]=matrix[now_y][now_x]
        matrix[now_y][now_x] = 0

    for i in range(r):
        for j in range(c):
            if matrix[i][j]!=0 and matrix[i][j]!=-1:
                queue.append([i,j,matrix[i][j]])

s=0
for i in matrix:
    s+=sum(i)
print(s+2)