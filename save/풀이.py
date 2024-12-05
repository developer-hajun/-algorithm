def solution(n):
    arr = [0]*(n+1)
    if n<=2:
        return n
    arr[1]=1
    arr[2]=1
    for i in range(2,n+1):
        arr[i]+=arr[i-1]+arr[i-2]
        arr[i]%=1234567
    return arr[-1]