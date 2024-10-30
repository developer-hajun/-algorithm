n,m,k=map(int,input().split())
if k==0:
    arr=[[0]*m for i in range(n)]
    arr[0][0]=1
    for i in range(n):
        for j in range(m):
            if i+1<n:
                arr[i+1][j]+=arr[i][j]
            if j+1<m:
                arr[i][j+1]+=arr[i][j]
else:
    ky = k//m
    kx = k%m-1
    if kx==-1:
        ky-=1
        kx = m-1
    arr = [[0] * m for i in range(n)]
    arr[0][0] = 1
    for i in range(ky+1):
        for j in range(kx+1):
            if i+1<n:
                arr[i+1][j]+=arr[i][j]
            if j+1<m:
                arr[i][j+1]+=arr[i][j]
    value = arr[ky][kx]
    arr = [[0] * m for i in range(n)]
    arr[ky][kx] = value

    for i in range(ky,n):
        for j in range(kx,m):
            if i+1<n:
                arr[i+1][j]+=arr[i][j]
            if j+1<m:
                arr[i][j+1]+=arr[i][j]
print(arr[n-1][m-1])