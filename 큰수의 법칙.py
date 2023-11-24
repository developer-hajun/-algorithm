n,m,k = map(int,input().split())
data = list(map(int,input().split()))
data.sort()
first = data[n-1]
second = data[n-2]

result = 0
for i in range(1,m+1):
    if i%(k+1) == 0:
        result+=second
    else:
        result+=first
print(result)
# O(n)
result=0
CS = m/(k+1)
result += (m-CS)*first
result += CS*second
# O(1)
print(int(result))
