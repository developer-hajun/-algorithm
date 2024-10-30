n=int(input())
arr=[0]+list(map(int,input().split()))
dp =[99999]*(n+1)
dp[1]=0
for i in range(1,n+1):
    if dp[i]==99999:
        continue
    for j in range(i+1,i+arr[i]+1):
        if j>n:
            break
        dp[j]=min(dp[j],dp[i]+1)
if dp[-1]==99999:
    print(-1)
else:
    print(dp[-1])