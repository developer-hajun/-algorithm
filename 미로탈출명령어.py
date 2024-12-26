from collections import deque


def solution(n, m, x, y, r, c, k):
    # dlru순으로 가야함
    y, x = x, y

    queue = deque()
    queue.append([y, x, "", 0])
    visit = [[[0] * (k + 1) for _ in range(m + 1)] for _ in range(n + 1)]
    while queue:
        y, x, answer, count = queue.popleft()
        if count == k:
            if [y, x] == [r, c]:
                return answer
            continue

        for move_y, move_x, string in [1, 0, "d"], [0, -1, "l"], [0, 1, "r"], [-1, 0, "u"]:
            ny, nx, nanswer = y + move_y, x + move_x, answer + string
            if 1 <= ny <= n and 1 <= nx <= m and visit[ny][nx][count + 1] == 0:
                visit[ny][nx][count + 1] = 1
                queue.append([ny, nx, nanswer, count + 1])
    return "impossible"