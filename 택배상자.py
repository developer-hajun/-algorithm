from collections import deque


def solution(order):
    queue = deque()
    for i in range(len(order)):
        queue.append(i + 1)
    stack = []
    no = 0
    while queue:
        while stack:
            if stack[-1] == order[no]:
                stack.pop()
                no += 1
            else:
                break
        if queue[0] == order[no]:
            no += 1
            queue.popleft()
        elif queue[0] > order[no]:
            while stack and stack[-1] == order[no]:
                stack.pop()
                no += 1
            else:
                break
        else:
            stack.append(queue.popleft())
    while stack:
        if stack[-1] == order[no]:
            stack.pop()
            no += 1
        else:
            break

    return no