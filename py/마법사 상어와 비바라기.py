import copy

n,m = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]
move_cloud = [[0, -1], [-1, -1], [-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1]]
cloud = [[0]*n for _ in range(n)]
cloud[n-1][0]=1
cloud[n-1][1]=1
cloud[n-2][0]=1
cloud[n-2][1]=1

def m_cloud():
    new_cloud = [[0]*n for _ in range(n)]
    for y in range(n):
        for x in range(n):
            if cloud[y][x]==1:
                ny = y + (move_cloud[move][0]*count)
                nx = x + (move_cloud[move][1]*count)
                if ny < 0:
                    while not 0<=ny<n:
                        ny = n-abs(ny)
                if ny >= n:
                    ny = ny % n
                if nx < 0:
                    while not 0 <= nx < n:
                        nx = n-abs(nx)
                if nx >= n:
                    nx = nx % n
                new_cloud[ny][nx]=1
    for y in range(n):
        for x in range(n):
            if new_cloud[y][x] == 1:
                matrix[y][x]+=1

    return new_cloud

def rain_mod():
    for y in range(n):
        for x in range(n):
            if cloud[y][x]:
                count=0
                for ny,nx in [y+1,x+1],[y-1,x-1],[y-1,x+1],[y+1,x-1]:
                    if 0<=ny<n and 0<=nx<n and matrix[ny][nx]:
                        count+=1
                matrix[y][x]+=count
def new_cloud():
    for y in range(n):
        for x in range(n):
            if cloud[y][x]==1:
                cloud[y][x]=0
            elif matrix[y][x]>1:
                matrix[y][x]-=2
                cloud[y][x]=1
for _ in range(m):
    move , count = map(int, input().split())
    move-=1
    cloud = m_cloud()
    rain_mod()
    new_cloud()
answer = 0
for _ in matrix:
    answer += sum(_)
print(answer)