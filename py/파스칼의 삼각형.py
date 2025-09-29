n,k = map(int,input().split())
if n<=2:
    print(1)
else:
    arr=[1,1]
    for i in range(3,n+1):
        new_arr=[]
        new_arr.append(1)
        for j in range(1,len(arr)):
            new_arr.append(arr[j-1]+arr[j])
        new_arr.append(1)
        arr=new_arr
    print(arr[k-1])