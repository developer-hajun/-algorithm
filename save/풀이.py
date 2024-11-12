n = int(input())
dp=['']*(n+1)
dp[0]='SK'
dp[1]='CY'
value = [1,3,4]
for i in range(2,n+1):
    ch = True
    for now in value:
        if i-now >= 0  and dp[i-now]=='CY':
            ch = False
    if ch:
        dp[i]='CY'
    else:
        dp[i]='SK'
print(dp[n])

