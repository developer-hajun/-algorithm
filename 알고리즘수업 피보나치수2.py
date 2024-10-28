from collections import deque

a,b = 1,1
n = int(input())
count = 0
for i in range(3,n+2):
    a , b = (a+b)%1000000007 , a
print(b,n-2)
