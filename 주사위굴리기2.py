from collections import deque

dice = [1, 2, 3, 4, 5, 6]
dir = 0
y, x = 0, 0

move = [[0, 1], [1, 0], [0, -1], [-1, 0]]


def turn():
    global dice
    a, b, c, d, e, f = dice
    if dir == 0:  #동
        dice = d, b, a, f, e, c
    elif dir == 1:  #남
        dice = b, f, c, d, a, e
    elif dir == 2:  #서
        dice = c, b, f, a, e, d
    else:  #북
        dice = e, a, c, d, f, b


n, m, k = list(map(int, input().split()))
matrix = [list(map(int, input().split())) for _ in range(n)]


def move_dice():
    global y, x, dir
    ny, nx = y + move[dir][0], x + move[dir][1]
    if 0 <= ny < n and 0 <= nx < m:
        y, x = ny, nx
        turn()
    else:
        y, x = y - move[dir][0], x - move[dir][1]
        dir = (dir + 2) % 4
        turn()


def get_answer():
    visit = [[0] * m for _ in range(n)]
    count = matrix[y][x]
    visit[y][x] = 1
    queue = deque()
    queue.append([y, x])
    while queue:
        i, j = queue.popleft()
        for ny, nx in (i + 1, j), (i - 1, j), (i, j - 1), (i, j + 1):
            if 0 <= ny < n and 0 <= nx < m and visit[ny][nx] == 0 and matrix[ny][nx] == matrix[y][x]:
                visit[ny][nx] = 1
                count += matrix[ny][nx]
                queue.append([ny, nx])
    return count


answer = 0
for _ in range(k):
    move_dice()
    answer += get_answer()
    if matrix[y][x]<dice[-1]:
        dir=(dir+1)%4
    elif matrix[y][x] > dice[-1]:
        dir -=1
        if dir<0:
            dir=3
print(answer)
