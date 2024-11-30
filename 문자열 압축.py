from collections import deque


def solution(s):
    result = 9999
    if len(s) == 1:
        return 1
    for i in range(1, len(s)):
        queue = deque()
        for j in range(0, len(s), i):
            queue.append(s[j:j + i])
        answer = []
        while queue:
            now = queue.popleft()
            if not answer:
                answer.append([1, now])
            else:
                if answer[-1][1] == now:
                    answer[-1][0] += 1
                else:
                    answer.append([1, now])
        now = ""
        for count, value in answer:
            if count == 1:
                now += value
            else:
                now += str(count) + value
        result = min(result, len(now))
    return result
