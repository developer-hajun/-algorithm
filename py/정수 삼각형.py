n = int(input())
arr =[]
for _ in range(n):
    arr.append(list(map(int,input().split())))
for i in range(n-2,-1,-1):
    new_arr=[]
    for j in range(len(arr[i])):
        value = max(arr[i+1][j],arr[i+1][j+1])+arr[i][j]
        new_arr.append(value)
    arr[i]=new_arr
print(arr[0][0])