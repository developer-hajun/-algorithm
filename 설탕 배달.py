n = int(input())
arr = [999999]*(5000+1)
arr[3]=1
arr[5]=1

for i in range(6,n+1):
    arr[i]=min(arr[i-3],arr[i-5])+1

if arr[n]>=999999:
    print(-1)
else:
    print(arr[n])