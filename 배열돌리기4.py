import copy
from collections import deque
from itertools import combinations, permutations

n, m, k = map(int, input().split())

matrix2 = [list(map(int, input().split())) for _ in range(n)]

answer =9999

def rotate(y, x, y1, x1, layer):
    for l in range(layer):
        queue = deque()
        for i in range(x + l, x1 - l + 1):
            queue.append(matrix[y + l][i])
        for j in range(y + l + 1, y1 - l + 1):
            queue.append(matrix[j][x1 - l])
        for i in range(x1 - l - 1, x + l - 1, -1):
            queue.append(matrix[y1 - l][i])
        for j in range(y1 - l - 1, y + l, -1):
            queue.append(matrix[j][x + l])
        queue.rotate(1)
        for i in range(x + l, x1 - l + 1):
            matrix[y + l][i] = queue.popleft()
        for j in range(y + l + 1, y1 - l + 1):
            matrix[j][x1 - l] = queue.popleft()
        for i in range(x1 - l - 1, x + l - 1, -1):
            matrix[y1 - l][i] = queue.popleft()
        for j in range(y1 - l - 1, y + l, -1):
            matrix[j][x + l] = queue.popleft()


command = [list(map(int, input().split())) for _ in range(k)]
for _ in permutations(command,k):
    matrix = copy.deepcopy(matrix2)
    for a,b,s in _:
        a -= 1
        b -= 1
        y, x, y1, x1 = a - s, b - s, a + s, b + s
        rotate(y, x, y1, x1, s)
    now = 9999
    for _ in matrix:
        now = min(now, sum(_))
    answer = min(answer,now)
print(answer)

