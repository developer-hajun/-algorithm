import heapq
from collections import deque



def find_shark(x):
    global answer
    now = 0
    while True:
        if matrix[now][x]:
            answer+= -(heapq.heappop(matrix[now][x])[0])
            break
        now+=1
def move_shark():
    for y in range(r):
        for x in range(c):
            if not matrix[y][x]:
                continue
            size,speed,moves = heapq.heappop(matrix[y][x])
            my,mx = y,x
            while speed!=0:
                ny = my+(move[moves][0]*speed)
                nx = mx+(move[moves][1]*speed)
                

r,c,m = map(int,input().split())

matrix =[]
for i in range(r):
    line=[]
    for j in range(c):
        line2=[]
        line.append(line2)
    matrix.append(line)

for i in range(m):
    y,x,speed,move,size = map(int,input().split())
    heapq.heappush(matrix[y-1][x-1],[-size,speed,move])
move = [[],[-1,0],[1,0],[0,1],[0,-1]]
answer =0
for x in range(1):
    find_shark(x)
    for _ in matrix:
        print(_)
    move_shark()