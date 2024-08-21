from collections import deque

n = int(input())

fibo = deque()
fibo.append([0,1])

if n<=1500000:
    for i in range(2,n+1):
        first,second = fibo.popleft()
        fibo.append([second%1000000,(first+second)%1000000])
else:
    n = n%1500000
    for i in range(2, n + 1):
        first, second = fibo.popleft()
        fibo.append([second % 1000000, (first + second) % 1000000])
print(fibo[0][1])