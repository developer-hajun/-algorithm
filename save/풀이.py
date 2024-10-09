import sys

n = int(input())
dp = [100000]*100001
dp[2]=1
dp[4]=2
dp[5]=1
if n<=5:
    if dp[n]==100000:
        print(-1)
    else:
        print(dp[n])
    sys.exit()
for i in range(6,n+1):
    dp[i] = min(dp[i],dp[i-2],dp[i-5])+1
if dp[n] == 100000:
    print(-1)
else:
    print(dp[n])
import sys
from collections import deque
T = int(input())

for _ in range(T):
    A, B = map(int,sys.stdin.readline().rstrip().split())

    visited = [False for i in range(10001)]
    deq = deque()
    deq.append([A,''])
    visited[A] = True

    while deq:
        num, command = deq.popleft()

        if num == B:
            print(command)
            break

        d = num * 2 % 10000
        if not visited[d]:
            visited[d] = True
            deq.append([d, command + 'D'])

        s = (num - 1) % 10000
        if not visited[s]:
            visited[s] = True
            deq.append([s, command + 'S'])

        l = num // 1000 + (num % 1000)*10
        if not visited[l]:
            visited[l] = True
            deq.append([l, command + 'L'])

        r = num // 10 + (num % 10) * 1000
        if not visited[r]:
            visited[r] = True
            deq.append([r, command + 'R'])