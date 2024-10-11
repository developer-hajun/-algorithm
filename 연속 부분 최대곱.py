n = int(input())

value =  list(float(input()) for _ in range(n))

for i in range(1,n):
    value[i] = max(value[i],value[i-1]*value[i])
    print(value)
print('%0.3f'%max(value))