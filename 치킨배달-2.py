from itertools import combinations
n,m = map(int,input().split())

matrix = [list(map(int,input().split())) for i in range(n)]

house = []
seller = []
for i in range(n):
    for j in range(n):
        if matrix[i][j] == 1:
            house.append([i,j])
        elif matrix[i][j] == 2:
            seller.append([i,j])

answer = 9999999
for sell in combinations(seller,m):
    now_value = 0
    for y,x in house:
        value = 999999
        for ny,nx in sell:
            value = min(value,abs(ny-y)+abs(nx-x))
        now_value+=value
    answer = min(now_value,answer)
print(answer)
