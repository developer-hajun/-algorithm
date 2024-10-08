import sys

n=int(input())
if n==1 or n==2:
    print(n)
    sys.exit()
dp = [0]*(n+1)
dp[1]=1
dp[2]=2
for _ in range(3,n+1):
    dp[_]=(dp[_-2]+dp[_-1])%15746
print(dp[n])