
n,m = map(int,input().split())
arr = [[0]*(m+1)]+[[0]+list(map(int,input().split())) for _ in range(n)]
for y in range(1,n+1):
    for x in range(1,m+1):
        arr[y][x] = max(arr[y-1][x],arr[y-1][x-1],arr[y][x-1])+arr[y][x]
print(arr[n][m])