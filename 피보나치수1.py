import sys

count1,count2 = 0,0

def fibo(n):
    global count2
    a,b = 1,1
    for i in range(3,n+1):
        count2+=1
        c=a+b
        a=b
        b=c
def fibo2(n):
    global count1
    if n==1 or n==2:return 1
    count1+=1
    return fibo2(n-1)+fibo2(n-2)
k = int(input())
fibo(k)
fibo2(k)
print(count1+1,count2)