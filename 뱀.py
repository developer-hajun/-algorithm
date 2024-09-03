import sys
from collections import deque

n = int(input()); k =int(input())
matrix = [[0]*n for _ in range(n)]

for i in range(k):
    a,b = map(int,input().split())
    matrix[a-1][b-1] = 1
matrix[0][0]=2
move = [[],[0,1],[0,-1],[-1,0],[1,0]]
bam =deque()
bam.append([0,0])
now_move = 1

c = int(input())
command = deque()

max_time = -1
for _ in range(c):
    time , comm = input().split()
    max_time = max(max_time, int(time))
    command.append([int(time),comm])
count = 1

for i in range(1,max_time+1):
    next_y = bam[0][0]+move[now_move][0]
    next_x = bam[0][1]+move[now_move][1]
    if 0>next_y or next_y>=n or 0>next_x or next_x>=n or matrix[next_y][next_x]==2:
        print(count)
        sys.exit()
    if matrix[next_y][next_x]==1:
        matrix[next_y][next_x]=0
        bam.appendleft([next_y,next_x])
    else:
        y,x= bam.pop()
        matrix[y][x]=0
        bam.appendleft([next_y,next_x])
    matrix[next_y][next_x]=2

    if command[0][0] == i:
        now = command.popleft()[1]
        if now_move==1:
            if now=='L':
                now_move=3
            else:
                now_move=4
        elif now_move==2:
            if now == 'L':
                now_move = 4
            else:
                now_move = 3
        elif now_move==3:
            if now == 'L':
                now_move = 2
            else:
                now_move = 1
        elif now_move==4:
            if now == 'L':
                now_move = 1
            else:
                now_move = 2
    count+=1
while True:
    next_y = bam[0][0] + move[now_move][0]
    next_x = bam[0][1] + move[now_move][1]
    if 0 > next_y or next_y >= n or 0 > next_x or next_x >= n or matrix[next_y][next_x] == 2:
        print(count)
        sys.exit()
    if matrix[next_y][next_x] == 1:
        matrix[next_y][next_x] = 0
        bam.appendleft([next_y, next_x])
    else:
        matrix[next_y][next_x] = 0
        y, x = bam.pop()
        matrix[y][x] = 0
        bam.appendleft([next_y, next_x])
    matrix[next_y][next_x] = 2
    count += 1
