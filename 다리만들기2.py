from collections import deque


def find_land():
    visit = [[0]*m for _ in range(n)]
    now = 1
    for i in range(n):
        for j in range(m):
            if matrix[i][j]!=0 and visit[i][j]==0:
                queue = deque()
                queue.append([i,j])
                visit[i][j]=now
                while queue:
                    y,x = queue.popleft()
                    for ny,nx in [y+1,x],[y-1,x],[y,x+1],[y,x-1]:
                        if 0<=ny<n and 0<=nx<m and matrix[ny][nx]!=0 and visit[ny][nx]==0:
                            visit[ny][nx]=now
                            queue.append([ny,nx])
                now+=1
    return visit,now

def find_land2(y,x):
    queue = deque()
    save = [[i, j]]
    queue.append([i, j])
    visit[i][j] = 1
    while queue:
        y, x = queue.popleft()
        for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= ny < n and 0 <= nx < m and matrix[ny][nx] != 0 and visit[ny][nx] == 0:
                visit[ny][nx] = 1
                save.append([ny, nx])
                queue.append([ny, nx])
    return save

def append_queue(vector):
    queue = deque()
    for ny, nx in vector:
        queue.append([ny, nx, 0,0])
        queue.append([ny, nx, 1,0])
        queue.append([ny, nx, 2,0])
        queue.append([ny, nx, 3,0])
    return queue

def find_bridge(queue,now_number):
    move = [[0,1],[1,0],[0,-1],[-1,0]]
    while queue:
        y,x,move_count,count = queue.popleft()
        ny = y+move[move_count][0]
        nx = x+move[move_count][1]
        if 0 <= ny < n and 0 <=nx <m:
            if matrix[ny][nx] ==0:
                queue.append([ny,nx,move_count,count+1])
            elif matrix[ny][nx]!=now_number:
                if count>=2:
                    edge.append([count,now_number,matrix[ny][nx]])

def find_parent(parent,x):
    if parent[x]!=x:
        parent[x]=find_parent(parent,parent[x])
    return parent[x]

def union_parent(a,b,x,y):
    if a<b:
        parent[a]=y
    else:
        parent[b]=x

n,m = map(int,input().split())
matrix = [list(map(int,input().split())) for _ in range(n)]
matrix,now = find_land()
visit=[[0]*m for _ in range(n)]
edge =[]

for i in range(n):
    for j in range(m):
        if matrix[i][j]!=0 and visit[i][j]==0:
            save = find_land2(i,j)
            queue = append_queue(save)
            find_bridge(queue,matrix[i][j])
edge = list(set([tuple(i) for i in edge]))
edge.sort()
parent=[ i for i in range(now)]



total_cost=0
ch = True
visited =[0]*now
num=0
for i in range(len(edge)):
    cost,x,y = edge[i]
    a= find_parent(parent,x)
    b= find_parent(parent,y)
    if a!=b:
        num+=1
        union_parent(a,b,x,y)
        total_cost +=cost
if total_cost ==0 or num!=now-2:
    print(-1)
else:
    print(total_cost)


