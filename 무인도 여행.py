
from collections import deque


def solution(maps):
    visit = [[0] * len(maps[0]) for _ in range(len(maps))]

    answer = []

    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if visit[i][j] == 0 and maps[i][j] != 'X':
                queue = deque()
                queue.append([i, j])
                visit[i][j] = 1
                value = int(maps[i][j])
                while queue:
                    y, x = queue.popleft()
                    for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
                        if 0 <= ny < len(maps) and 0 <= nx < len(maps[0]) and visit[ny][nx] == 0 and maps[ny][
                            nx] != 'X':
                            value += int(maps[ny][nx])
                            visit[ny][nx] = 1
                            queue.append([ny, nx])
                answer.append(value)
    if not answer:
        return [-1]
    else:
        answer.sort()
        return answer