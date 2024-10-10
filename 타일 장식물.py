dp=[0]*81
dp[1] = 4
dp[2] = 6
n=int(input())
if n<=2:
    print(dp[n])
else:
    for i in range(3,n+1):
        dp[i] = dp[i-1]+dp[i-2]
    print(dp[n])