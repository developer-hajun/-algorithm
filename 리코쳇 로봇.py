from collections import deque


def solution(board):
    R = []
    for i in range(len(board)):
        if R:
            break
        for j in range(len(board[0])):
            if board[i][j] == "R":
                R = [i, j]
                break
                board[i][j] == "."
    visit = [[[0] * 4 for _ in range(len(board[0]))] for p in range(len(board))]

    queue = deque()
    queue.append(R + [0])
    answer = -1
    while queue:
        y, x, count = queue.popleft()
        if board[y][x] == 'G':
            answer = count
            break
        for ny, nx, c in [-1, 0, 0], [1, 0, 1], [0, 1, 2], [0, -1, 3]:
            now_y = y + ny
            now_x = x + nx
            if not (0 <= now_y < len(board)) or not (0 <= now_x < len(board[0])) or board[now_y][now_x] == 'D':
                continue
            while 0 <= now_y + ny < len(board) and 0 <= now_x + nx < len(board[0]) and board[now_y + ny][
                now_x + nx] != 'D':
                now_y += ny
                now_x += nx
            if visit[now_y][now_x][c] == 0:
                queue.append([now_y, now_x, count + 1])
                visit[now_y][now_x][c] = 1
    return answer




