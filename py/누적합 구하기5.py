n,m = map(int,input().split())
arr = [list(map(int, input().split())) for i in range(n)]

sum_arr = []
sum_arr.append([])
for i in range(n):
    lists = [0,arr[i][0]]
    for j in range(1,n):
        lists.append(lists[-1]+arr[i][j])
    sum_arr.append(lists)

for _ in range(m):
    y1,x1,y2,x2 = map(int,input().split())
    value = 0
    for y in range(y1,y2+1):
        value+=sum_arr[y][x2]-sum_arr[y][x1-1]
    print(value)

