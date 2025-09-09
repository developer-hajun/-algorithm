n = int(input())
value = [list(map(int, input().split())) for i in range(n)]

now = value[0]
for i in range(1,n):
    new_value = [0,0,0]
    new_value[0] = value[i][0]+min(now[1],now[2])
    new_value[1] = value[i][1] + min(now[0], now[2])
    new_value[2] = value[i][2] + min(now[0], now[1])
    now = new_value
print(min(now))