from collections import deque

n = int(input())
k = [0]*(n+1)
queue=deque()
queue.append(1)



while queue:
    q=queue.popleft()
    if q==n:
        break
    if q*3<=n and k[q*3]==0:
        queue.append(q*3)
        k[q*3]=q
    if q * 2 <= n and k[q * 2] == 0:
        queue.append(q * 2)
        k[q * 2]=q
    if q+1<=n and k[q+1] == 0:
        queue.append(q+1)
        k[q+1]=q

answer = [n]

now = n
while k[now]:
    answer.append(k[now])
    now = k[now]
print(len(answer)-1)
for _ in answer:
    print(_,end=' ')



