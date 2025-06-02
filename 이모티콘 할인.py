from collections import deque


def solution(storey):
    visit = [float('inf')] * (storey * 2 + 1)
    queue = deque()
    queue.append([storey, 0])
    while True:
        now, count = queue.popleft()
        if now == 0:
            return count
        for move in (now - 1, now + 1, now - 10, now + 10, now - 100, now + 100):
            if 0 <= move <= storey * 2 and not visit[move]:
                visit[move] = 1
                queue.append([move, count + 1])
    return 0
