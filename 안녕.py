n = int(input())

he = [0]+list(map(int, input().split()))
happy = [0]+list(map(int, input().split()))
dp = [[0 for _ in range(100)] for _ in range(n+1)]
for i in range(1,n+1):
    for j in range(1,100):
        if he[i] <= j:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - he[i]] + happy[i])
        else:
            dp[i][j] = dp[i - 1][j]
print(dp[n][99])
