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
