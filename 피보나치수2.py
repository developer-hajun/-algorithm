import sys

n = int(input())

if n <= 1:
    print(n)
    sys.exit()

arr = [0,1]
for i in range(2,n+1):
    arr.append(arr[i-1]+arr[i-2])
print(arr[-1])