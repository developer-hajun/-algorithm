import sys

n,k = map(int,input().split())
color = [list(map(int,input().split())) for i in range(n)]
record = []
moves = [[0,1],[0,-1],[-1,0],[1,0]]
for i in range(n):
    line=[]
    for j in range(n):
        line2=[]
        line.append(line2)
    record.append(line)

chess = []
for i in range(k):
    y,x,dir = map(int,input().split())
    chess.append([y-1,x-1,dir-1])
    record[y-1][x-1].append(i)

def move(i):
    y,x,dir = chess[i]
    if record[y][x][0]!=i:
        return 0
    ny = y+moves[dir][0]
    nx = x+moves[dir][1]
    if not 0<=ny<n or not 0<=nx<n or color[ny][nx]==2:
        if 0<=dir<=1:
            now_d = (dir+1)%2
        else:
            now_d= (dir-1)%2+2
        chess[i][2]=now_d
        nx = x+moves[now_d][1]
        ny = y+moves[now_d][0]
        if not 0 <= nx < n or not 0 <= ny < n or color[ny][nx] == 2:
            return 0
    chess_set = []
    chess_set.extend(record[y][x])
    record[y][x]=[]

    if color[ny][nx]==1:
        chess_set.reverse()

    for i in chess_set:
        record[ny][nx].append(i)
        chess[i][:2] = [ny,nx]

    if len(record[ny][nx]) >= 4:
        return 1
    return 0


turn = 1
while turn <= 1000:
    for i in range(k):
        now = move(i)
        if now:
            print(turn)
            sys.exit()
    turn+=1

print(-1)
