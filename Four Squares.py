from cmath import sqrt

n = int(input())
value = [0] * (int(n ** (1 / 2)) + 2)

for _ in range(0,int(n**(1/2)+2)):
    value[_] = pow(_, 2)
start=int(n**(1/2))+1
count = 0
dp = [x for x in range(n+1)]
for i in range(1,n+1):
    for j in range(i):
        if value[j]>i:
            break
        if dp[i]>dp[i-value[j]]+1:
            dp[i] = dp[i-value[j]]+1
print(dp[n])