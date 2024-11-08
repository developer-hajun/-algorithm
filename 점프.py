n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]

cost = [[0]*n for _ in range(n)]
cost[0][0]=1
for i in range(n):
    for j in range(n):
        if cost[i][j]==0 or (i==(n-1)and j==(n-1)):
            continue
        if i+arr[i][j]<n:
            cost[i+arr[i][j]][j]+=cost[i][j]
        if j+arr[i][j]<n:
            cost[i][j+arr[i][j]]+=cost[i][j]

print(cost[n-1][n-1])