n,k = map(int,input().split())
count = 0
while n!=1:
    if n%k==0: n /= 5
    else: n-=1
    count+=1
# O(N)
print(count)
n,k = map(int,input().split())
count = 0
while n>=k:
    registar = (n/k)*k
    count += (n-registar)
    n = registar
    if n<k:
        break
    n = n/k
    count+=1
count += (n-1)
print(int(count))


