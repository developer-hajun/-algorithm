import copy

moves = [
    [],
    [(0,1),(1,0),(-1,0),(0,-1)],
    [(0,1,0,-1),(1,0,-1,0)],
    [(1,0,0,1),(1,0,0,-1),(-1,0,0,1),(-1,0,0,-1)],
    [(1,0,-1,0,0,1),(1,0,-1,0,0,-1),(0,1,0,-1,1,0),(0,1,0,-1,-1,0)],
    [(1,0,-1,0,0,1,0,-1)]
]
def move(temp,y,x,move_x,move_y):
    count = 0
    value = 0
    while True:
        ny = y+(value*move_y)
        nx = x+(value*move_x)
        if 0<=nx<m and 0<=ny<n:
            if temp[ny][nx]==6:
                break
            temp[ny][nx]=-1
        else:
            break
        value+=1
def solve(board,now):
    global answer
    if now==len(cam):
        value = 0
        for _ in board:
            value+=_.count(0)
        answer = min(answer,value)
        return
    y,x,cam_value = cam[now]
    if cam_value==1:
        for move_y, move_x in moves[cam_value]:
            temp = copy.deepcopy(board)
            move(temp,y,x,move_y,move_x)
            solve(temp,now+1)
    elif cam_value==2 or cam_value==3:
        for move_y, move_x,move_y1, move_x1 in moves[cam_value]:
            temp = copy.deepcopy(board)
            move(temp, y, x, move_y, move_x)
            move(temp, y, x, move_y1, move_x1)
            solve(temp,now+1)
    elif cam_value==4:
        for move_y, move_x,move_y1, move_x1,move_y2,move_x2 in moves[cam_value]:
            temp = copy.deepcopy(board)
            move(temp, y, x, move_y, move_x)
            move(temp, y, x, move_y1, move_x1)
            move(temp, y, x, move_y2, move_x2)
            solve(temp,now+1)
    elif cam_value==5:
        for move_y, move_x,move_y1, move_x1,move_y2,move_x2,move_y3,move_x3 in moves[cam_value]:
            temp = copy.deepcopy(board)
            move(temp, y, x, move_y, move_x)
            move(temp, y, x, move_y1, move_x1)
            move(temp, y, x, move_y2, move_x2)
            move(temp, y, x, move_y3, move_x3)
            solve(temp,now+1)




n,m = map(int,input().split())
matrix = []
visit=[[0]*m for _ in range(n)]
cam = []
wall=0
for i in range(n):
    line = list(map(int,input().split()))
    matrix.append(line)
    for j in range(m):
        if line[j]==6:
            wall+=1
        elif line[j]!=0:
            cam.append([i,j,line[j]])
answer = 64
solve(matrix,0)
print(answer)