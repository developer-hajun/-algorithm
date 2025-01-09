from collections import deque
n = int(input())
k = int(input())


move = [[0,1],[1,0],[0,-1],[-1,0]]
matrix = [[0]*(n+1) for i in range(n+1)]
matrix[1][1]=2
for _ in range(k):
    y,x = map(int,input().split())
    matrix[y][x] = 1
l = int(input())
turn = []
for _ in range(l):
    y,x = input().split()
    turn.append([int(y),x])
turn.reverse()

bam = deque()
bam.append([1,1])
move_dist=0 #오른쪽

count = 0


while True:
    ny,nx = bam[0][0]+move[move_dist][0],bam[0][1]+move[move_dist][1]
    if ny<1 or ny>n or nx<1 or nx>n or matrix[ny][nx]==2:
        print(count+1)
        break

    if matrix[ny][nx]==1:
        matrix[ny][nx]=0
        bam.appendleft([ny,nx])
    else:
        y,x = bam.pop()
        matrix[y][x]=0
        bam.appendleft([ny,nx])
    matrix[ny][nx]=2
    count += 1
    if turn and turn[-1][0]==count:
        t = turn[-1][1]
        turn.pop()
        if t=='D':
            move_dist+=1
            if move_dist==4:
                move_dist=0
        else:
            move_dist-=1
            if move_dist==-1:
                move_dist=3
    # for _ in matrix:
    #     print(_)
    # print()


