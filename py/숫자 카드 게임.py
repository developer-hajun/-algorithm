o,m = map(int, input().split())

max_result = -1
for i in range(o):
    data = list(map(int,input().split()))
    min_value = min(data)
    max_result = max(max_result,min_value)
print(max_result);