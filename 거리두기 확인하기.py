from collections import deque


def solution(places):
    answer = []
    for p in range(5):
        place = places[p]

        def check():
            for i in range(5):
                for j in range(5):
                    if place[i][j] == 'P':
                        queue = deque()
                        queue.append([i, j])
                        visit = [[0] * 5 for _ in range(5)]
                        visit[i][j] = 1
                        for count in range(2):
                            if not queue:
                                continue
                            y, x = queue.popleft()
                            for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
                                if 0 <= ny < 5 and 0 <= nx < 5 and visit[ny][nx] == 0 and place[ny][nx] != 'X':
                                    if place[ny][nx] == 'P':
                                        return False
                                    visit[ny][nx] = 1
                                    queue.append([ny, nx])
            return True

        if check():
            answer.append(1)
        else:
            answer.append(0)
    return answer



