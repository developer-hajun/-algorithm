from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    board = [[0]*102 for _ in range(102)]

    for a, b, c, d in rectangle:
        a *= 2; b *= 2; c *= 2; d *= 2
        for y in range(b, d+1):
            for x in range(a, c+1):
                board[y][x] = 1

    for a, b, c, d in rectangle:
        a *= 2; b *= 2; c *= 2; d *= 2
        for y in range(b+1, d):
            for x in range(a+1, c):
                board[y][x] = 0

    q = deque()
    q.append((characterY*2, characterX*2, 0))
    board[characterY*2][characterX*2] = 3

    while q:
        y, x, dist = q.popleft()
        if (y, x) == (itemY*2, itemX*2):
            return dist // 2 

        for dy, dx in [(-1,0),(1,0),(0,-1),(0,1)]:
            ny, nx = y + dy, x + dx
            if 0 <= ny < 102 and 0 <= nx < 102 and board[ny][nx] == 1:
                board[ny][nx] = 3
                q.append((ny, nx, dist+1))
