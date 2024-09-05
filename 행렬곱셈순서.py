t = int(input())
matrix = [[-1,-1]]
for i in range(t):
    a,b = map(int,input().split())
    matrix.append([a,b])

dp = [[0]*t for _ in range(t)]
save =[[]]

for length in range(2,t+1):
    for now in range(1,t+2-length):
        dp[now][now+length-1] = min(dp[now][now+i]+dp[now+i+1][now+length-1] for i in range(length-1))