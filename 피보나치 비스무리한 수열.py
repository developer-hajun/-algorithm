import sys

fibo = [0]*1000001

fibo[1]=1
fibo[2]=1
fibo[3]=1

n=int(input())
if n<=3:
    print(fibo[n])
    sys.exit()
for i in range(4,n+1):
    fibo[i]=(fibo[i-1]+fibo[i-3])
print(fibo[n])


