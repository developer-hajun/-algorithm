from collections import deque
import heapq

global answer
answer = 999999


def solution(board, r, c):
    def bfs(card1, ):
        a, b = card1
        visit = [[9999 for _ in range(4)] for _ in range(4)]
        visit[a][b] = 0
        queue = deque()
        queue.append([a, b, 0])
        while queue:
            y, x, count = queue.popleft()
            for move_y, move_x in [-1, 0], [1, 0], [0, 1], [0, -1]:
                ny, nx = y + move_y, x + move_x
                if 0 <= ny < 4 and 0 <= nx < 4 and visit[ny][nx] > count + 1:
                    visit[ny][nx] = count + 1
                    queue.append([ny, nx, count + 1])
                fy, fx = -1, -1
                for i in range(1, 5):
                    ny, nx = y + move_y * i, x + move_x * i
                    if 0 <= ny < 4 and 0 <= nx < 4:
                        fy, fx = ny, nx
                        if board[ny][nx] != 0:
                            break
                if 0 <= fy < 4 and 0 <= fx < 4 and visit[fy][fx] > count + 1:
                    visit[fy][fx] = count + 1
                    queue.append([fy, fx, count + 1])
        return visit

    card = {}
    for i in range(4):
        for j in range(4):
            if board[i][j] != 0:
                if board[i][j] not in card:
                    card[board[i][j]] = []
                card[board[i][j]].append([i, j])

    def change(p, v):
        for y, x in card[p]:
            board[y][x] = v

    def dfs(now, count, visit, ans):
        global answer
        if count == 0:
            answer = min(answer, ans)
            return
        for pick in card:
            if visit[pick]:
                continue
            # 이미 방문한 경우
            vis = bfs(now)
            t = bfs(card[pick][0])
            s = bfs(card[pick][1])

            t1, t2 = card[pick][0][0], card[pick][0][1]
            s1, s2 = card[pick][1][0], card[pick][1][1]

            value = vis[t1][t2] + t[s1][s2] + 2  # 0->1->2
            value2 = vis[s1][s2] + s[t1][t2] + 2  # 0->2->1

            visit[pick] = 1
            change(pick, 0)
            dfs([s1, s2], count - 1, visit, ans + value)
            dfs([t1, t2], count - 1, visit, ans + value2)
            change(pick, pick)
            visit[pick] = 0

    dfs([r, c], len(card), [0] * 7, 0)
    return answer






