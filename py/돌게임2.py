n = int(input())
dp=[-1]*(n+1)
#0상근 1창영
dp[1] = 1
dp[2] = 0
dp[3] = 1
if n<=3:
    if dp[n]:
        print("CY")
    else:
        print("SK")

for i in range(4,n+1):
    if dp[i-1] or dp[i-3]:
        dp[i]= 0
    else:
        dp[i]=1
if dp[n]:
    print("CY")
else:
    print("SK")