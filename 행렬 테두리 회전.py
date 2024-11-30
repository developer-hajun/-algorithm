from collections import deque


def create_matrix(rows, columns):
    matrix = []
    count = 1
    for i in range(rows):
        row = []
        for j in range(columns):
            row.append(count)
            count += 1
        matrix.append(row)
    return matrix


def solution(rows, columns, queries):
    answer = []
    matrix = create_matrix(rows, columns)

    def rotate(y, x, ny, nx):
        route = deque()
        for go in range(x, nx + 1):
            route.append(matrix[y][go])
        for go in range(y + 1, ny + 1):
            route.append(matrix[go][nx])
        for go in range(nx - 1, x - 1, -1):
            route.append(matrix[ny][go])
        for go in range(ny - 1, y, -1):
            route.append(matrix[go][x])
        route.appendleft(route.pop())
        answer.append(min(route))
        for go in range(x, nx + 1):
            matrix[y][go] = route.popleft()
        for go in range(y + 1, ny + 1):
            matrix[go][nx] = route.popleft()
        for go in range(nx - 1, x - 1, -1):
            matrix[ny][go] = route.popleft()
        for go in range(ny - 1, y, -1):
            matrix[go][x] = route.popleft()

    for a, b, c, d in queries:
        rotate(a - 1, b - 1, c - 1, d - 1)
    return answer

