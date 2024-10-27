n = int(input())

now = [1]*(10)

for i in range(1,n):
    sum_value = []
    sum_value.append(now[0])
    for j in range(1,10):
        sum_value.append(sum_value[-1]+now[j])
    for j in range(10):
        now[j] = sum_value[j]
print(sum(now)%10007)
