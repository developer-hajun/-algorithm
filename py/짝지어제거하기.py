from collections import deque
def solution(s):
    stack = []
    queue =deque(s)
    while queue:
        while stack and queue and stack[-1]==queue[0]:
            queue.popleft()
            stack.pop()
        if queue:
            stack.append(queue.popleft())
    if stack:
        return 0
    else:
        return 1