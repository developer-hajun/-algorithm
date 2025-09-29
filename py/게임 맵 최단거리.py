from collections import deque


def solution(maps):
    visit = [[100001] * len(maps[0]) for _ in range(len(maps))]

    queue = deque()
    queue.append([0, 0, 0])
    visit[0][0] = 0
    while queue:
        y, x, count = queue.popleft()
        for ny, nx in [y - 1, x], [y + 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= ny < len(maps) and 0 <= nx < len(maps[0]) and maps[ny][nx] == 1 and visit[ny][nx] > count + 1:
                visit[ny][nx] = count + 1
                queue.append([ny, nx, count + 1])
    if visit[-1][-1] == 100001:
        return -1
    else:
        return visit[-1][-1] + 1