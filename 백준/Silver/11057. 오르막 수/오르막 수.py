n = int(input())

now = [1]*(10)

for i in range(1,n):
    for j in range(1,10):
        now[j]+=now[j-1]
print(sum(now)%10007)
