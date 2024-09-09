from itertools import permutations
n,m  = map(int,input().split())

matrix = [list(map(int,input().split())) for _ in range(n)]
line=[0]*m
line2 =[0]*m
matrix = [line]+matrix+[line2]

visit=[[[0]*m for _ in range(n+1)] for _ in range(3)]
#이전에 어떤각도로 들어왓는지
for y in range(1,n+1):
    for x in range(m):
        #우대각으로 들어올려면 이전에 아래와 좌대각으로 들어온 값을 불러오면됨
        for ny,nx,ins,out1,out2 in [y-1,x-1,0,1,2],[y-1,x,1,0,2],[y-1,x+1,2,0,1]:
            if 0<=ny<n and 0<=nx<m:
                visit[ins][y][x]= matrix[y][x] + min(visit[out1][ny][nx],visit[out2][ny][nx])
            else:
                visit[ins][y][x]=9999

answer = 1000
for i in range(3):
    answer = min(answer,min(visit[i][-1]))
print(answer)
