from collections import deque

queue = [deque(map(int, input().strip())) for _ in range(4)]
m = int(input())
for i in range(m):
    a, b = map(int, input().split())
    a -= 1
    left = queue[a][6]
    right = queue[a][2]
    queue2 = deque()
    queue2.append([a-1,-b,left,2])
    queue2.append([a+1,-b,right,6])
    visit=[0]*4
    visit[a]=1
    # for q, w, e, r in [a - 1, -b, left, 2], [a + 1, -b, right, 6]:
    #     if q == -1 or q == 4:
    #         continue
    #     elif queue[q][r] == e:
    #         continue
    #     if w == -1:
    #         queue[q].append(queue[q].popleft())
    #     else:
    #         queue[q].appendleft(queue[q].pop())
    while queue2:
        q,w,e,r = queue2.popleft()
        if q == -1 or q == 4:
            continue
        elif queue[q][r]==e:
            continue
        elif visit[q]:
            continue
        visit[q]=1
        queue2.append([q + 1, -w, queue[q][2], 6])
        queue2.append([q - 1,-w,queue[q][6],2])
        if w==-1:
            queue[q].append(queue[q].popleft())
        else:
            queue[q].appendleft(queue[q].pop())
    if b==-1:
        queue[a].append(queue[a].popleft())
    else:
        queue[a].appendleft(queue[a].pop())

answer =0
now = 1

for _ in queue:
    if _[0]==1:
        answer += now
    now*=2
print(answer)
