import copy
from collections import deque

n, m = map(int, input().split())
matrix = [list(input().strip()) for _ in range(n)]

count = int(input())
command = list(map(int, input().split()))


def delete_m(w, ans):
    if w % 2 == 0:
        for i in range(0, m):
            if matrix[n - ans][i] == 'x':
                matrix[n - ans][i] = '.'
                break
    else:
        for i in range(m - 1, -1, -1):
            if matrix[n - ans][i] == 'x':
                matrix[n - ans][i] = '.'
                break


def find_cluster():
    visit = [[0] * m for _ in range(n)]
    queue = deque()
    for i in range(m):
        if matrix[n - 1][i] == 'x':
            queue.append([n - 1, i])
            visit[n - 1][i] = 1
    while queue:
        y, x = queue.popleft()
        for next_x, next_y in [0, 1], [0, -1], [1, 0], [-1, 0]:
            new_x = x + next_x
            new_y = y + next_y
            if 0 <= new_y < n and 0 <= new_x < m and visit[new_y][new_x] == 0 and matrix[new_y][new_x] == 'x':
                visit[new_y][new_x] = 1
                queue.append([new_y, new_x])

    cluster = []
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == 'x' and visit[i][j] == 0:
                visit[i][j] = 2
                cluster.append([i, j])
    return cluster,visit


def move_cluster(cluster):
    now=1
    answer=0
    while now+cluster[0][0]<n:

        #들어 갈 수 있는지 확인
        check = True
        for y,x in cluster:
            if visit[y+now][x]==0 or visit[y+now][x]==2:
                continue
            check=False
        if not check:
            break
        #주위에 연결될 수 있는 클러스터가 있는지 확인
        check = False
        if now+cluster[0][0]!=n-1:
            for y,x in cluster:
                if visit[y+now+1][x]==1:
                    check=True
                    break
            if check:
                answer=now
        else:
            answer = now
        now+=1
    return answer



for i in range(count):
    delete_m(i, command[i])
    cust,visit = find_cluster()
    if not cust:
        continue
    cust = sorted(cust, reverse=True)
    value = move_cluster(cust)
    for y,x in cust:
        matrix[y][x]='.'
    for y,x in cust:
        matrix[y+value][x]='x'

for _ in matrix:
    for j in _:
        print(j,end='')
    print()