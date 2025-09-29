from collections import deque


def solution(board):
    visit = [[[9999999] * 4 for _ in range(len(board[0]))] for _ in range(len(board))]
    max_y, max_x = len(board), len(board[0])
    queue = deque()
    queue.append([0, 0, 0, -1])
    visit[0][0] = [0, 0, 0, 0]
    while queue:
        y, x, count, dis = queue.popleft()
        for ny, nx, ndis in [y - 1, x, 0], [y + 1, x, 1], [y, x + 1, 2], [y, x - 1, 3]:
            if y == 0 and x == 0:
                now_count = count + 100
            elif dis == ndis:
                now_count = count + 100
            else:
                now_count = count + 600
            if 0 <= ny < max_y and 0 <= nx < max_x and board[ny][nx] == 0 and visit[ny][nx][dis] >= now_count:
                visit[ny][nx][dis] = now_count
                queue.append([ny, nx, now_count, ndis])
    return min(visit[-1][-1])

