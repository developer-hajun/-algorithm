arr =[999999]*21
arr[0]=0
arr[16]=1
arr[10]=1
arr[5]=1
arr[1]=1

cost =[16,10,5,1]
for i in range(21):
    for value in cost:
        if i-value>0:
            arr[i] = min(arr[i],arr[i-value]+1)
print('j',end='')
for i in range(21):
    print("{:>{}}".format(i, max(arr)-2), end='')
print("\nc",end='')
for i in range(21):
    print("{:>{}}".format(arr[i], max(arr)-2), end='')