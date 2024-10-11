from collections import deque

n =int(input())
value = list(map(int,input().split()))
queue = deque()

answer = -1
for i in range(n):
    if not queue or queue[-1]<=value[i]:
        queue.append(value[i])
    else:
        answer = max(answer,len(queue))
        queue=deque()
        queue.append(value[i])
answer = max(answer,len(queue))
queue=deque()

for i in range(n-1,-1,-1):
    if not queue or queue[-1] <= value[i]:
        queue.append(value[i])
    else:
        answer = max(answer, len(queue))
        queue = deque()
        queue.append(value[i])
answer = max(answer, len(queue))

print(answer)