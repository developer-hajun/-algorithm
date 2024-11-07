import sys

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 5)
n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[-1] * m for _ in range(n)]
moves = [[1, 0], [0, 1], [-1, 0], [0, -1]]


def dfs(y, x):
    if y == n - 1 and x == m - 1:
        return 1
    if visited[y][x] != -1:
        return visited[y][x]
    visited[y][x] = 0
    for move_y, move_x in moves:
        ny = y + move_y
        nx = x + move_x
        if 0 <= ny < n and 0 <= nx < m and graph[ny][nx] < graph[y][x]:
            if visited[ny][nx] != -1:
                visited[y][x]+=visited[ny][nx]
            else:
                visited[y][x] += dfs(ny, nx)
    return visited[y][x]


print(dfs(0, 0))
