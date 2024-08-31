import heapq
from collections import deque


def find_shark(x):
    global answer
    now = 0
    while True:
        if matrix[now][x]:
            answer += -(heapq.heappop(matrix[now][x])[0])
            break
        now += 1
        if now>=r:
            break


def move_shark():
    push=[]
    for y in range(r):
        for x in range(c):
            if not matrix[y][x]:
                continue
            size, speed, moves = heapq.heappop(matrix[y][x])
            my, mx,speed2 = y, x,speed
            if speed==0:
                push.append([my,mx,size,speed,moves])

            while speed2 != 0:
                ny = my + (move[moves][0] * speed2)
                nx = mx + (move[moves][1] * speed2)
                if ny<0:
                    speed2 = abs(ny)
                    my = 0
                    moves = 2
                elif ny>=r:
                    speed2 = speed2 - (r - 1 - my)
                    my = r - 1
                    moves = 1
                elif nx<0:
                    speed2 = abs(nx)
                    mx=0
                    moves=3
                elif nx>=c:
                    speed2 = speed2 - (c-1-mx)
                    mx = c-1
                    moves = 4
                else:
                    #heapq.heappush(matrix[ny][nx],[size,speed,moves])
                    push.append([ny,nx,size,speed,moves])
                    break
    for ny,nx,size,speed,mv in push:
        if matrix[ny][nx]:
            size2,speed2,mv2 = heapq.heappop(matrix[ny][nx])
            if size<size2:
                heapq.heappush(matrix[ny][nx],[size,speed,mv])
            else:
                heapq.heappush(matrix[ny][nx],[size2,speed2,mv2])
        else:
            heapq.heappush(matrix[ny][nx],[size,speed,mv])


r, c, m = map(int, input().split())

matrix = []
for i in range(r):
    line = []
    for j in range(c):
        line2 = []
        line.append(line2)
    matrix.append(line)

for i in range(m):
    y, x, speed, move, size = map(int, input().split())
    heapq.heappush(matrix[y - 1][x - 1], [-size, speed, move])
move = [[], [-1, 0], [1, 0], [0, 1], [0, -1]]
answer = 0


for x in range(c):
    find_shark(x)
    move_shark()
print(answer)