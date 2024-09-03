import copy

m, s = map(int, input().split())
matrix = []
shark = []
move_fish = [[0, -1], [-1, -1], [-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1]]
move_shark = [[-1, 0], [0, -1], [1, 0], [0, 1]]
small = [[0] * 4 for i in range(4)]


def init():
    global shark
    for i in range(4):
        line = []
        for j in range(4):
            line2 = []
            line.append(line2)
        matrix.append(line)
    for _ in range(m):
        a, b, c = map(int, input().split())
        matrix[a - 1][b - 1].append(c - 1)
    shark = list(map(int, input().split()))
    shark[0]-=1
    shark[1]-=1

def fish_move():
    n_matrix = []
    for y in range(4):
        line = []
        for x in range(4):
            line2 = []
            line.append(line2)
        n_matrix.append(line)

    for y in range(4):
        for x in range(4):
            if not matrix[y][x]:
                continue
            while matrix[y][x]:
                now = matrix[y][x].pop()
                check = True
                for i in range(8):
                    ny = y + move_fish[now][0]
                    nx = x + move_fish[now][1]
                    if 0 <= ny < 4 and 0 <= nx < 4 and small[ny][nx] == 0 and [ny, nx] != shark:
                        check = False
                        n_matrix[ny][nx].append(now)
                        break
                    now -= 1
                    if now == -1:
                        now = 7
                if check:
                    n_matrix[y][x].append(now)
    return n_matrix


def shark_move(y, x, fish_value, count, visit):
    global max_eat, shark, eat
    if count == 3:
        if fish_value > max_eat:
            max_eat = fish_value
            shark = [y, x]
            eat = copy.deepcopy(visit)
        return
    for i in range(4):
        ny = y + move_shark[i][0]
        nx = x + move_shark[i][1]
        if 0 <= ny < 4 and 0 <= nx < 4:
            if [ny, nx] not in visit:
                visit.append([ny,nx])
                shark_move(ny, nx, fish_value + len(matrix[ny][nx]), count + 1, visit)
                visit.pop()
            else:
                shark_move(ny, nx, fish_value, count + 1, visit)

def shark_eat():
    for y,x in eat:
        if matrix[y][x]:
            small[y][x]=3
            matrix[y][x]=[]
def small_remove():
    for y in range(4):
        for x in range(4):
            if small[y][x]:
                small[y][x]-=1



init()

for _ in range(s):
    copy_fish = copy.deepcopy(matrix)
    matrix = fish_move()
    max_eat, eat = -1, []
    shark_move(shark[0],shark[1],0,0,[])
    shark_eat()
    small_remove()
    for i in range(4):
        for j in range(4):
            matrix[i][j] += copy_fish[i][j]
answer = 0
for i in range(4):
    for j in range(4):
        answer += len(matrix[i][j])
print(answer)