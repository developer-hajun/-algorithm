t = int(input())

for _ in range(t):
    k = int(input())
    nums = [0]+list(map(int, input().split()))
    lst = [0]
    for i in range(1,k+1):
        lst.append(lst[-1]+nums[i])
    dp = [[0]*(k+1) for _ in range(k+1)]
    for length in range(2,k+1): #길이
        for now in range(1,k+2-length): #시작지점
            dp[now][now+length-1]=min(dp[now][now+q]+dp[now+q+1][now+length-1] for q in range(length-1))+(lst[now+length-1]-lst[now-1])
    print(dp[1][k])