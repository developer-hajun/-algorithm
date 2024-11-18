value = [[0, 0], [5, 10], [4, 40], [6, 30], [3, 50]]

arr = [[0] * 11 for _ in range(5)]

for i in range(1, 5):
    for j in range(1, 11):
        if value[i][0] > j:
            arr[i][j] = arr[i - 1][j]
            continue
        arr[i][j] = max(arr[i - 1][j], arr[i - 1][j - value[i][0]] + value[i][1])

print("배낭용량 ->  W=  ", end=' ')
for i in range(11):
    print(f"{i:2}", end=' ')
print("\n물건  가치  무게 ", end=' ')
for i in range(11):
    print(f"{arr[0][i]:2}", end=' ')
print()

for i in range(1, len(value)):
    print(f"{i + 1:2}     {value[i][0]:2}    {value[i][1]:2}", end="   ")
    for j in range(11):
        print(f"{arr[i][j]:2}", end=' ')
    print()
