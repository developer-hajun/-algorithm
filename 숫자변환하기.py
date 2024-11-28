def solution(x, y, n):
    dp = [99999] * (1000001)
    dp[x] = 0
    for i in range(x, y + 1):
        for v in i + n, i * 2, i * 3:
            if v <= 1000000:
                dp[v] = min(dp[v], dp[i] + 1)
    if dp[y] >= 99999:
        return -1
    else:
        return dp[y]
