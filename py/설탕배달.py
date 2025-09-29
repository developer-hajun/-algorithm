import sys

n=int(input())
dp=[-1]*(n+1)
if n<=5:
    if n==3 or n==5:
        print(1)
    else:
        print(-1)
    sys.exit()
dp[3]= 1
dp[5] =1

for i in range(6, n + 1):
    a = 0
    b = 0
    if dp[i - 3] > 0:
        a = dp[i - 3] + 1
    if dp[i - 5] > 0:
        b = dp[i - 5] + 1
    if a and b:
        dp[i] = min(a, b)
    if not a and b:
        dp[i] = b
    if not b and a:
        dp[i] = a
print(dp[n])