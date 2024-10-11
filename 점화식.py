n = int(input())
value = [0]*36
value[0]=1
value[1]=1
value[2]=2
value[3]=5
if n>3:
    for i in range(4,n+1):
        for j in range(i):
            value[i]+=value[j]*value[i-j-1]
print(value[n])