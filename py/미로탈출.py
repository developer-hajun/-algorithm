from collections import deque


def solution(maps):
    visit = [[[99999] * len(maps[0]) for _ in range(len(maps))] for q in range(2)]
    queue = deque()
    for i in range(len(maps)):
        if queue:
            break
        for j in range(len(maps[0])):
            if maps[i][j] == 'S':
                queue.append([i, j, 0])
                visit[0][i][j] = 0
    ch = False
    while queue:
        y, x, count = queue.popleft()
        for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= ny < len(maps) and 0 <= nx < len(maps[0]) and visit[0][ny][nx] > count + 1 and maps[ny][nx] != 'X':
                if maps[ny][nx] == 'L':
                    ch = True
                    queue = deque()
                    queue.append([ny, nx, count + 1])
                    visit[1][ny][nx] = count + 1
                    break
                queue.append([ny, nx, count + 1])
                visit[0][ny][nx] = count + 1
        if ch:
            break
    if not queue:
        return -1
    ch = False

    while queue:
        y, x, count = queue.popleft()
        for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= ny < len(maps) and 0 <= nx < len(maps[0]) and visit[1][ny][nx] > count + 1 and maps[ny][nx] != 'X':
                if maps[ny][nx] == 'E':
                    ch = True
                    queue = deque()
                    queue.append([ny, nx, count + 1])
                    break
                queue.append([ny, nx, count + 1])
                visit[1][ny][nx] = count + 1
        if ch:
            break
    if not queue:
        return -1
    return queue[-1][2]


