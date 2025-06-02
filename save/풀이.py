from collections import deque
def solution(land):
    answer = 0
    visit = [[0] * land[0] for _ in range(len(land))]

    def sicu(wh):
        queue = deque()
        queue.append(wh)
        count = 1
        while queue:
            y, x = queue.popleft()
            for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
                if 0 <= ny < len(land) and 0 <= nx < len(land[0]) and not visit[ny][nx] and land[ny][nx] == 1:
                    visit[ny][nx] = 1
                    count += 1
                    queue.append([ny, nx])
        return count

    for x in range(len(land[0])):
        for y in range(len(land)):
            if land[y][x] == 1 and not visit[y][x]:
                answer += sicu([y, x])

    return answer
solution([[1, 0, 1, 0, 1, 1], [1, 0, 1, 0, 0, 0], [1, 0, 1, 0, 0, 1], [1, 0, 0, 1, 0, 0], [1, 0, 0, 1, 0, 1], [1, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1]])