import sys

n,k = map(int,input().split())
if k==1:
    print(1)
    sys.exit()
arr=[[0]*(n+1) for _ in range(k+1)]
#[깊이,현재수]
arr[1]=[1]*(n+1)
for i in range(2,k+1):
    for j in range(n+1):
        for k in range(j+1):
            arr[i][j]+=arr[i-1][k]
print(arr[-1][-1]%1000000000)