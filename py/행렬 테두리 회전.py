from collections import deque


def solution(rows, columns, queries):
    matrix = []
    matrix.append([0] * columns)
    now = 1
    for i in range(rows):
        a = [0]
        for j in range(columns):
            a.append(now)
            now += 1
        matrix.append(a)
    answer = []

    def move(y, x, yy, xx):
        queue = deque()
        for nx in range(x, xx + 1):
            queue.append(matrix[y][nx])
        for ny in range(y + 1, yy + 1):
            queue.append(matrix[ny][xx])
        for nx in range(xx - 1, x - 1, -1):
            queue.append(matrix[yy][nx])
        for ny in range(yy - 1, y, -1):
            queue.append(matrix[ny][x])
        answer.append(min(queue))
        queue.appendleft(queue.pop())

        for nx in range(x, xx + 1):
            matrix[y][nx] = queue.popleft()
        for ny in range(y + 1, yy + 1):
            matrix[ny][xx] = queue.popleft()
        for nx in range(xx - 1, x - 1, -1):
            matrix[yy][nx] = queue.popleft()
        for ny in range(yy - 1, y, -1):
            matrix[ny][x] = queue.popleft()

    for y, x, ny, nx in queries:
        move(y, x, ny, nx)
    return answer
