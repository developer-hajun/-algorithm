from collections import deque

n = int(input())
line=[[9999,-2]]*(n+1)
queue=deque()
queue.append([n,1,1])  # n 개수, 1 상근 -1 창영 , 카운트
while queue:
    now,who,count=queue.popleft()
    if now-3>=0 and line[now-3][0]>count:
        line[now-3]=[count,who]
        queue.append([now-3,-who,count+1])
    if now - 1 >= 0 and line[now - 1][0] > count:
        line[now - 1] = [count, who]
        queue.append([now - 1, -who, count + 1])
if line[0][1]==1:
    print("SK")
else:
    print("CY")