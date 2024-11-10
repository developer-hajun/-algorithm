dp = [[0]*10 for _ in range(65)]
dp[1]=[1]*10
t= int(input())
value = list(int(input()) for _ in range(t))
for i in range(2,max(value)+1):
    for j in range(10):
        for k in range(j+1):
            dp[i][j] += dp[i-1][k]

for _ in value:
    print(sum(dp[_]))