import sys

n = int(input())
value = [0]+list(int(input()) for _ in range(n) )
if n<2:
    print(value)
    sys.exit()
dp =[[0]*(n+1) for _ in range(3)] #1잔 마신경우 , 연속 2잔 마신경우
dp[1][1]=value[1]
answer = 0
for i in range(2,n+1):
    now = value[i]

    dp[1][i]= max(max(dp[2][:i-1]),max(dp[1][:i-1]))+now
    dp[2][i] = dp[1][i - 1] + now
    answer = max(answer,dp[2][i],dp[1][i])
print(answer)
