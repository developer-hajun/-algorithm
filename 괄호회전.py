from collections import deque


def solution(s):
    answer = 0
    for i in range(len(s)):
        queue = deque(list(s.strip()))
        for j in range(i):
            queue.append(queue.popleft())

        def check():
            c = []
            while queue:
                now = queue.popleft()
                if now in ["{", '(', '[']:
                    if now == '{':
                        c.append('}')
                    elif now == '[':
                        c.append(']')
                    elif now == '(':
                        c.append(')')
                else:
                    if not c:
                        return False
                    if c[-1] != now:
                        return False
                    c.pop()
            if c:
                return False
            return True

        if check():
            answer += 1
    return answer


