n , y = map(int, input().split())
dp=[0]*11
dp[0]=n
for i in range(1,y+1):
    if i>=1:
        dp[i] = max(dp[i-1]+(dp[i-1]*5//100),dp[i])
    if i>=3:
        dp[i] = max(dp[i-3]+(dp[i-3]*20//100),dp[i])
    if i>=5:
        dp[i] = max(dp[i-5]+(dp[i-5]*35//100),dp[i])
print(dp[y])
