from collections import deque

n=int(input())
answer = 0
queue = deque()
queue.append(n)
while queue:
    value = queue.popleft()
    if value==1:
        continue
    a = value//2
    b = value-a
    answer += a*b
    queue.append(a)
    queue.append(b)
print(answer)
