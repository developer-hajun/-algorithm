import copy
import sys
from collections import deque

n, m = map(int, input().split())

matrix = [list(input().strip()) for i in range(n)]

R = []
B = []
for i in range(n):
    for j in range(m):
        if matrix[i][j] == 'R':
            R = [i, j]
            matrix[i][j] = '.'
        elif matrix[i][j] == 'B':
            B = [i, j]
            matrix[i][j] = '.'
move = [[0, -1], [0, 1], [-1, 0], [1, 0]]

queue = deque()
queue.append([R, B,0])


def move_board(ball, dir,ball_r,ball_b):
    ch=False
    while True:
        ny = ball[0] + move[dir][0]
        nx = ball[1] + move[dir][1]
        if 0 <= ny < n and 0 <= nx < m and [ny, nx] != ball_r and [ny, nx] != ball_b:
            if matrix[ny][nx] == 'O':
                ch=True
                ball=[0,0]
                break
            elif matrix[ny][nx] == '.':
                ball = [ny,nx]
            elif matrix[ny][nx] == '#':
                break
        else:
            break
    return ball,ch


def move_left():
    new_R = copy.deepcopy(now_R)
    new_B = copy.deepcopy(now_B)
    if new_R[1] < new_B[1]:
        new_R,ch1 = move_board(new_R, 0,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_B,ch2= move_board(new_B, 0,copy.deepcopy(new_R),copy.deepcopy(new_B))
    else:
        new_B,ch2 = move_board(new_B, 0,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_R,ch1 = move_board(new_R, 0,copy.deepcopy(new_R),copy.deepcopy(new_B))
    if ch1 and not ch2:
        print(c+1)
        sys.exit()
    elif (ch1 and ch2) or ch2:
        return now_R, now_B
    return new_R,new_B


def move_right():
    new_R = copy.deepcopy(now_R)
    new_B = copy.deepcopy(now_B)
    if new_R[1] > new_B[1]:
        new_R,ch1 = move_board(new_R, 1,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_B,ch2 = move_board(new_B, 1,copy.deepcopy(new_R),copy.deepcopy(new_B))
    else:
        new_B,ch2 = move_board(new_B, 1,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_R,ch1 = move_board(new_R, 1,copy.deepcopy(new_R),copy.deepcopy(new_B))
    if ch1 and not ch2:
        print(c+1)
        sys.exit()
    elif (ch1 and ch2) or ch2:
        return now_R, now_B
    return new_R,new_B


def move_up():
    new_R = copy.deepcopy(now_R)
    new_B = copy.deepcopy(now_B)
    if new_R[0] < new_B[0]:
        new_R,ch1 = move_board(new_R, 2,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_B,ch2 = move_board(new_B, 2,copy.deepcopy(new_R),copy.deepcopy(new_B))
    else:
        new_B,ch2 = move_board(new_B, 2,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_R,ch1 = move_board(new_R, 2,copy.deepcopy(new_R),copy.deepcopy(new_B))
    if ch1 and not ch2:
        print(c+1)
        sys.exit()
    elif (ch1 and ch2) or ch2:
        return now_R, now_B
    return new_R,new_B


def move_down():
    new_R = copy.deepcopy(now_R)
    new_B = copy.deepcopy(now_B)
    if new_R[0] > new_B[0]:
        new_R,ch1 = move_board(new_R, 3,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_B,ch2 = move_board(new_B, 3,copy.deepcopy(new_R),copy.deepcopy(new_B))
    else:
        new_B,ch2 = move_board(new_B, 3,copy.deepcopy(new_R),copy.deepcopy(new_B))
        new_R,ch1 = move_board(new_R, 3,copy.deepcopy(new_R),copy.deepcopy(new_B))
    if ch1 and not ch2:
        print(c+1)
        sys.exit()
    elif (ch1 and ch2) or ch2:
        return now_R, now_B
    return new_R,new_B


while queue:
    now_R, now_B,c = queue.popleft()
    if c==10:
        break
    r,b = move_left()
    if [r,b]!=[now_R,now_B]:
        queue.append([r,b,c+1])
    r, b = move_right()
    if [r, b] != [now_R, now_B]:
        queue.append([r, b, c + 1])
    r, b = move_up()
    if [r, b] != [now_R, now_B]:
        queue.append([r, b, c + 1])
    r, b = move_down()
    if [r, b] != [now_R, now_B]:
        queue.append([r, b, c + 1])
print(-1)
