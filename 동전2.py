n,k = map(int,input().split())
coin = list(int(input()) for _ in range(n))
arr = [999999999]*(k+1)
arr[0]=0
for i in coin:
    if k<i:
        continue
    arr[i]=1

for i in range(1,k+1):
    for value in coin:
        if i>=value:
            arr[i]=min(arr[i],arr[i-value]+1)
if arr[k]==999999999:
    print(-1)
else:
    print(arr[k])
