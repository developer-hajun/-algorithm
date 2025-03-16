
from collections import deque

n, m, k = map(int, input().split())
food = [list(map(int, input().split())) for _ in range(n)]
land = [[5] * n for _ in range(n)]
trees = []
for tree in range(m):
    x, y, age = map(int, input().split())
    trees.append([x - 1, y - 1, age])
trees.sort(key=lambda x: x[2])
trees = deque(trees)

def spring():
    new_tree = deque([])
    dead_tree = deque([])
    while trees:
        y, x, age = trees.popleft()
        if land[y][x] >= age:
            new_tree.append([y, x, age + 1])
            land[y][x] -= age
        else:
            dead_tree.append([y, x, age // 2])
    while dead_tree:
        y, x, age = dead_tree.popleft()
        land[y][x] += age
    return new_tree
def fall():
    global trees  # trees를 전역 변수로 선언
    new_trees = deque()
    for r, c, age in trees:
        if age % 5 != 0:
            continue
        for nr, nc in (r - 1, c - 1), (r - 1, c), (r - 1, c + 1), (r, c - 1), (r, c + 1), (r + 1, c - 1), (r + 1, c), (r + 1, c + 1):
            if 0 <= nr < n and 0 <= nc < n:
                new_trees.append([nr, nc, 1])
    new_trees.extend(trees)
    trees = new_trees  # 새로운 나무를 trees에 할당
    for i in range(n):
        for j in range(n):
            land[i][j] += food[i][j]

for _ in range(k):
    trees = spring()
    fall()

print(len(trees))
