from collections import deque


def solution(n, works):
    works.sort(reverse=True)
    max_values = sorted(list(set(works)))
    while n > 0 and max_values:
        left = max_values.pop()
        right = 0
        if max_values:
            right = max_values[-1]
        queue = deque()
        for index in range(len(works)):
            if left == works[index]:
                queue.append(index)
        while n != 0 and works[queue[0]] != right:
            index = queue.popleft()
            works[index] -= 1
            queue.append(index)
            n -= 1
    answer = 0
    for i in works:
        answer += i ** 2
    return answer



