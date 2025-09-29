from collections import deque

n = int(input())

value = [list(map(int, input().split()))[1] for _ in range(n)]

queue = deque()
count = 0
for y in value:
    h  = y
    while queue and queue[-1]>y:
        if queue[-1]!=h:
            count+=1
            h = queue[-1]
        queue.pop()
    queue.append(y)

while queue:
    if queue.pop()!=0:
        count+=1
print(count)
