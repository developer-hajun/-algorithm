import sys
N = int(sys.stdin.readline())
dy = [1, 0, -1, 0]
dx = [0, -1, 0, 1]
board = [[0]*101 for _ in range(101)]
for _ in range(N):
    y, x, d, g = map(int, sys.stdin.readline().split())
    board[y][x] = 1
    curve = [d]

    for _ in range(g):
        for i in range(len(curve)-1, -1, -1):
            curve.append((curve[i]+1) % 4)


    for i in range(len(curve)): # 커브 방향 동안
        y, x = y + dy[curve[i]], x + dx[curve[i]]
        board[y][x] = 1

result = 0
for i in range(100):
    for j in range(100):
        if board[i][j] and board[i+1][j] and board[i][j+1] and board[i+1][j+1]: # 조건 4
            result += 1
print(result)