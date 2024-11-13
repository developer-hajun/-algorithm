from collections import deque


def solution(land):
    answer = [0] * len(land[0])
    visit = [[0] * (len(land[0])) for _ in range(len(land))]
    da = 0
    for i in range(len(land)):
        for j in range(len(land[0])):
            if land[i][j] == 1 and visit[i][j] == 0:
                count = 1
                visit[i][j] = 1
                queue = deque()
                queue.append([i, j])
                s1 = set()
                while queue:
                    y, x = queue.popleft()
                    s1.add(x)
                    for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
                        if 0 <= nx < len(land[0]) and 0 <= ny < len(land):
                            if visit[ny][nx] == 0 and land[ny][nx] == 1:
                                count += 1
                                visit[ny][nx] = 1
                                queue.append([ny, nx])

                for s in s1:
                    answer[s] += count
    return max(answer)
